<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>

            html, body {
                height: 100%;
                margin: 0;
                padding: 0;
            }
            body {
                font-family: Arial, sans-serif;

                background-color: #f5f5f5;

            }
            .container {
                max-width: 800px;
                margin: 50px auto;
                padding: 20px;
                background-color: #fff;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            h1 {
                text-align: center;
                color: #333;
            }
            nav {
                margin-top: 30px;
                text-align: center;
            }
            ul {
                list-style-type: none;
                padding: 0;
                text-align: center;
                margin: 0;
            }
            li {
                display: inline-block;
                margin: 0 10px;
            }
            a {
                text-decoration: none;
                color: #007bff;
                font-weight: bold;
                transition: color 0.3s ease;
            }
            a:hover {
                color: #0056b3;
            }
            .logout {
                color: white;
                padding: 10px 20px;
                border: none;
                cursor: pointer;
                position: absolute;
                top: 10px;
                right: 50px;
            }
            .logout:hover::before {
                content: "Logout";
                position: absolute;
                top: 100%;
                left: 50%;
                transform: translateX(-50%);
                background-color: #333;
                color: white;
                padding: 5px;
                border-radius: 5px;
                opacity: 0;
                transition: opacity 0.3s;
            }
            .logout:hover::before {
                opacity: 1;
            }
            .footer {
                background-color: #ef9926;
                color: white;
                font-weight: bold;
                position: absolute;
                bottom: 0;
                font-size: 20px;
                width: 100%;
                height: 100px;
                text-align: center;
                padding: 20px;
            }
            header {
                color: black;
                margin: 10px;
                text-align: center;
                font-size: 1.8em;
                font-weight: thin;
            }
            .header-fpt {
                background-color: #f1f1f1;
                text-align: center;
                padding: 10px;
            }
            .logo-header img {
                width: 150px;
            }
            .feedback {
                text-align: center;
                display: ${requestScope.count == 0 ? 'none' : 'block'};
            }
            .container-feedback {
                display: ${requestScope.count == 0 ? 'block' : 'none'};
            }
            .listFeature {
                display: grid;
                grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
                gap: 10px;
                justify-content: center;
            }

            .listFeature ul {
                list-style-type: none;
                padding: 0;
            }

            .listFeature li {
                text-align: center;
                background-color: #f1f1f1;
                padding: 15px;
                border-radius: 5px;
                margin:20px;
            }

            .listFeature li a {
                text-decoration: none;
                color: #333;
                font-weight: bold;
                transition: color 0.3s ease;
            }

            .listFeature li a:hover {
                color: #007bff;
            }


            .content {
                flex: 1;
            }

            .footer {
                position: relative;
                bottom: 0;
                background-color: #ef9926;
                color: white;
                font-weight: bold;
                text-align: center;
                padding: 20px;
                font-size: 20px;
            }
        </style>

        <style>
            html, body {
                height: 100%;
                margin: 0;
                padding: 0;
                overflow-: hidden;
            }

            .container {
                min-height: calc(100% - 100px); /* Subtract the height of the footer */
                display: flex;
                flex-direction: column;
            }

            .content {
                flex: 1;
             
            }
            .footer {
                height: 100px; /* Set a fixed height for the footer */
                background-color: #ef9926;
                color: white;
                font-weight: bold;
                text-align: center;
                padding: 20px;
                font-size: 20px;
                position: relative;
                margin-top: auto; /* Make the footer stick to the bottom */
            }
        </style>
    </head>
    <body>
        <div class="welcome">
            <h2>Hello ${requestScope.user}</h2>
        </div>
        <div class="container">
            <div class="content">
                <div class="header-fpt">
                    <div class="logo-header">
                        <img src="../img/logofpt.png" alt="logo" />
                    </div>
                    <div class="logout">
                        <svg
                            xmlns="http://www.w3.org/2000/svg"
                            viewBox="0 0 512 512"
                            width="35"
                            height="35"
                            fill="#808080"
                            onclick="logout()"
                            >
                        <!--!Font Awesome Free 6.5.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.-->
                        <path
                            d="M377.9 105.9L500.7 228.7c7.2 7.2 11.3 17.1 11.3 27.3s-4.1 20.1-11.3 27.3L377.9 406.1c-6.4 6.4-15 9.9-24 9.9c-18.7 0-33.9-15.2-33.9-33.9l0-62.1-128 0c-17.7 0-32-14.3-32-32l0-64c0-17.7 14.3-32 32-32l128 0 0-62.1c0-18.7 15.2-33.9 33.9-33.9c9 0 17.6 3.6 24 9.9zM160 96L96 96c-17.7 0-32 14.3-32 32l0 256c0 17.7 14.3 32 32 32l64 0c17.7 0 32 14.3 32 32s-14.3 32-32 32l-64 0c-53 0-96-43-96-96L0 128C0 75 43 32 96 32l64 0c17.7 0 32 14.3 32 32s-14.3 32-32 32z"
                            />
                        </svg>
                    </div>
                </div>
                <nav>
                    <ul class="listFeature">
                        <li><a href="timetable">Weekly Timetable</a></li>
                        <li><a href="lecture_info.jsp">Thông Tin Lecture</a></li>
                        <li><a href="mark" >Mark report</a></li>
                        <li><a href="seefeedback" >See Feedback</a></li>
                        <li><a href="../chat" >Chat</a></li>

                    </ul>
                    <br/>
                    <br/>
                    <br/>
                    <br/>
                    <br/>
                    <br/>
                    <form action="home" method="get"> 
                        <input type="text" name="stinfo" placeholder="enter name or student id"/>
                        <input type="submit" value="search"/>
                    </form>
                    <c:if test="${requestScope.nothing ne 0}">
                        <table>
                            <thead>
                                <tr>
                                    <th>NO</th>    
                                    <th>StudentID</th>
                                    <th>Name</th>
                                    <th>Date of birth</th>
                                    <th>Image</th>
                                    <th>Email</th>
                                    <th>View grade</th>

                                </tr>
                            </thead>
                            <h3 style="color:red">${requestScope.notFound}</h3>
                            <tbody>

                                <c:forEach items="${requestScope.list}" var="s" varStatus="loop">
                                    <tr>
                                        <td>${loop.index + 1}</td>
                                        <td>${s.id}</td>
                                        <td>${s.name}</td>                                  
                                        <td>${s.dob}</td>                                  

                                        <td><img src="../img/${s.imgUrl}" alt="Student image"></td>
                                        <td>${s.email}</td>   
                                        <td><a href="viewgrade?sid=${s.id}">View Grade</a></td>   
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>
                    </c:if>


                </nav>
            </div>
        </div>
        <div class="footer">
            <p>FPT UNIVERSITY</p>
            <span>DAI HOC FPT HA NOI</span>
        </div>
        <script>

            function logout() {
                window.location.href = '/testasm/logout';
            }
        </script>
    </body>
</html>
