<%-- 
    Document   : activityDetail
    Created on : Mar 9, 2024, 10:22:40 AM
    Author     : -MSI-
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            table {
                border-collapse: collapse;
                width: 100%;
            }
            td, th {
                border: 1px solid #dddddd;
                text-align: left;
                padding: 8px;
            }
            tr:nth-child(even) {
                background-color: #f2f2f2;
            }

            a:hover {
                background-color: #45a049;
            }
            .back a {
                color: black;
                font-size: 25px;
                padding: 5px 10px;
                text-decoration: none;
                background-color: #ccc;
            }
            .back {
                padding: 10px;
                border-radius: 5px;
                margin: 25px 0;
            }
        </style>
    </head>
    <body>
        <jsp:include page="../homebutton.jsp"></jsp:include>
        <div class="back">
            <a href="timetable">Back</a>
        </div>
        <div>
            <table>
                <tr align="left">
                    <td>Date</td>
                    <td>${requestScope.infoCourse.session.date}</td>

                </tr>
                <tr align="left">
                    <td>Slot</td>
                    <td>${requestScope.infoCourse.session.group.timeslot.id}</td>

                </tr>
                <tr align="left">
                    <td>Student Group</td>
                    <td><a href="class?id=${requestScope.infoCourse.session.group.id}">${requestScope.infoCourse.session.group.name}</a></td>

                </tr>
                <tr align="left">
                    <td>instructor</td>
                    <td>${requestScope.infoCourse.session.teacher.name}</td>

                </tr>
                <tr align="left">
                    <td>Course</td>
                    <td>${requestScope.infoCourse.session.group.course.description} ( ${requestScope.infoCourse.session.group.course.code} )</td>

                </tr>
                <tr align="left">
                    <td>Course session number</td>
                    <td>${requestScope.infoCourse.slot}</td>

                </tr>
                <tr align="left">
                    <td>Attendance</td>
                    <td>${requestScope.infoCourse.status}</td>

                </tr>
                <tr align="left">
                    <td>Record Time</td>
                    <td>${requestScope.infoCourse.time}</td>

                </tr>
            </table>
        </div>
    </body>
</html>
