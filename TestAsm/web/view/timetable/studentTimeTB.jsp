<%-- 
    Document   : studentTimeTB
    Created on : Feb 16, 2024, 11:07:29 AM
    Author     : -MSI-
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
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
            font-size: 20px;
            padding: 10px;
        }
        .home {
            padding: 10px;
            border-radius: 5px;
            margin: 25px 0;
        }

    </style>
    <body>
        <h1>${requestScope.test1} aaa</h1>
        <div class="home">
            <a href="../index.html">Home</a>
        </div>
        <div>
            <h2 id="header-timetable">Student Time Table</h2>
        </div>
        <div>
            <label for="year">Select Year:</label>
            <select id="year">
                <option value="2022">2022</option>
                <option value="2023" selected>2023</option>
                <option value="2024">2024</option>
                <option value="2025">2025</option>
            </select>
            <label for="week">Select Week:</label>
            <select id="week"></select>
        </div>
        <div id="timetable"></div>

        <script>
            var yearElement = document.querySelector("#year");
            var weekElement = document.querySelector("#week");
            var timetableContainer = document.getElementById("timetable");

            function updateWeeks() {
                var selectedYear = yearElement.value;
                var weeksInYear = getWeeksInYear(selectedYear);

                // Clear existing options
                weekElement.innerHTML = "";

                // Populate weeks
                for (var i = 1; i <= weeksInYear; i++) {
                    var weekRange = getWeekRange(selectedYear, i);
                    var option = document.createElement("option");
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
                }

                // Update timetable when weeks change
                updateTimetable();
            }

            function updateTimetable() {
                var selectedYear = yearElement.value;
                var selectedWeek = weekElement.value;
                var weekRange = getWeekRange(selectedYear, selectedWeek);

                // Clear existing timetable
                timetableContainer.innerHTML = "";

                // Render timetable for selected week
                var timetableData = getTimetableData(selectedYear, selectedWeek);
                if (timetableData.length === 0) {
                    timetableContainer.innerHTML = `<div class="no-timetable">No timetable available for the selected week and year</div>`;
                    return;
                }

                var table = document.createElement("table");
                var headerRow = table.insertRow();
                for (let key of [
                    "Date",
                    "Time Slot",
                    "Lecture",
                    "Course",
                    "Group",
                    "Room",
                    "Attendance Status"
                ]) {
                    const headerCell = document.createElement("th");
                    headerCell.textContent = key;
                    headerRow.appendChild(headerCell);
                }

                for (let entry of timetableData) {
                    const row = table.insertRow();
                    for (let key of [
                        "date",
                        "timeSlot",
                        "lecture",
                        "course",
                        "group",
                        "room",
                        "attendanceStatus"
                    ]) {
                        const cell = row.insertCell();
                        cell.textContent = entry[key];
                    }
                }

                timetableContainer.appendChild(table);
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

            // Sample timetable data
            function getTimetableData(year, week) {

                const timetableData = [];
            <c:forEach items="${requestScope.list}" var="a">
                var timetable = {
                    date: "${a.session.date}",
                    timeSlot: "${a.session.timeSlot.description}",
                    lecture: "${a.session.teacher.name}",
                    course: "${a.session.group.course.name}",
                    group: "${a.session.group.name}",
                    room: "${a.session.room.description}",
                    attendanceStatus: "${a.status}"
                };
                timetableData.push(timetable);
            </c:forEach>
//                const timetableData = [
//                    {
//                        date: "2023-01-03",
//                        timeSlot: "8:00 - 9:00",
//                        lecture: "Dr. Smith",
//                        course: "Math",
//                        group: "A",
//                        room: "101",
//                        attendanceStatus: "Present"
//                    },
//                    {
//                        date: "2023-09-04",
//                        timeSlot: "8:00 - 9:00",
//                        lecture: "Dr. Smith",
//                        course: "Math",
//                        group: "A",
//                        room: "101",
//                        attendanceStatus: "Present"
//                    },
//                    {
//                        date: "2023-09-09",
//                        timeSlot: "9:00 - 10:00",
//                        lecture: "Prof. Johnson",
//                        course: "Science",
//                        group: "B",
//                        room: "102",
//                        attendanceStatus: "Absent"
//                    },
//                    // Add more entries for different dates, times, and courses
//                    {
//                        date: "2023-09-11", // Ngày của tuần 2
//                        timeSlot: "10:00 - 11:00",
//                        lecture: "Dr. Brown",
//                        course: "English",
//                        group: "C",
//                        room: "103",
//                        attendanceStatus: "Present"
//                    },
//                    {
//                        date: "2023-09-11", // Ngày của tuần 2
//                        timeSlot: "11:00 - 12:00",
//                        lecture: "Prof. White",
//                        course: "History",
//                        group: "D",
//                        room: "104",
//                        attendanceStatus: "Present"
//                    },
//                    {
//                        date: "2023-09-18", // Ngày của tuần 3
//                        timeSlot: "13:00 - 14:00",
//                        lecture: "Dr. Lee",
//                        course: "Physics",
//                        group: "E",
//                        room: "105",
//                        attendanceStatus: "Absent"
//                    },
//                    {
//                        date: "2023-09-25", // Ngày của tuần 4
//                        timeSlot: "14:00 - 15:00",
//                        lecture: "Prof. Black",
//                        course: "Chemistry",
//                        group: "F",
//                        room: "106",
//                        attendanceStatus: "Present"
//                    },
//                    {
//                        date: "2023-10-02", // Ngày của tuần 5
//                        timeSlot: "15:00 - 16:00",
//                        lecture: "Dr. Green",
//                        course: "Biology",
//                        group: "G",
//                        room: "107",
//                        attendanceStatus: "Present"
//                    }
//                ];

                // Filter timetable data based on selected week and year
                const filteredData = timetableData.filter((entry) => {
                    const entryDate = new Date(entry.date);
                    const entryWeek = entryDate.getWeek();
                    const entryYear = entryDate.getFullYear();
                    return entryWeek == week && entryYear == year;
                });

                return filteredData;
            }




            // Initialize weeks when the page loads
            updateWeeks();

            // Update weeks when the year changes
            yearElement.addEventListener("change", updateWeeks);

            // Update timetable when weeks change
            weekElement.addEventListener("change", updateTimetable);
        </script>
    </body>
</html>