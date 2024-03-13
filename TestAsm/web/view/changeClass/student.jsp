<%-- 
    Document   : student
    Created on : Mar 12, 2024, 11:15:21 PM
    Author     : -MSI-
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Change Request</title>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script>
            $(document).ready(function () {
                // Mảng để lưu trữ các yêu cầu đã tạo
                var changeRequests = [];

                // Hàm để hiển thị form tạo yêu cầu
                function showRequestForm() {
                    $('#requestForm').show();
                }

                // Hàm để thêm yêu cầu vào bảng
                function addRequestToTable(course, fromStudent, fromGroup, toStudent, toGroup) {
                    // Kiểm tra xem course đã tồn tại trong yêu cầu trước đó chưa
                    var exists = false;
                    $.each(changeRequests, function (index, request) {
                        if (request.course === course) {
                            exists = true;
                            return false; // Thoát khỏi vòng lặp khi tìm thấy course trùng
                        }
                    });

                    if (exists) {
                        alert('Không thể tạo yêu cầu cho cùng một course ' + course + '!');
                        return;
                    }

                    // Thêm yêu cầu vào mảng
                    changeRequests.push({
                        course: course,
                        fromStudent: fromStudent,
                        fromGroup: fromGroup,
                        toStudent: toStudent,
                        toGroup: toGroup
                    });

                    // Hiển thị yêu cầu trên bảng
                    var newRow = '<tr><td>' + course + '</td><td>' + fromStudent + '</td><td>' + fromGroup + '</td><td>' + toStudent + '</td><td>' + toGroup + '</td><td><button class="cancelRequestButton">Hủy</button></td></tr>';
                    $('#changeRequestTable tbody').append(newRow);
                }

                // Sự kiện click cho nút tạo yêu cầu
                $('#createRequestButton').click(function () {
                    showRequestForm();
                });

                // Sự kiện click cho nút thêm yêu cầu
                $('#addRequestButton').click(function (event) {
                    var course = $('#course').val();
                    var fromStudent = $('#fromStudent').val();
                    var fromGroup = $('#fromGroup').val();
                    var toStudent = $('#toStudent').val();
                    var toGroup = $('#toGroup').val();

                    // Kiểm tra xem course đã tồn tại trong yêu cầu trước đó chưa
                    var exists = false;
                    $.each(changeRequests, function (index, request) {
                        if (request.course === course) {
                            exists = true;
                            return false; // Thoát khỏi vòng lặp khi tìm thấy course trùng
                        }
                    });

                    if (exists) {
                        alert('Không thể tạo yêu cầu cho cùng một course ' + course + '!');
                        event.preventDefault(); // Ngăn chặn hành động mặc định của form
                        return;
                    }

                    addRequestToTable(course, fromStudent, fromGroup, toStudent, toGroup);

                    // Gửi dữ liệu tới servlet
                    $.ajax({
                        type: 'POST',
                        url: 'change', // Địa chỉ của servlet nhận dữ liệu
                        data: {
                            course: course,
                            fromStudent: fromStudent,
                            fromGroup: fromGroup,
                            toStudent: toStudent,
                            toGroup: toGroup
                        },
                        success: function (response) {
                            alert('Yêu cầu đã được lưu vào cơ sở dữ liệu.');
                            // Có thể thêm mã để xử lý phản hồi từ servlet nếu cần
                        },
                        error: function (xhr, status, error) {
                            alert('Đã có lỗi xảy ra: ' + error);
                        }
                    });
                });

                // Sự kiện click cho nút Hủy
                $('#changeRequestTable').on('click', '.cancelRequestButton', function () {
                    var row = $(this).closest('tr');
                    var course = row.find('td:eq(0)').text(); // Lấy nội dung cột course của hàng đó
                    var fromStudent = row.find('td:eq(1)').text(); // Lấy nội dung cột fromStudent của hàng đó
                    var fromGroup = row.find('td:eq(2)').text(); // Lấy nội dung cột fromGroup của hàng đó

                    // Gửi dữ liệu tới servlet
                    $.ajax({
                        type: 'POST',
                        url: 'your_servlet_url_here', // Địa chỉ của servlet nhận dữ liệu
                        data: {
                            course: course,
                            fromStudent: fromStudent,
                            fromGroup: fromGroup
                        },
                        success: function (response) {
                            // Xử lý phản hồi từ servlet nếu cần
                            alert('Đã hủy yêu cầu thành công.');
                        },
                        error: function (xhr, status, error) {
                            alert('Đã có lỗi xảy ra: ' + error);
                        }
                    });

                    // Xóa yêu cầu khỏi mảng và bảng
                    changeRequests = changeRequests.filter(function (request) {
                        return request.course !== course;
                    });
                    row.remove();
                });

            <c:forEach items="${requestScope.requireds}" var="request">
                addRequestToTable('${request.fromGroup.course.code}', '${request.fromStudent.name}', '${request.fromGroup.name}', '${request.toStudent.name}', '${request.toGroup.name}');
            </c:forEach>

                // Sự kiện submit cho form
                $('#changeRequestForm').submit(function (event) {
                    // Ngăn chặn hành động mặc định của form
                    event.preventDefault();
                });

            });
        </script>
    </head>
    <body>
        <jsp:include page="../homebutton.jsp"></jsp:include>
            <h2>Change Request</h2>
            <button id="createRequestButton">Tạo Yêu Cầu</button>
            <div id="requestForm" style="display:none;">
                <form id="changeRequestForm" method="post" action="change">
                    <label for="course">Course:</label>
                    <select id="course">
                    <c:forEach items="${requestScope.groups}" var="g" varStatus="loop">
                        <option value="${g.course.code}">${g.course.code}</option>
                    </c:forEach>

                </select><br>
                <label for="fromStudent">From Student:</label>
                <input type="text" id="fromStudent" readonly="true" value="${requestScope.studentid}"><br>
                <label for="fromGroup">From Group:</label>
                <input type="text" id="fromGroup"><br>
                <label for="toStudent">To Student:</label>
                <input type="text" id="toStudent"><br>
                <label for="toGroup">To Group:</label>
                <input type="text" id="toGroup"><br>
                <button id="addRequestButton">Thêm Yêu Cầu</button>
            </form>
        </div>
        <br>
        <table id="changeRequestTable" border="1">
            <thead>
                <tr>
                    <th>Course</th>
                    <th>From Student</th>
                    <th>From Group</th>
                    <th>To Student</th>
                    <th>To Group</th>
                    <th></th> <!-- Cột cho nút Hủy -->
                </tr>
            </thead>
            <tbody>
                <!-- Các yêu cầu sẽ được thêm vào đây -->
            </tbody>
        </table>
    </body>
</html>
