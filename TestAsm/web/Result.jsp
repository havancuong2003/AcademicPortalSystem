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

        <h1>${requestScope.idhe1}</h1>
        <h1>${requestScope.namehe1}</h1>
        <h1>${requestScope.dobhe1}</h1>
        <h1>${requestScope.imghe1}</h1>
        <h1>${requestScope.emailhe1}</h1>
        <h1>${requestScope.usernamehe1}</h1>
        <h1>${requestScope.valuehe1} a</h1>
        <h1>${requestScope.s} b</h1>
        <h1>${requestScope.donehe1} b</h1>
        <br/> <br/> <br/>
        
        <h1>Second</h1>
         <br/>
          <br/> <br/>
          
         <h1>${requestScope.idhe10}</h1>
        <h1>${requestScope.namehe10}</h1>
        <h1>${requestScope.dobhe10}</h1>
        <h1>${requestScope.imghe10}</h1>
        <h1>${requestScope.emailhe10}</h1>
        <h1>${requestScope.usernamehe10}</h1>
        <h1>${requestScope.valuehe10} a</h1>
        <h1>${requestScope.s} b</h1>
        <h1>${requestScope.donehe10} b</h1>
    </body>
</html>
