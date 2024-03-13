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
</head>
<body>
     <jsp:include page="../homebutton.jsp"></jsp:include>
    <h2>List requests</h2>
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
</body>
</html>

