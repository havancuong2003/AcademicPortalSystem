<%-- 
    Document   : lecture
    Created on : Feb 28, 2024, 1:47:39 PM
    Author     : -MSI-
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Grade Entry - Lecture</title>
        <style>
            /* CSS styles here */
            body {
                font-family: Arial, sans-serif;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
            }
            table th, table td {
                border: 1px solid #ddd;
                padding: 8px;
                text-align: left;
            }
            table th {
                background-color: #f2f2f2;
            }
            table tr:nth-child(even) {
                background-color: #f2f2f2;
            }
            form {
                margin-bottom: 20px;
            }
            form label {
                display: block;
                margin-bottom: 5px;
            }
            form input[type="number"] {
                width: 80px;
                padding: 5px;
                border: 1px solid #ddd;
                border-radius: 4px;
            }
            form button {
                background-color: #4CAF50;
                color: white;
                border: none;
                padding: 10px 20px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                border-radius: 4px;
                cursor: pointer;
            }
            form button:hover {
                background-color: #45a049;
            }
            a:hover {
                background-color: #45a049;
            }
            .home a {
                color: black;
                font-size: 25px;
                padding: 5px 10px;
                text-decoration: none;
                background-color: #ccc;
            }
            .home {
                padding: 10px;
                border-radius: 5px;
                margin: 25px 0;
            }
        </style>
    </head>
    <body>
        <div class="home">
            <a href="mark">Back</a>
        </div>
        <div class="container">

            <h1>Grade Entry</h1>
            <h2>Grade Item: ${requestScope.gradeItem}</h2>
            <form action="updatemarkofcourse" method="post">
                <table>
                    <thead>
                        <tr>
                            <th>Student ID</th>
                            <th>Student Name</th>
                            <th>Grade</th>
                            <th>Comment</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.listStudentByCourse}" var="s">

                            <tr>
                                <td>${s.student.id}</td>
                                <td>${s.student.name} </td>
                                <td><input type="number" value="${s.value}" name="value${s.student.id}" min="0" max="100" required></td>
                                <td><input type="text" value="${s.comment}"/></td>
                        <input type="hidden" name="gradeitem" value="${s.gradeItem}"/>
                        <input type="hidden" name="groupid" value="${s.group.id}"/>
                        </tr>
                    </c:forEach>


                    </tbody>
                </table>
                <input type="submit">Submit Grades</input>
            </form>
        </div>
    </body>
</html>

