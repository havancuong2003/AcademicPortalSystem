<%-- 
    Document   : lecture
    Created on : Feb 16, 2024, 9:10:59 PM
    Author     : -MSI-
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Điểm danh Sinh viên</title>
        <style>
            /* Styling cho table */
            table {
                width: 100%;
                border-collapse: collapse;
            }
            th, td {
                border: 1px solid #dddddd;
                text-align: left;
                padding: 8px;
            }
            th {
                background-color: #f2f2f2;
            }
            /* Styling cho ảnh sinh viên */
            img {
                max-width: 100px;
                height: auto;
            }
            /* Styling cho ô radio */
            input[type="radio"] {
                margin-right: 10px;
            }
            #submit{
                padding:10px;
                margin:30px 0;
            }
            .footer {
                background-color: #ef9926;
                color: white;
                font-weight: bold;

                font-size: 20px;
                width: 100%;
                height: 100px;
                text-align: center;
                padding: 20px;
                position: relative;
                bottom: 0;
            }
            #submitAtd{
                text-align: center;
            }
            .header-attendance{
                font-size: 50px;
                text-align: center;
            }
            .home a {
                color: rgb(13, 90, 235);
                font-size: 25px;
                padding: 5px 10px;
            }
            .home {
                padding: 10px;
                border-radius: 5px;
                margin: 25px 0;
            }
        </style>
    </head>
    <body>
      
        <div class="container">
            <div class="home">
                <a href="home">Home</a>
            </div>
            <h2 class="header-attendance">Điểm danh Sinh viên</h2>
            <form action="attendance" method="post">
                <table>
                    <thead>
                        <tr>
                            <th>NO</th>
                            <th>Group</th>
                            <th>StudentID</th>
                            <th>Name</th>
                            <th>Image</th>
                            <th>Attendance Status</th>
                            <th>Description</th>
                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach items="${requestScope.list}" var="s" varStatus="loop">
                            <tr>
                                <td>${loop.index + 1}</td>
                                <td>${s.session.group.name}</td>
                                <td>${s.student.id}</td>
                                <td>${s.student.name}</td>                                  

                                <td><img src="../img/${s.student.imgUrl}" alt="Student image"></td>
                                <td>
                                    <c:if test="${s.session.status eq 'true'}">
                                        <label><input type="radio" name="attendance${s.student.id}" value="present" ${s.status eq 'true' ? 'checked' : ''}> Present</label>
                                        <label><input type="radio" name="attendance${s.student.id}" value="absent" ${s.status eq 'false' ? 'checked' : ''}> Absent</label>
                                        </c:if>

                                    <c:if test="${s.session.status eq 'false'}">
                                        <label><input type="radio" name="attendance${s.student.id}" value="present"> Present</label>
                                        <label><input type="radio" name="attendance${s.student.id}" value="absent"> Absent</label>
                                        </c:if>

                                </td>
                                <td>
                                    <input type="text" name="description${s.student.id}" value="${s.description}">
                                </td>

                        <input type="hidden" value="${s.session.id}" name="sessionid"/>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div id="submitAtd">
                    <input type="submit" id ="submit" value ="Save">
                </div>
            </form>


        </div>
        <div class="footer">
            <p>FPT UNIVERSITY</p>
            <span>DAI HOC FPT HA NOI</span>
        </div>
        <script>

        </script>
    </body>
</html>

