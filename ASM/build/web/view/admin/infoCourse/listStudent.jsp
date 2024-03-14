<%-- 
    Document   : listStudent
    Created on : Mar 9, 2024, 1:44:27 PM
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
        .student-table {
    border-collapse: collapse;
    width: 100%;
}

.student-table th, .student-table td {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}

.student-table tr:nth-child(even) {
    background-color: #f2f2f2;
}

.student-image {
    width: 50px;
    height: 50px;
    object-fit: cover;
}

    </style>
</head>
<body>
   <jsp:include page="../../homebutton.jsp"></jsp:include>
    <table class="student-table">
        <thead>
            <th>Index</th>
            <th>Image</th>
            <th>Member</th>
            <th>Name</th>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.students}" var="s" varStatus="loop">
                <tr>
                    <td>${loop.index + 1}</td>
                    <td><img src="../img/${s.imgUrl}" class="student-image" /></td>
                    <td>${s.id}</td>
                    <td>${s.name}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
