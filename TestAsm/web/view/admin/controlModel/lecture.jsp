<%-- 
    Document   : lecture
    Created on : Mar 3, 2024, 10:17:51 PM
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
        </style>
        <script>
            function editStudent(lectureId) {
                var row = document.getElementById(lectureId);
                var cells = row.cells;

                // Loop through each cell (except the first and last one)
                for (var i = 1; i < cells.length - 1; i++) {
                    var cellData = cells[i].innerHTML;
                    // Create an input element
                    var input = document.createElement("input");
                    // Set its type to text
                    input.type = "text";
                    // Set its value to the current cell content
                    input.value = cellData.trim(); // trim() to remove leading/trailing whitespace
                    // Replace the cell content with the input element
                    cells[i].innerHTML = "";
                    cells[i].appendChild(input);
                }

                // Hide the edit button
                cells[cells.length - 2].style.display = "none";
                // Show the save button
                cells[cells.length - 1].style.display = "inline-block";
            }

            function saveStudent(lectureId, event) {
                // Prevent default form submission
                event.preventDefault();

                var row = document.getElementById(lectureId);
                var cells = row.cells;

                // Loop through each cell (except the first and last one)
                for (var i = 1; i < cells.length - 1; i++) {
                    // Extract the input value
                    var inputVal = cells[i].querySelector('input').value;
                    // Replace the input element with its value
                    cells[i].innerHTML = inputVal;



                }

                for (var i = 1; i < cells.length - 1; i++) {
                    // Extract the value from the td element
                    var cellValue = cells[i].innerText.trim();

                    // Find the corresponding hidden input element and update its value
                    var inputName = "lecture" + cells[i].getAttribute("data-field") + "_" + lectureId;
                    var hiddenInput = document.querySelector('input[name="' + inputName + '"]');
                    if (hiddenInput) {
                        hiddenInput.value = cellValue;
                    }
                }

                // Show the edit button
                cells[cells.length - 2].style.display = "inline-block";
                // Hide the save button
                cells[cells.length - 1].style.display = "none";
                row.querySelector('.edit-status').value = "true";
            }

        </script>
    </head>
    <body>
        
        <jsp:include page="../homebutton.jsp"></jsp:include>
        <h1>${requestScope.ss}</h1>
            <form action="controlLecture" method="get">
                <input type="text" placeholder="Search Name, StudentID" name="lectureInfo">
                <input type="submit" value="Search"/>
            </form>
            <form action="controlLecture" method="post">
                <input type="hidden" name="lectureInfo" value="${requestScope.lectureInfo}"/>
                <table style="border: 1px solid black">
                    <th>NO</th>
                    <th>Student ID</th>
                    <th>Student Name</th>
                    <th>Date of birth</th>
                    <th>Email</th>
                    <th>User Name</th>
                    <th>Edit</th>
                    <c:forEach items="${requestScope.lectures}" var="l" varStatus="loop">
                    <tr id="${l.id}">
                        <td>${loop.index + 1}</td>
                       
                    <input type="hidden" name="lectureID_${l.id}" value="${l.id}">
                    <td data-field="ID">${l.id}</td>
                    <td data-field="Name">${l.name}</td>
                    <input type="hidden" name="lectureName_${l.id}" value="${l.name}">
                    <td data-field="DOB">${l.dob}</td>
                    <input type="hidden" name="lectureDOB_${l.id}" value="${l.dob}">
                   
                    <td data-field="Email">${l.email}</td>
                    <input type="hidden" name="lectureEmail_${l.id}" value="${l.email}">
                    <td data-field="Username">${l.username}</td>
                    <input type="hidden" name="lectureUsername_${l.id}" value="${l.username}">
                    <td><button onclick="editStudent('${l.id}')">Edit</button></td>
                    <td style="display: none;"><input type="button" onclick="saveStudent('${l.id}', event)" value="Save"/></td>
                    <input type="hidden" class="edit-status" name="editStatus_${l.id}" value="false"/> <!-- Hidden input to store edit status -->
                    </tr>
                </c:forEach>
            </table>
                <input type="submit" value="Save ALL"/>
        </form>
    </body>
</html>
