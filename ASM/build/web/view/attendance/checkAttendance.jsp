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
                background-color: #ffcccc; /* Hoặc bất kỳ màu đỏ nào khác bạn muốn */
            }

            /* Màu cho present (màu xanh lá) */
            .present {
                background-color: #ccffcc; /* Hoặc bất kỳ màu xanh lá nào khác bạn muốn */
            }
            a {
                text-decoration: none; /* Loại bỏ gạch chân mặc định */
                color: #333; /* Màu chữ mặc định */
                display: inline-block; /* Hiển thị dạng block để có thể thiết lập margin và padding */
                margin-bottom: 10px; /* Khoảng cách dưới giữa các liên kết */
            }
            a:hover {
                color: #007bff; /* Màu chữ khi di chuột qua */
            }

            /* Thiết lập kiểu cho các liên kết được bọc bởi thẻ div */
            .term-link, .course-link {
                padding: 10px 15px; /* Padding cho nút */
                border: 1px solid #ccc; /* Viền */
                border-radius: 5px; /* Độ cong viền */
                background-color: #f8f9fa; /* Màu nền */
                transition: all 0.3s ease; /* Hiệu ứng chuyển đổi mượt mà */
            }

            /* Kiểu cho các liên kết khi di chuột qua */
            .term-link:hover, .course-link:hover {
                background-color: #e9ecef; /* Màu nền khi di chuột qua */
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

        </style>
    </head>
    <body>
        <jsp:include page="../homebutton.jsp"></jsp:include>
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
