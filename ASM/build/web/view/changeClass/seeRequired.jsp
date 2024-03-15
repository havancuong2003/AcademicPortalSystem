<%-- 
    Document   : seeRequired
    Created on : Mar 13, 2024, 12:42:41 PM
    Author     : -MSI-
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Request Page</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f2f2f2;
            }

            .container {
                max-width: 1200px;
                margin: 0 auto;
                padding: 20px;
            }

            .header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 20px;
            }

            h2 {
                color: #333;
                margin-bottom: 0;
            }

            table {
                border-collapse: collapse;
                width: 100%;
            }

            th,
            td {
                border: 1px solid #ddd;
                padding: 8px;
                text-align: left;
            }

            th {
                background-color: #4CAF50;
                color: white;
                padding-top: 12px;
                padding-bottom: 12px;
                text-align: left;
            }

            form {
                display: inline;
                margin-right: 10px;
            }

            input[type="submit"] {
                background-color: #4CAF50;
                color: white;
                padding: 5px 10px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }

            input[type="submit"]:hover {
                background-color: #45a049;
            }
        </style>
    </head>
    <body>
        <jsp:include page="../homebutton.jsp"></jsp:include>
            <div class="container">
                <div class="header">
                    
                <h2>List requests</h2>
                </div>
                <table border="1">
                    <thead>
                        <tr>
                            <th>Course</th>
                            <th>From Student</th>
                            <th>From Group</th>
                            <th>To Student</th>
                            <th>To Group</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.requests}" var="request">
                        <tr>
                            <td>${request.fromGroup.course.code}</td>
                            <td>${request.fromStudent.name}</td>
                            <td>${request.fromGroup.name}</td>
                            <td>${request.toStudent.name}</td>
                            <td>${request.toGroup.name}</td>
                            <td>
                                <form action="seerequired" method="post">
                                    <input type="hidden" name="requestId" value="${request.id}">
                                    <input type="hidden" name="action${request.id}" value="accept">
                                    <input type="hidden" name="fromST${request.id}" value="${request.fromStudent.id}">
                                    <input type="hidden" name="fromGr${request.id}" value="${request.fromGroup.id}">
                                    <input type="hidden" name="toSt${request.id}" value="${request.toStudent.id}">
                                    <input type="hidden" name="toGr${request.id}" value="${request.toGroup.id}">
                                    <input type="submit" value="Accept">
                                </form>
                                <form action="seerequired" method="post">
                                    <input type="hidden" name="requestId" value="${request.id}">
                                    <input type="hidden" name="action${request.id}" value="reject">
                                    <input type="submit" value="Reject">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>

        </div>
    </body>
</html>

