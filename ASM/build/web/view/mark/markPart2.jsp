<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Grade Information</title>
        <style>
            /* Reset CSS */
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }

            /* Body styles */
            body {
                font-family: Arial, sans-serif;
                background-color: #f7f7f7;
                color: #333;
            }

            /* Container styles */
            .container {
                display: flex;
                max-width: 1200px;
                margin: 0 auto;
            }

            /* Sidebar styles */
            .sidebar {
                width: 250px;
                background-color: #777;
                padding: 20px;
            }

            .sidebar h2 {
                color: #fff;
                margin-bottom: 20px;
            }

            .sidebar ul {
                list-style-type: none;
                padding: 0;
            }

            .sidebar ul li {
                margin-bottom: 10px;
            }

            /* Content styles */
            .content {
                flex: 1;
                padding: 20px;
            }

            .content .grades {
                margin-top: 20px;
            }

            .content .grades h2 {
                margin-bottom: 20px;
            }

            /* Table styles */
            table {
                width: 100%;
                border-collapse: collapse;
                background-color: #fff;
                border-radius: 5px;
                overflow: hidden;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            }

            th, td {
                padding: 10px;
                border-bottom: 1px solid #ddd;
                text-align: left;
            }

            th {
                background-color: #777;
                color: #fff;
            }
            .terms {
                display: inline-block;
                vertical-align: top;
                width: 40%;
                margin:50px;
                text-align: center;

            }
            .terms h3,.courses h3{
                font-size:50px;
            }

            .courses {
                display: inline-block;
                vertical-align: top;
                width: 30%;
                text-align: center;
            }

            .grades {
                <c:if test="${requestScope.checkClick eq 'show'}">
                    display : block;
                </c:if>
                <c:if test="${requestScope.checkClick eq 'hidden'}">
                    display :none;
                </c:if>
                vertical-align: top;
                width: 40%;
            }
         
            #active${requestScope.activeTerm} a,#active${requestScope.activeCourse} a{
                background-color:green;
            }

            li {
                list-style-type: none;
            }
            /* Anchor tag styles */
            /* Anchor tag styles */
          .terms  a,.courses a {
                display: block;
                width: 100%;
                padding: 15px;
                text-decoration: none;
                color: #fff;
                background-color: #777;
                border-radius: 5px;
                text-align: center;
                transition: background-color 0.3s, transform 0.3s;
                font-weight: bold;
                margin:20px;
            }

            a:hover {
                background-color: #45a049;
                transform: translateY(-3px);
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            }
            #gradeInfo{
                text-align: center;
                margin:50px;
                width:100%;
            }
            .containGrade{
                width:1000px;
            }
            .totalMark{
                text-align: left;
            }
            #statusMark{
                color:#45a049;
                <c:if test="${requestScope.status eq 'NOT PASS'}">color:red;</c:if>
            }
          
        </style>
    </head>
    <body>
        <h1>${requestScope.ts}</h1>
        <h1 style="color:red">${requestScope.error}</h1>
        <jsp:include page="../homebutton.jsp"></jsp:include>

        <div class="terms">
            <h3>Terms</h3>
            <ul>
                <c:forEach items="${requestScope.termMark}" var="group">
                    <li id="active${group.term.id}"><a href="mark?termid=${group.term.id}" >${group.term.description}</a></li>
                    </c:forEach>
            </ul>
        </div>

        <div class="courses">
            <h3>Courses</h3>
            <ul id="courseList">
                <c:forEach items="${requestScope.courseByTerm}" var="g">
                    <li id="active${g.course.id}"><a href="mark?termid=${param.termid}&courseid=${g.course.id}&gid=${g.id}" onclick="showMarks()">${g.course.description}</a></li>
                    </c:forEach>


            </ul>
        </div>

        <div class="grades" id="gradeInfo">
            <div class="containGrade">  
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
            <br/>
            <br/>
            <br/>
            <div class="totalMark">
                <h1>
                    COURSE TOTAL: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; AVERAGE &nbsp;&nbsp;:<span>${requestScope.total}</span>
                </h1>
                <br/>
                <h1>
                    &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;   Status &nbsp;&nbsp;&nbsp;<span id="statusMark"> ${requestScope.status}</span> 
                </h1>
                <br/>
                 <h1>
                    &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;<span> ${requestScope.cmt}</span> 
                </h1>
            </div>
        </div>



        <script>


            var courseData = [
            <c:forEach items="${requestScope.markOfCourse}" var="m">
                {
                    coursename: '${m.group.course.code}',
                    gradeCategory: '${m.gradeCategory}',
                    gradeItem: '${m.gradeItem}',
                    weight: '${m.weight}',
                    value: '${m.value}',
                    comment: '${m.comment}'
                },
            </c:forEach>

            ];

            function generateRows(subjectData) {
                var html = '';
                var rowspanCount = 1;
                if (subjectData.length < 2) {
                    html += "<tr><td colspan='5'>No information about " + courseData[0].coursename + "</td></tr>";
                } else {
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
            generateRows(courseData);





        </script>


    </script>

</body>
</html>
