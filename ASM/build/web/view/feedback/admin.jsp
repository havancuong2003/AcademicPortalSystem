<%-- 
    Document   : admin
    Created on : Mar 11, 2024, 9:46:36 PM
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
            body {
                font-family: Arial, sans-serif;
                background-color: #f0f0f0;
                margin: 0;
                padding: 0;
            }

            .container {
                width: 80%;
                margin: 20px auto;
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            table {
                width: 100%;
                border-collapse: collapse;
                border-spacing: 0;
            }

            th, td {
                padding: 8px;
                border: 1px solid #ccc;
                text-align: left;
            }

            th {
                background-color: #f2f2f2;
                font-weight: bold;
            }

            tr:nth-child(even) {
                background-color: #f9f9f9;
            }

            tr:hover {
                background-color: #e0e0e0;
            }
            .color1 {
                background-color: #FFE4E1;
            } /* Màu Pink */
            .color2 {
                background-color: #98FB98;
            } /* Màu Light Green */
            .color3 {
                background-color: #ADD8E6;
            } /* Màu Light Blue */
        </style>
    </head>
    <body>
        <jsp:include page="../homebutton.jsp"></jsp:include>
            <div class="container">


                <table>
                    <thead>
                    <th>Group name</th>
                    <th>Student</th>
                    <th>Term</th>
                    <th>Course name</th>
                    <th>Lecture</th>
                    <th>Content</th>

                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.feedbacks}" var="f">

                        <tr>
                            <td>${f.group.name}</td>
                            <td>${f.student.name}</td>
                            <td>${f.group.term.description}</td>
                            <td>${f.group.course.code}</td>
                            <td>${f.group.teacher.name}</td>
                            <td>${f.content}</td>
                        </tr>
                    </c:forEach>
                </tbody>

            </table>
        </div>
        <script>
            // JavaScript code

            // Lấy tất cả các hàng trong bảng
            var rows = document.querySelectorAll('tbody tr');

            // Định nghĩa một đối tượng để lưu trữ màu của từng course
            var courseColors = {};

            // Lặp qua từng hàng trong bảng
            rows.forEach(function (row) {
                // Lấy giá trị của "Course name" trong từng hàng
                var courseName = row.querySelector('td:nth-child(4)').textContent;

                // Kiểm tra xem courseName đã được map với một màu chưa
                if (!courseColors.hasOwnProperty(courseName)) {
                    // Nếu chưa, chọn ngẫu nhiên một màu và gán cho courseName
                    var randomColor = getRandomColor();
                    courseColors[courseName] = randomColor;
                }

                // Thêm màu cho hàng
                row.style.backgroundColor = courseColors[courseName];
            });

            // Hàm để tạo màu ngẫu nhiên
           function getRandomColor() {
           var colors = ['#FFE4E1', '#98FB98', '#ADD8E6', '#FFD700', '#87CEEB', '#FFA07A', '#FF6347', '#00FFFF', '#FF00FF', '#FFFF00',
                  '#B0E0E6', '#FFC0CB', '#7FFFD4', '#F08080', '#00FA9A', '#DDA0DD', '#20B2AA', '#AFEEEE', '#FA8072', '#FFEFD5']; 
            var randomIndex = Math.floor(Math.random() * colors.length); // Chọn ngẫu nhiên một index trong mảng màu
            return colors[randomIndex]; // Trả về màu ứng với index được chọn ngẫu nhiên
        }

        </script>

    </body>
</html>

