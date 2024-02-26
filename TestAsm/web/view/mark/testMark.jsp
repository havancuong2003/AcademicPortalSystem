<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Grade Information</title>
        <style>
            table, th, td {
                border: 1px solid black;
                border-collapse: collapse;
                padding: 8px;
            }

            a {
                cursor: pointer;
                color: blue;
                text-decoration: underline;
            }

            .terms {
                display: inline-block;
                vertical-align: top;
                width: 20%;
            }

            .courses {
                display: inline-block;
                vertical-align: top;
                width: 30%;
            }

            .grades {
                display: none; /* Ẩn bảng điểm ban đầu */
                vertical-align: top;
                width: 40%;
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

        <div class="home">
            <a href="home">Home</a>
        </div>

        <h2>Subject Selection</h2>
        <div class="terms">
            <h3>Terms</h3>
            <ul>
                <c:forEach items="${requestScope.termMark}" var="group">
                    <li><a href="mark?termid=${group.term.id}" >${group.term.description}</a></li>
                    </c:forEach>
            </ul>
        </div>

        <div class="courses">
            <h3>Courses</h3>
            <ul id="courseList">
                <c:forEach items="${requestScope.courseByTerm}" var="g">
                    <li><a href="#" onclick="showMarks('${g.course.code}')">${g.course.description}</a></li>
                    </c:forEach>

                <c:forEach items="${requestScope.courseByTerm1}" var="g">
                    <li><a href="#" onclick="showMarks('${g.course.code}')">${g.course.description}</a></li>
                    </c:forEach>
            </ul>
        </div>

        <div class="grades" id="gradeInfo">
            <h2>Grade Information</h2>
            <table id="gradeTable">
                <thead>
                    <tr>
                        <th>GRADE CATEGORY</th>
                        <th>GRADE ITEM</th>
                        <th>WEIGHT</th>
                        <th>VALUE</th>
                        <th>COMMENT</th>
                    </tr>
                </thead>
                <tbody id="gradeTableBody">
                    <!-- Grades will be displayed here -->
                </tbody>
            </table>
        </div>

        <script>

            var coursesData = [];

            <c:forEach items="${requestScope.listGroup}" var="group">
                <c:set var="listId" value="list${group.id}" />
            var ${group.course.code}Data = [];

                <c:forEach items="${requestScope[listId]}" var="dataItem">
                    ${group.course.code}Data.push({
                            gradeCategory: '${dataItem.gradeCategory}',
                            gradeItem: '${dataItem.gradeItem}',
                            weight: '${dataItem.weight}',
                            value: '${dataItem.value}',
                            comment: '${dataItem.comment}'
                        });
                </c:forEach>

                        coursesData.push({
                            courseName: '${group.course.code}',
                            data: ${group.course.code}Data
                        });
            </c:forEach>




                        // Function to generate table rows
                        function generateRows(subjectData) {
                            var html = '';
                            var rowspanCount = 1;
                            for (var i = 0; i < subjectData.length; i++) {
                                if (i > 0 && subjectData[i].gradeCategory === subjectData[i - 1].gradeCategory) {
                                    rowspanCount++;
                                } else {
                                    rowspanCount = 1;
                                }
                                html += "<tr>";
                                if (rowspanCount === 1) {
                                    html += "<td rowspan='" + getRowCount(subjectData, i) + "'>" + subjectData[i].gradeCategory + "</td>";
                                }
                                html += "<td>" + subjectData[i].gradeItem + "</td>";
                                html += "<td>" + subjectData[i].weight + "</td>";
                                html += "<td>" + subjectData[i].value + "</td>";
                                html += "<td>" + subjectData[i].comment + "</td>";
                                html += "</tr>";
                            }
                            document.getElementById("gradeTableBody").innerHTML = html;
                        }

                        // Function to calculate the number of rows for rowspan
                        function getRowCount(data, currentIndex) {
                            var count = 1;
                            var currentCategory = data[currentIndex].gradeCategory;
                            for (var i = currentIndex + 1; i < data.length; i++) {
                                if (data[i].gradeCategory === currentCategory) {
                                    count++;
                                } else {
                                    break;
                                }
                            }
                            return count;
                        }

                        // Function to show marks based on selected course code
                        function showMarks(courseCode) {

                            var courseData = coursesData.find(course => course.courseName === courseCode);
                            if (courseData) {
                                generateRows(courseData.data);
                                document.getElementById('gradeInfo').style.display = 'block'; // Hiển thị bảng điểm khi chọn môn học
                            } else {
                                // Handle case when course data is not found
                                console.error("Course data not found for course code: " + courseCode);
                            }
                        }

        </script>

    </body>
</html>
