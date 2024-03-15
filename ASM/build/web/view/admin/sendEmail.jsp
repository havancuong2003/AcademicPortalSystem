<%-- 
    Document   : sendEmail
    Created on : Mar 3, 2024, 8:43:10 PM
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
        <style>
            body {
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 0;
  background-color: #f2f2f2;
}

.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

h2 {
  font-size: 2rem;
  font-weight: bold;
  color: #333;
  margin-bottom: 20px;
}

table {
  width: 100%;
  border-collapse: collapse;
  border-spacing: 0;
  background-color: #fff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

th,
td {
  padding: 15px;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

th {
  font-weight: bold;
  color: #333;
}

input[type="checkbox"] {
  margin-right: 10px;
}

input[type="button"],
input[type="submit"] {
  background-color: #4CAF50;
  color: #fff;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  margin-top: 20px;
}

input[type="button"]:hover,
input[type="submit"]:hover {
  background-color: #45a049;
}
        </style>
    </head>
    <body>

        <jsp:include page="../homebutton.jsp"></jsp:include>
            <div class="container">
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
                            <td><c:out value="${student.student.id}" /></td>
                            <td><c:out value="${student.student.name}" /></td>
                            <td><c:out value="${student.student.email}" /></td>
                            <td><c:out value="${student.course.code}" /></td> 
                            <td>
                                <input type="checkbox" name="selectedStudents" value="${student.student.id}" />
                                <input type="hidden" name="course-${student.student.id}" value="${student.course.code}" />
                                <input type="hidden" name="courseid" value="${student.course.id}" />
                            </td>
                        </tr>
                    </c:forEach>
                </table>

                <input type="button" value="Select All" onclick="selectAll()" /><br/>
                <input type="submit" value="Send Email" />
            </form>
        </div>



    </body>
</html>
