<%-- 
    Document   : lecture
    Created on : Mar 11, 2024, 11:47:34 AM
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
            body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 80%;
            margin: 20px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        table {
            width: 100%;
            border-collapse: collapse;
            border-spacing: 0;
        }

        th, td {
            padding: 8px;
            border: 1px solid #ccc;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
            font-weight: bold;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #e0e0e0;
        }
        </style>
    </head>
    <body>
        <jsp:include page="../homebutton.jsp"></jsp:include>
        <div class="container">
            
        
        <table>
            <thead>
            <th>Group name</th>
            <th>Term</th>
            <th>Course name</th>
            <th>Content</th>

        </thead>
        <tbody>
            <c:forEach items="${requestScope.feedbacks}" var="f">
                
                <tr>
                    <td>${f.group.name}</td>
                    <td>${f.group.term.description}</td>
                    <td>${f.group.course.code}</td>
                    <td>${f.content}</td>
                </tr>
            </c:forEach>
        </tbody>

    </table>
            </div>
</body>
</html>
