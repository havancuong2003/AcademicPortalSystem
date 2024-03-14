<%-- 
    Document   : SendMail
    Created on : Mar 1, 2024, 8:30:55 PM
    Author     : -MSI-
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Send an e-mail</title>
        <script>
            function selectAll() {
                var checkboxes = document.getElementsByName('selectedStudents');
                checkboxes.forEach(function (checkbox) {
                    checkbox.checked = true;
                });
            }
        </script>
    </head>
    <body>



        <form action="emailsending" method="post">



            <table border="1">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Course</th>
                    <th>Select</th>
                </tr>
                <c:forEach var="student" items="${students}">
                    <tr>
                        <td><c:out value="${student.id}" /></td>
                        <td><c:out value="${student.name}" /></td>
                        <td><c:out value="${student.email}" /></td>
                        <td><c:out value="${student.course}" /></td> 
                        <td>
                            <input type="checkbox" name="selectedStudents" value="${student.id}" />
                            <input type="hidden" name="course-${student.id}" value="${student.course}" />
                        </td>
                    </tr>
                </c:forEach>
            </table>

            <input type="button" value="Select All" onclick="selectAll()" /><br/>
            <input type="submit" value="Send Email" />
        </form>


    </body>
</html>
