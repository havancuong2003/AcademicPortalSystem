<%-- 
    Document   : Ressult
    Created on : Mar 1, 2024, 8:34:18 PM
    Author     : -MSI-
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Result</title>
    </head>
    <body>
        <center>
            <h3><%=request.getAttribute("Message")%></h3>
        </center>
            <h1>${requestScope.fid}</h1>
            <h1>${requestScope.content}</h1>
        
    </body>
</html>
