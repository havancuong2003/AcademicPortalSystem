<%-- 
    Document   : lecture
    Created on : Feb 9, 2024, 9:38:34 PM
    Author     : -MSI-
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>${requestScope.test}</h1>
        <c:forEach items="${requestScope.list}" var="s" >
            <h1>
                ${s.id}
            </h1>
        </c:forEach>
    </body>
</html>
