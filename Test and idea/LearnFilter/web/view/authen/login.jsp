<%-- 
    Document   : login
    Created on : Feb 26, 2024, 1:35:44 PM
    Author     : -MSI-
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h3 style="color:red">${requestScope.ms}</h3>
         <form action="login" method="post">
            Username: <input type="text" name="username"/> <br/>
            Password: <input type="password" name="password"/> <br/>
            <input type="submit" value="Login"/>

        </form>
    </body>
</html>
