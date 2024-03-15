<%-- 
    Document   : studentUpdate
    Created on : Mar 11, 2024, 11:28:36 AM
    Author     : -MSI-
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .container {
                width: 80%;
                margin: 0 auto;
            }

            /* Apply styles to the table */
            table {
                border-collapse: collapse;
                width: 100%;
            }

            /* Apply styles to table headers */
            th {
                background-color: #f2f2f2;
                border: 1px solid #dddddd;
                padding: 12px;
                text-align: left;
                font-weight: bold; /* Make header text bold */
            }

            /* Apply styles to table cells */
            td {
                border: 1px solid #dddddd;
                padding: 12px;
                text-align: left;
            }

            /* Style the textarea */
            textarea {
                width: 100%;
                height: 150px;
                font-size: 14px;
                resize: vertical;
                padding: 5px;
                border: 1px solid #dddddd;
                border-radius: 5px;
                margin: 8px 0; /* Add margin between textareas */
            }

            /* Style the submit button */
            input[type="submit"] {
                background-color: #4CAF50;
                color: white;
                padding: 12px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                transition: background-color 0.3s;
            }

            input[type="submit"]:hover {
                background-color: #45a049;
            }

        </style>
    </head>
    <body>
        <jsp:include page="../homebutton.jsp"></jsp:include>
            <div class="container">
                <form action="updatefeedback" method="post">


                    <table>
                        <thead>
                        <th>Content</th>
                        <th>Lecture</th>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.feedbacks}" var="f">
                            <tr>
                                <td><textarea id="content" name="content${f.id}" rows="10" cols="50">${f.content}</textarea></td>
                                <td>${f.group.teacher.name}</td>
                            </tr>


                        </c:forEach>
                    </tbody>
                </table>

                <input type="submit" value="save"/>
            </form>
        </div>
    </body>
</html>
