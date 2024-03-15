<%-- 
    Document   : viewGradeStudentByLecture
    Created on : Mar 5, 2024, 1:08:40 PM
    Author     : -MSI-
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Academic Transcript</title>
        <style>
            body {
                font-family: Arial, sans-serif;
            }

            table {
                width: 100%;
                border-collapse: collapse;
            }

            th, td {
                border: 1px solid #ccc;
                padding: 8px;
                text-align: left;
            }

            th {
                background-color: #4CAF50;
                color: white;
            }

            tr:nth-child(even) {
                background-color: #f2f2f2;
            }

            input[type="submit"] {
                background-color: #4CAF50;
                color: white;
                padding: 8px 16px;
                border: none;
                cursor: pointer;
            }

            input[type="submit"]:hover {
                background-color: #45a049;
            }
            .header{
                text-align: center;
            }
        </style>
    </head>
    <body>
        <jsp:include page="../homebutton.jsp"></jsp:include>
        <div class="header"> <h1>Academic Transcript</h1></div>
           
            <div class="container">
                <table border="1">
                    <thead>
                        <tr>
                            <th>Term</th>
                            <th>Semester</th>
                            <th>Course Code</th>
                            <th>Course Name</th>
                            <th>Total</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="infoMark" items="${infoMarks}">
                        <c:set var="infoArray" value="${fn:split(infoMark, ';')}" />

                        <tr>
                            <td>${infoArray[0]}</td>
                            <td>${infoArray[1]}</td>
                            <td>${infoArray[2]}</td>
                            <td>${infoArray[3]}</td>
                            <td>${infoArray[4]}</td>
                            <td>${infoArray[5]}</td>
                        </tr>

                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>

