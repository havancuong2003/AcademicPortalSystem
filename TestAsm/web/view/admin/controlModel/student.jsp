<%-- 
    Document   : student
    Created on : Mar 3, 2024, 10:17:51 PM
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
                table {
                width: 100%;
                border-collapse: collapse;
            }
            th, td {
                border: 1px solid #dddddd;
                text-align: left;
                padding: 8px;
            }
            th {
                background-color: #f2f2f2;
            }
            /* Styling cho ảnh sinh viên */
            img {
                max-width: 100px;
                height: auto;
            }
        </style>
    </head>
    <body>
        <jsp:include page="../homebutton.jsp"></jsp:include>
            <form action="controlStudent" method="get">
                <input type="text" placeholder="Search Name, StudentID" name="studentInfo">
                <input type="submit" value="Search"/>
            </form>
            <table style="border: 1px solid black">
                <th>NO</th>
                <th>Student ID</th>
                <th>Student Name</th>
                <th>Date of birth</th>
                <th>Image</th>
                <th>Email</th>
                <th>User Name</th>
                <c:forEach items="${requestScope.students}" var="s" varStatus="loop">
                <tr>
                    <td>${loop.index + 1}</td>
                    <td>${s.id}</td>
                    <td>${s.name}</td>
                    <td>${s.dob}</td>
                    <td><img src="../img/${s.imgUrl}" alt="student img"/></td>
                    <td>${s.email}</td>
                    <td>${s.username}</td>
                    <td><button type="submit">Edit</button>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>
