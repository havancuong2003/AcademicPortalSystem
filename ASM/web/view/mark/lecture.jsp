<%-- 
    Document   : lecture
    Created on : Feb 28, 2024, 1:57:01 PM
    Author     : -MSI-
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Grade Information</title>
        <style>
            /* Reset CSS */
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }

            /* Body styles */
            body {
                font-family: Arial, sans-serif;
                background-color: #f7f7f7;
                color: #333;
            }

            /* Container styles */
            .container {
                max-width: 1200px;
                margin: 0 auto;
                padding: 20px;
            }

            /* Grade section styles */
            .grades {
                background-color: #fff;
                border-radius: 5px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                padding: 20px;
                margin-bottom: 20px;
            }

            .grades h2 {
                margin-bottom: 20px;
            }

            /* Table styles */
            table {
                width: 100%;
                border-collapse: collapse;
                background-color: #fff;
            }

            th, td {
                padding: 10px;
                border-bottom: 1px solid #ddd;
                text-align: left;
            }

            th {
                background-color: #777;
                color: #fff;
            }

            /* Anchor tag styles */
            a {
                display: inline-block;
                padding: 8px 16px;
                text-decoration: none;
                color: #fff;
                background-color: #777;
                border-radius: 5px;
                transition: background-color 0.3s;
            }
            <c:if test="${requestScope.check eq 'hidden'}">
                .grades{
                    display:none;
                }
            </c:if>

            a:hover {
                background-color: #45a049;
            }
        
        </style>
    </head>
    <body>
         <jsp:include page="../homebutton.jsp"></jsp:include>
        <div class="container">
            <c:forEach items="${requestScope.listClassTeach}" var="g">


                <a  href="mark?termid=${g.term.id}&groupdid=${g.id}" onclick="showGradeTable()"> <span>${g.term.id}</span>
                    <span>${g.course.code}</span>
                    <span>${g.name}</span></a>
                </c:forEach>
            <div class="grades">
                <h2>Grade Information</h2>
                <table id="gradeTable">
                    <thead>
                        <tr>
                            <th>GRADE CATEGORY</th>
                            <th>GRADE ITEM</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody id="gradeTableBody">
                      
                    </tbody>
                </table>
            </div>
        </div>

        <script>


            var courseData = [
            <c:forEach items="${requestScope.gradeTableMark}" var="m">

                {
                    groupID: '${m.group.id}',
                    gradeCategory: '${m.gradeCategory}',
                    gradeItem: '${m.gradeItem}'
                },

            </c:forEach>
            ];
          
            var categoryCounts = {};

            
            courseData.forEach(function (item) {
                var category = item.gradeCategory;
              
                if (categoryCounts[category] === undefined) {
                    categoryCounts[category] = 1;
                } else {
                    categoryCounts[category]++;
                }
            });

           
            var html = '';
            courseData.forEach(function (item, index) {
                if (index === 0 || item.gradeCategory !== courseData[index - 1].gradeCategory) {
                    html += "<tr>";
                    html += "<td rowspan='" + categoryCounts[item.gradeCategory] + "'>" + item.gradeCategory + "</td>";
                }
                html += "<td>" + item.gradeItem + "</td>";
                html += "<td><a href='updatemark?groupid=" + item.groupID + "&gradeItem=" + encodeURIComponent(item.gradeItem) + "'>View Grade Item</a></td>";


                html += "</tr>";
            });
            document.getElementById("gradeTableBody").innerHTML = html;
            function showGradeTable() {
                var show = document.querySelector(".grades");
                show.style.display = "block";
            }
        </script>

    </body>
</html>

