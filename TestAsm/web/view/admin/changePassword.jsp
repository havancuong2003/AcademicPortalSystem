<%-- 
    Document   : changePassword
    Created on : Mar 4, 2024, 12:28:02 PM
    Author     : -MSI-
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="homebutton.jsp"></jsp:include>

            <form action="changepassword" method="get">
                <h3>Select your role</h3>
                <select name="role">
                    <option value="1">ADMIN</option>
                    <option value="2">Lecture</option>
                    <option value="3">Student</option>

                </select>
                <input type="text" name="id" placeholder="Search by ID" required/>
                <input type="submit" value="Search"/>
            </form>
            <h1 style="color:red">${requestScope.NotFind}</h1>
            <h1 style="color:green">${requestScope.ms}</h1>
        
        <form action="changepassword" method="post">
            <c:if test="${requestScope.account ne null}">
                <input type="hidden" name="username" value="${requestScope.account.username}"/>

                <input type="text" value="${requestScope.account.username}"/>
                <input type="text" name="password" value="${requestScope.account.password}"/>
                <c:choose>
                    <c:when test="${requestScope.account.role == 1}">
                        <input type="text" value="admin" readonly/>
                    </c:when>
                    <c:when test="${requestScope.account.role == 2}">
                        <input type="text" value="lecture" readonly/>
                    </c:when>
                    <c:when test="${requestScope.account.role == 3}">
                        <input type="text" value="student" readonly/>
                    </c:when>
                    <c:otherwise>
                        <input type="text" value="Unknown" readonly/>
                    </c:otherwise>
                </c:choose>

                <input type ="submit" value="Save"/>
            </c:if>

        </form>

    </body>
</html>
