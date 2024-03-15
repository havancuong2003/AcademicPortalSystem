<%-- 
    Document   : checkAttendance
    Created on : Mar 10, 2024, 2:52:25 PM
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
            table {
                width: 100%;
                border-collapse: collapse;
            }
            th, td {
                border: 1px solid #ddd;
                padding: 8px;
                text-align: left;
            }
            th {
                background-color: #f2f2f2;
            }
            tr:nth-child(even) {
                background-color: #f2f2f2;
            }
            .absent {
                background-color: #ffcccc;
            }


            .present {
                background-color: #ccffcc;
            }
            a {
                text-decoration: none;
                color: #333;
                display: inline-block;
                margin-bottom: 10px;
            }
            a:hover {
                color: #007bff;
            }


            .term-link, .course-link {
                padding: 10px 15px;
                border: 1px solid #ccc;
                border-radius: 5px;
                background-color: #f8f9fa;
                transition: all 0.3s ease;
            }


            .term-link:hover, .course-link:hover {
                background-color: #e9ecef;
            }
            #active${requestScope.active}{
                color:red;
            }
            #activecourse${requestScope.activeCourse}{
                color:red;
            }
            a:hover {
                background-color: #45a049;
            }
            .container-header{
                text-align: center;
            }

        </style>
    </head>
    <body>
        <jsp:include page="../homebutton.jsp"></jsp:include>
        <div class="container-header">
            <c:forEach items="${requestScope.term}" var="t"> 
                <a id="active${t.term.id}" href="checkabsent?term=${t.term.id}">
                    <div class="term-link">${t.term.description}</div>

                </a>
            </c:forEach>



        <br/>
     
            <c:forEach items="${requestScope.courseByTerm}" var="c">
                <a id="activecourse${c.course.id}" href="checkabsent?term=${requestScope.termid}&course=${c.course.id}">
                    <div class="course-link">${c.course.code}</div>
                </a>
            </c:forEach>
        </div>


        <br/>
        <br/>
        <br/>
        <br/>
        <table>
            <thead>
            <th>No</th>
            <th>Date</th>
            <th>Slot</th>
            <th>Room</th>
            <th>Lecture</th>
            <th>Group</th>
            <th>Attendance Status</th>
            <th>Lecture comment</th>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.attendance}" var="a" varStatus="loop">
                <tr>
                    <td>${loop.index +1}</td>
                    <td>${a.session.date}</td>
                    <td>${a.session.group.timeslot.id}</td>
                    <td>${a.session.group.room.description}</td>
                    <td>${a.session.teacher.name}</td>
                    <td>${a.session.group.name}</td>
                    <c:choose>
                        <c:when test="${a.status eq true}">
                            <td class="present">Present</td>
                        </c:when>
                        <c:when test="${a.status eq false}">
                            <td class="absent">Absent</td>
                        </c:when>
                        <c:otherwise>
                            <td style="background-color: #ccc;">Future</td>
                        </c:otherwise>
                    </c:choose>


                    <td>${a.description}</td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="7"><h1>ABSENT: ${requestScope.percent}% ABSENT SO FAR (${requestScope.absent} ABSENT ON ${requestScope.total} TOTAL).</h1></td>
            </tr>
        </tbody>

    </table>
</body>
</html>
