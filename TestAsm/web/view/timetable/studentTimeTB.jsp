<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .body {
                margin: 0;
                padding: 0;
            }

            /* Add your CSS styles here */
            table {
                width: 100%;
                border-collapse: collapse;
            }

            .no-timetable {
                margin-top: 30px;
                text-align: center;
                font-size: 30px;
            }

            #header-timetable {
                text-align: center;
                font-size: 50px;
            }

            th,
            td {
                border: 1px solid black;
                padding: 10px;
                text-align: center;
            }

            th {
                background-color: #f2f2f2;
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

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
            $(document).ready(function () {
                $('#year').change(function () {
                    // Automatically submit the form when the select element changes
                    $('#myForm').submit();
                });

                $('#week').change(function () {
                    // Automatically submit the form when the select element changes
                    $('#myForm').submit();
                });
            });
        </script>

    </head>
    <body>
        <h1>${sessionScope.test} hjhj</h1>
        <h1>${requestScope.abc}</h1>
        <h1>${sessionScope.dropDownYear} aa</h1>
        <h1>${sessionScope.dropDownWeek} bb</h1>
        <div class="home">
            <a href="/testasm/student/home">Home</a>
        </div>
        <div>
            <h2 id="header-timetable">Student Time Table</h2>
        </div>


        <table border="1px">
            <tr>
                <td>
                    <div>
                        <form action ="timetable" id="myForm" method="post">
                            <label for="year">Select a year:</label>
                            <select id="year" name="dropdownYear">
                                <option value="2022" <c:if test="${sessionScope.dropDownYear eq '2022'}">selected</c:if>>2022</option>
                                <option value="2023" <c:if test="${sessionScope.dropDownYear eq '2023'}">selected</c:if>>2023</option>
                                <option value="2024" <c:if test="${sessionScope.dropDownYear eq '2024'}">selected</c:if>>2024</option>
                                <option value="2025" <c:if test="${sessionScope.dropDownYear eq '2025'}">selected</c:if>>2025</option>
                                </select>
                                <label for="week">Select a week:</label>
                                <select id="week" name="dropdownWeek"></select>
                            </form>
                        </div>


                    </td>
                <c:forEach items="${requestScope.list}" var="d">
                    <td>
                        (<fmt:formatDate pattern="E" value="${d.session.date}" />)
                ${d.session.date}</td>
            </c:forEach>
    </tr>
    <c:forEach items="${requestScope.slots}" var="slot">
        <tr>
            <td>${slot.description}  </td>
            <c:forEach items="${requestScope.list}" var="d">
                <td>
                    a
                    <c:if test="${ d.session.group.timeslot.id eq slot.id}">
                        ${les.group.name} - ${les.group.subject.name} aaa
                        <a href="att?id=${les.id}">Take</a>
                    </c:if>

                </td>
            </c:forEach>
        </tr>
    </c:forEach>
</table> 


<div id="timetable"></div>

<script>
    var yearElement = document.querySelector("#year");
    var weekElement = document.querySelector("#week");


    function updateWeeks() {
        var selectedYear = yearElement.value;
        var weeksInYear = getWeeksInYear(selectedYear);

        // Clear existing options
        weekElement.innerHTML = "";

        // Populate weeks
        for (var i = 1; i <= weeksInYear; i++) {
            var weekRange = getWeekRange(selectedYear, i);
            var option = document.createElement("option");

            var dropDownWeekValue = ${sessionScope.dropDownWeek};

            console.log(dropDownWeekValue);
            var isSelected = (dropDownWeekValue === i); // So sánh giá trị của biến JavaScript và biến JSP
            if (isSelected) {
                option.selected = true;
            }
            option.value = i;
            option.text =
                    "Week " +
                    i +
                    ": " +
                    ("0" + weekRange.start.getDate()).slice(-2) +
                    "-" +
                    ("0" + (weekRange.start.getMonth() + 1)).slice(-2) +
                    " to " +
                    ("0" + weekRange.end.getDate()).slice(-2) +
                    "-" +
                    ("0" + (weekRange.end.getMonth() + 1)).slice(-2);
            weekElement.add(option);

            // Create and append hidden input for start date
            var startDate = document.createElement("input");
            startDate.type = "hidden";
            startDate.name = "startDate"; // Set the name attribute if needed

            // Set start date to the first day of the week
            var firstDayOfWeek = new Date(weekRange.start);
            firstDayOfWeek.setDate(firstDayOfWeek.getDate() - firstDayOfWeek.getDay() + 1); // Set to Monday of the week
            var year = firstDayOfWeek.getFullYear(); // Get the year of the start date
            var month = firstDayOfWeek.getMonth() + 1; // Get the month of the start date
            var day = firstDayOfWeek.getDate(); // Get the day of the start date

            // Format the start date as yyyy-mm-dd
            startDate.value = year + "-" + (month < 10 ? "0" : "") + month + "-" + (day < 10 ? "0" : "") + day;

            weekElement.appendChild(startDate);

            // Create hidden input for end date
            var endDate = document.createElement("input");
            endDate.type = "hidden";
            endDate.name = "endDate"; // Set the name attribute if needed
            // Set end date to the last day of the week
            var lastDayOfWeek = new Date(weekRange.end);
            lastDayOfWeek.setDate(lastDayOfWeek.getDate() + (7 - lastDayOfWeek.getDay())); // Set to Sunday of the week
            year = lastDayOfWeek.getFullYear(); // Get the year of the end date
            month = lastDayOfWeek.getMonth() + 1; // Get the month of the end date
            day = lastDayOfWeek.getDate(); // Get the day of the end date

            // Format the end date as yyyy-mm-dd
            endDate.value = year + "-" + (month < 10 ? "0" : "") + month + "-" + (day < 10 ? "0" : "") + day;

            weekElement.appendChild(endDate);

        }
    }
    // Function to get the week number from a date
    Date.prototype.getWeek = function () {
        var date = new Date(this.getTime());
        date.setHours(0, 0, 0, 0);
        date.setDate(date.getDate() + 3 - ((date.getDay() + 6) % 7));
        var week1 = new Date(date.getFullYear(), 0, 4);
        return (
                1 +
                Math.round(
                        ((date.getTime() - week1.getTime()) / 86400000 -
                                3 +
                                ((week1.getDay() + 6) % 7)) /
                        7
                        )
                );
    };

    // Function to get the number of weeks in a year
    function getWeeksInYear(year) {
        var date = new Date(year, 11, 31);
        var week = date.getWeek();
        return week === 1 ? 52 : week;
    }

    // Function to get the range of dates for a given week in a year
    function getWeekRange(year, weekNumber) {
        var firstDayOfYear = new Date(year, 0, 1);
        var daysToFirstMonday = (8 - firstDayOfYear.getDay()) % 7;
        var firstMondayOfYear = new Date(year, 0, 1 + daysToFirstMonday);

        var startOfWeek = new Date(firstMondayOfYear);
        startOfWeek.setDate(startOfWeek.getDate() + (weekNumber - 1) * 7);

        var endOfWeek = new Date(startOfWeek);
        endOfWeek.setDate(endOfWeek.getDate() + 6);

        return {
            start: startOfWeek,
            end: endOfWeek
        };
    }

    // Initialize weeks when the page loads
    updateWeeks();

    // Update weeks when the year changes
    yearElement.addEventListener("change", updateWeeks);



</script>



</body>
</html>
