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
        </style>
    </head>
    <body>

        <c:forEach items="${requestScope.termMark}" var="mark">
            <h1>${mark.term.id}</h1>
            
        </c:forEach>
        
        <h2>${requestScope.test}</h2>
        <h2>Subject Selection</h2>
        <div>
            <c:forEach items="${requestScope.listGroup}" var="group">
                <a href="#" onclick="showMarks('${group.course.code}')">${group.course.code} </a> |

            </c:forEach>

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

//                var mathData = [
//                    {gradeCategory: 'Active Learning', gradeItem: 'Active Learning', weight: '10.0 %', value: '9.8', comment: ''},
//                    {gradeCategory: 'Exercise', gradeItem: 'Exercise 1', weight: '5.0 %', value: '8.5', comment: ''},
//                    {gradeCategory: 'Exercise', gradeItem: 'Exercise 2', weight: '5.0 %', value: '7.5', comment: ''},
//                    {gradeCategory: 'Presentation', gradeItem: 'Presentation', weight: '10.0 %', value: '8.5', comment: ''},
//                    {gradeCategory: 'Project', gradeItem: 'Project', weight: '30.0 %', value: '7', comment: ''},
//                    {gradeCategory: 'Final Exam', gradeItem: 'Final Exam', weight: '40.0 %', value: '', comment: ''},
//                    {gradeCategory: 'Final Exam Resit', gradeItem: 'Final Exam Resit', weight: '40.0 %', value: '', comment: ''},
//                    {gradeCategory: 'COURSE TOTAL', gradeItem: 'AVERAGE', weight: '0.0', value: '', comment: ''},
//                    {gradeCategory: 'COURSE TOTAL', gradeItem: 'STATUS', weight: '', value: '', comment: 'NOT PASSED'}
//                ];


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
                        // Function to show marks based on selected course code
                        function showMarks(courseCode) {
                            var courseData = coursesData.find(course => course.courseName === courseCode);
                            if (courseData) {
                                generateRows(courseData.data);
                            } else {
                                // Handle case when course data is not found
                                console.error("Course data not found for course code: " + courseCode);
                            }
                        }


                        // Generate table rows for initial display
                        generateRows(coursesData[0].data); // Display courseData0 data by default
        </script>

    </body>
</html>
