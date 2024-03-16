<%-- 
    Document   : news
    Created on : Mar 16, 2024, 3:48:42 PM
    Author     : -MSI-
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Page Title</title>
        <style>

            * {
                font-family: 'Poppins';
            }

            body {

                margin: 0;
            }

            a:hover {
                cursor: pointer;
            }

            #pagination {
                display: flex;
                align-items: center;
                justify-content: center;
                margin-top: 20px;
            }

            ul {
                list-style: none;
                padding: 0;
                margin: 0;
                display: inline-flex;
                flex-wrap: wrap;
            }

            li {
                color: #fff;
                display: flex;
            }

            li a {
                background-color: #444B6E;
                padding: 5px 10px;
                border: 2px solid #3D315B;
                border-right: 0;
                text-decoration: none;
            }

            li.active a {
                background-color: #9AB87A;
            }

            li:first-child a {
                border-radius: 5px 0 0 5px;
            }

            li:last-child a {
                border-radius: 0 5px 5px 0;
                border-right: 2px solid #3D315B;
            }

            html, body {
                height: 100%;
                margin: 0;
                padding: 0;

            }

            .container {
                width: 100%;
                max-width: 1200px;
                margin: 0 auto;
                padding-bottom: 50px; /* height of the pagination div */
            }

            .main-paging {
                margin-top: 20px;
            }

            .main-paging table {
                width: 100%;
                border-collapse: collapse;
            }

            .main-paging th, .main-paging td {
                padding: 10px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }

            .main-paging th {
                background-color: #f2f2f2;
                font-weight: bold;
            }

            .main-paging tr:hover {
                background-color: #f5f5f5;
            }

            #pagination {
                position: absolute;
                bottom: 20px;
                left: 0;
                width: 100%;

                text-align: center;

            }
            .body-notice {
                width: 100%;
                padding: 10px;
                box-sizing: border-box;
            }

            .body-notice table {
                width: 100%;
                border-collapse: collapse;
            }

            .body-notice td {
                padding: 10px;
                border: 1px solid #ccc;
            }

            .body-notice td:first-child {
                width: 70%;
            }

            .body-notice td:nth-child(2) {
                width: 20%;
            }

            .body-notice td:last-child {
                width: 10%;
            }
        </style>
    </head>
    <body>
    
        <jsp:include page="../homebutton.jsp"></jsp:include>
        <div class="container">

            <div class="main-paging">
                <c:forEach items="${requestScope.news}" var="n">
                    <div class="body-notice">
                        <table>

                            <tbody>

                                <tr>
                                    <td>${n.content}</td>
                                    <td>${n.title}</td>
                                    <td>${n.dateCreated}</td>
                                </tr>


                            </tbody>

                        </table>
                    </div>
                </c:forEach> 
                </ul>
            </div>
            <div id="pagination"></div>
        </div>


        <script>
            let pages = ${totalPage};
            let currentPage = ${currentPage}
            document.getElementById('pagination').innerHTML = createPagination(pages, currentPage);

            function createPagination(pages, page) {
                let str = '<ul>';
                let active;
                let pageCutLow = page - 1;
                let pageCutHigh = page + 1;
                // Show the Previous button only if you are on a page other than the first
                if (page > 1) {
                    str += '<li class="page-item previous no"><a onclick="updateUrl(' + (page - 1) + ')">Previous</a></li>';
                }
                // Show all the pagination elements if there are less than 6 pages total
                if (pages < 6) {
                    for (let p = 1; p <= pages; p++) {
                        active = page == p ? "active" : "no";
                        str += '<li class="' + active + '"><a onclick="updateUrl(' + p + ')">' + p + '</a></li>';
                    }
                }
                // Use "..." to collapse pages outside of a certain range
                else {
                    // Show the very first page followed by a "..." at the beginning of the
                    // pagination section (after the Previous button)
                    if (page > 2) {
                        str += '<li class="no page-item"><a onclick="updateUrl(1)">1</a></li>';
                        if (page > 3) {
                            str += '<li class="out-of-range"><a onclick="updateUrl(' + (page - 2) + ')">...</a></li>';
                        }
                    }
                    // Determine how many pages to show after the current page index
                    if (page === 1) {
                        pageCutHigh += 2;
                    } else if (page === 2) {
                        pageCutHigh += 1;
                    }
                    // Determine how many pages to show before the current page index
                    if (page === pages) {
                        pageCutLow -= 2;
                    } else if (page === pages - 1) {
                        pageCutLow -= 1;
                    }
                    // Output the indexes for pages that fall inside the range of pageCutLow
                    // and pageCutHigh
                    for (let p = pageCutLow; p <= pageCutHigh; p++) {
                        if (p === 0) {
                            p += 1;
                        }
                        if (p > pages) {
                            continue;
                        }
                        active = page == p ? "active" : "no";
                        str += '<li class="page-item ' + active + '"><a onclick="updateUrl(' + p + ')">' + p + '</a></li>';
                    }
                    // Show the very last page preceded by a "..." at the end of the pagination
                    // section (before the Next button)
                    if (page < pages - 1) {
                        if (page < pages - 2) {
                            str += '<li class="out-of-range"><a onclick="updateUrl(' + (page + 2) + ')">...</a></li>';
                        }
                        str += '<li class="page-item no"><a onclick="updateUrl(' + pages + ')">' + pages + '</a></li>';
                    }
                }
                // Show the Next button only if you are on a page other than the last
                if (page < pages) {
                    str += '<li class="page-item next no"><a onclick="updateUrl(' + (page + 1) + ')">Next</a></li>';
                }
                str += '</ul>';
                // Return the pagination string to be outputted in the pug templates
                document.getElementById('pagination').innerHTML = str;
                return str;
            }

            function updateUrl(id) {
                window.location.href = "http://localhost:9999/testasm/student/news?id=" + id;
            }

        </script>

    </body>
</html>
