<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        </style>
    </head>
    <body>

        <h2>Subject Selection</h2>
        <div>
            <a href="#" onclick="showMarks('Math')">Math</a> |
            <a href="#" onclick="showMarks('English')">English</a> |
            <a href="#" onclick="showMarks('Science')">Science</a>
        </div>

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

        <script>

            var coursesData = [];

            <c:forEach items="${requestScope.list}" var="course">
            var ${course.course.id}Data = [];
                <c:forEach items="${course.data}" var="dataItem">
                    ${course.course.id}Data.push({
                            gradeCategory: '${dataItem.gradeCategory}',
                            gradeItem: '${dataItem.gradeItem}',
                            weight: '${dataItem.weight}',
                            value: '${dataItem.value}',
                            comment: '${dataItem.comment}'
                        });
                </c:forEach>

                        coursesData.push({
                            courseName: '${course.course.id}',
                            data: ${course.course.id}Data
                        });
            </c:forEach>
//                        var mathData = [
//                            {gradeCategory: 'Active Learning', gradeItem: 'Active Learning', weight: '10.0 %', value: '9.8', comment: ''},
//                            {gradeCategory: 'Exercise', gradeItem: 'Exercise 1', weight: '5.0 %', value: '8.5', comment: ''},
//                            {gradeCategory: 'Exercise', gradeItem: 'Exercise 2', weight: '5.0 %', value: '7.5', comment: ''},
//                            {gradeCategory: 'Presentation', gradeItem: 'Presentation', weight: '10.0 %', value: '8.5', comment: ''},
//                            {gradeCategory: 'Project', gradeItem: 'Project', weight: '30.0 %', value: '7', comment: ''},
//                            {gradeCategory: 'Final Exam', gradeItem: 'Final Exam', weight: '40.0 %', value: '', comment: ''},
//                            {gradeCategory: 'Final Exam Resit', gradeItem: 'Final Exam Resit', weight: '40.0 %', value: '', comment: ''},
//                            {gradeCategory: 'COURSE TOTAL', gradeItem: 'AVERAGE', weight: '0.0', value: '', comment: ''},
//                            {gradeCategory: 'COURSE TOTAL', gradeItem: 'STATUS', weight: '', value: '', comment: 'NOT PASSED'}
//                        ];
//
//                        // Sample data for English
//                        var englishData = [
//                            {gradeCategory: 'Active Learning', gradeItem: 'Active Learning', weight: '10.0 %', value: '9.5', comment: ''},
//                            {gradeCategory: 'Homework', gradeItem: 'Homework 1', weight: '10.0 %', value: '8.0', comment: ''},
//                            {gradeCategory: 'Homework', gradeItem: 'Homework 2', weight: '10.0 %', value: '7.5', comment: ''},
//                            {gradeCategory: 'Quiz', gradeItem: 'Quiz 1', weight: '20.0 %', value: '8.5', comment: ''},
//                            {gradeCategory: 'Quiz', gradeItem: 'Quiz 2', weight: '20.0 %', value: '9.0', comment: ''},
//                            {gradeCategory: 'Final Exam', gradeItem: 'Final Exam', weight: '30.0 %', value: '', comment: ''},
//                            {gradeCategory: 'Final Exam Resit', gradeItem: 'Final Exam Resit', weight: '30.0 %', value: '', comment: ''},
//                            {gradeCategory: 'COURSE TOTAL', gradeItem: 'AVERAGE', weight: '0.0', value: '', comment: ''},
//                            {gradeCategory: 'COURSE TOTAL', gradeItem: 'STATUS', weight: '', value: '', comment: 'PASSED'}
//                        ];
//
//                        // Sample data for Science
//                        var scienceData = [
//                            {gradeCategory: 'Lab Work', gradeItem: 'Lab Report 1', weight: '20.0 %', value: '9.0', comment: ''},
//                            {gradeCategory: 'Lab Work', gradeItem: 'Lab Report 2', weight: '20.0 %', value: '8.5', comment: ''},
//                            {gradeCategory: 'Midterm Exam', gradeItem: 'Midterm Exam', weight: '30.0 %', value: '8.0', comment: ''},
//                            {gradeCategory: 'Final Exam', gradeItem: 'Final Exam', weight: '30.0 %', value: '', comment: ''},
//                            {gradeCategory: 'COURSE TOTAL', gradeItem: 'AVERAGE', weight: '0.0', value: '', comment: ''},
//                            {gradeCategory: 'COURSE TOTAL', gradeItem: 'STATUS', weight: '', value: '', comment: 'PASSED'}
//                        ];

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

                        // Function to show marks based on selected subject
                        function showMarks(subject) {
                            if (subject === "Math") {
                                generateRows(mathData);
                            } else if (subject === "English") {
                                generateRows(englishData);
                            } else if (subject === "Science") {
                                generateRows(scienceData);
                            }
                        }

                        // Generate table rows for initial display
                        generateRows(mathData); // Display Math data by default
        </script>

    </body>
</html>
