<%-- 
    Document   : studentUpdate
    Created on : Mar 11, 2024, 11:28:36 AM
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
            .container {
                width: 80%; /* Adjust as needed */
                margin: 0 auto; /* Center the container */
            }

            /* Apply styles to the table */
            table {
                border-collapse: collapse; /* Collapse border spacing */
                width: 100%; /* Table takes full width */
            }

            /* Apply styles to table headers */
            th {
                background-color: #f2f2f2; /* Light gray background */
                border: 1px solid #dddddd; /* Add border */
                padding: 8px; /* Add padding */
                text-align: left; /* Align text to the left */
            }

            /* Apply styles to table cells */
            td {
                border: 1px solid #dddddd; /* Add border */
                padding: 8px; /* Add padding */
                text-align: left; /* Align text to the left */
            }

            /* Style the textarea */
            textarea {
                width: 100%; /* Make the textarea fill its container */
                height: 150px; /* Set a fixed height */

                font-size: 14px; /* Adjust font size */
                resize: vertical; /* Allow vertical resizing */
            }
        </style>
    </head>
    <body>
        <jsp:include page="../homebutton.jsp"></jsp:include>
        <div class="container">
            <form action="updatefeedback" method="post">


                <table>
                    <thead>
                    <th>Content</th>
                    <th>Lecture</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.feedbacks}" var="f">
                            <tr>
                                <td><textarea id="content" name="content${f.id}" rows="10" cols="50">${f.content}</textarea></td>
                                <td>${f.group.teacher.name}</td>
                            </tr>
                        
                        
                        </c:forEach>
                    </tbody>
                </table>
                
                <input type="submit" value="save"/>
            </form>
        </div>
    </body>
</html>
