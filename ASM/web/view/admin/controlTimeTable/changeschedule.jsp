<%-- 
    Document   : changeschedule
    Created on : Mar 9, 2024, 2:30:37 PM
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
            #chosenIndex{
                width: 200px;
                height:40px;
            }
            #chooseTeacher{
                <c:if test="${requestScope.check eq 'hidden'}">
                    display:none;

                </c:if>
            }
            body {
                font-family: Arial, sans-serif;
            }
            .home a {
                color: black;
                font-size: 25px;
                padding: 5px 10px;
                text-decoration: none;
                background-color: #ccc;
                border-radius: 5px;
            }
            .home {
                padding: 10px;
                margin: 25px 0;
            }

            table {
                width: 100%;
                border-collapse: collapse;
            }
            th, td {
                border: 1px solid #ccc;
                padding: 8px;
                text-align: left;
            }
            th {
                background-color: #4CAF50;
                color: white;
            }
            tr:nth-child(even) {
                background-color: #f2f2f2;
            }
            input[type="submit"] {
                background-color: #4CAF50;
                color: white;
                padding: 8px 16px;
                border: none;
                cursor: pointer;
            }
            input[type="submit"]:hover {
                background-color: #45a049;
            }
            .formChange{
                text-align: center;
            }
            
        </style>
    </head>
    <body>



        <div class="home">
            <a href="timetablelecture">Back</a>
        </div>
        <div class="formChange"> 
            <h3>Input infomation want to Change lecture slot</h3>
            <form method="changeschedule" method="get">
            <input type="date" name="date" placeholder="date want to change" required/>
            <input type="number" name="slot" placeholder="slot want to change" required/>
            <input type="submit" value="Find"/>
        </form>
        </div>
       

        <div id="chooseTeacher">

            <h3>
                Choose Teacher to change schedule
            </h3>
            <table border="1">
                <tr>
                    <th>Index</th>
                    <th>Name</th>
                    <th>ID</th>
                </tr>
                <c:forEach items="${requestScope.teachers}" var="teacher" varStatus="loop">
                    <tr>
                        <td>${loop.index + 1}</td>
                        <td>${teacher.name}</td>
                        <td>${teacher.id}</td>
                    <input type="hidden" value="${teacher.id}" name="t${loop.index + 1}"> 
                    </tr>
                </c:forEach>
            </table>
            <form id="changeScheduleForm" action="changeschedule" method="post">
                <input type="number" name="selectTeacher" placeholder="choose index want to change" id="chosenIndex" min="1" max="${requestScope.teachers.size()}"/>
                <input type="hidden" name="date" value="${requestScope.date}"/>
                <input type="hidden" name="slot" value="${requestScope.slot}"/>
                <input type="submit" value="Submit"/>
            </form>
        </div>

</html>
