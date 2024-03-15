<%-- 
    Document   : groupChat
    Created on : Mar 11, 2024, 9:12:11 PM
    Author     : -MSI-
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>WEBSOCKET</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
    <style>


        a:hover {
            background-color: #45a049;
            color: white;
        }

        .home a {
            color: black;
            font-size: 25px;
            padding: 5px 10px;
            text-decoration: none;
            background-color: #ccc;
            border-radius: 5px;
        }

        .home {
            padding: 10px;
            border-radius: 5px;
            margin: 25px 0;
        }
        
        .group-item{
            border:1px solid #ccc;
            margin-bottom: 15px;
            margin-right: 5px;
            border-radius: 10px;
        }
        .group-item:hover {
            background-color: rgba(128, 128, 128, 0.155);
            cursor: pointer;
        }

        .group-content {
            display: none;
        }


        #groupContent${requestScope.firstGroup}{
            display:block;
        }

        * {
            margin: 0px;
            padding: 0px;
        }

       

        .no-padding-margin {
            margin: 0px;
            padding: 0px;
        }

        body {
            background-color: rgba(215, 214, 214, 0.556)
        }

     

     

        .navbar {
            /* background-color: #005b96; */
            /* background: linear-gradient(to right, #9370DB, #87CEEB); */
            background: linear-gradient(to right,#9370DB, #59b6da);
            padding: 13px 0px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
            position: fixed;
            width: 100%;
        }

  

        .body-content {
            padding-top: 70px;
            width: 100%;

        }



        .chat-box-left{
            background-color: white;
            margin-top: 20px;
            margin-left: 20px;
            border: 3px solid rgba(128, 128, 128, 0.3);
            border-radius: 10px 0px 0px 10px;
            padding: 20px 0px 20px 20px;

        }

      

        .box-chat-main {
            background-color: white;
            margin-top: 20px;
            margin-right: 20px;
            border: 3px solid rgba(128, 128, 128, 0.3);
            border-left: none;
            border-radius: 0px 10px 10px 0px;

        }

    

        .box-chat-main-header{
            display: flex;
            justify-content: space-between;
            border-bottom: 3px solid rgba(128, 128, 128, 0.249);
            padding: 20px 20px 0px 20px;
        }

        .box-chat-main-header a{
            color: black;
        }

        .box-chat-main-header div{
            margin-right: 10px;
        }


        .box-chat-main-header-title{
            display: flex;
        }

        .box-chat-main-body{
            overflow-y: scroll;
            padding: 30px 20px 30px 20px;
            height: 465px;
        }

     

        .box-chat-main-body .friend-conversation{
            display: flex;
            align-items: flex-start;
            justify-content: left;
            margin-bottom: 10px;
        }

        .box-chat-main-body .friend-conversation .title{
            margin-left: 10px;
            padding: 5px 10px;
            border-radius: 0px 10px 10px 10px;
            background: linear-gradient(to right, #9370DB, #87CEEB);
            color: white;
            margin-top: 5px;
            max-width: 300px;
            word-wrap: break-word;
        }

        .box-chat-main-body .your-self-conversation{
            display: flex;
            align-items: flex-start;
            justify-content: right;
            margin-bottom: 10px;
        }

        .box-chat-main-body .your-self-conversation .title{
            margin-right: 10px;
            margin-top: 5px;
            padding: 5px 10px;
            border-radius: 10px 10px 0px 10px;
            border: 1px solid rgba(128, 128, 128, 0.375);
            background-color: rgba(128, 128, 128, 0.21);
            /* background-color: white; */
            color: black;
            max-width: 300px;
            word-wrap: break-word;
        }

        .box-chat-main-footer{
            border-top: 3px solid rgba(128, 128, 128, 0.249);
            padding: 10px 20px;
        }

        .box-chat-main-footer form{
            display: flex;
            justify-content: center;
            align-items: center
        }

        .box-chat-main-footer input[type="text"]{
            width: 80%;
            height: 45px;
            border-radius: 40px 40px 40px 40px;
            background-color: rgba(128, 128, 128, 0.208);
            border: none;
            padding: 20px;
        }




        input[type="button"] {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
            border-radius: 5px;
        }

        .highlighted {
            background-color: rgba(128, 128, 128, 0.155);
            font-weight: bold; /* Make the text bold for emphasis */
        }

        .header-chat{
            text-align: center;
        }

        


    </style>
    <body>	
        <div class="home">
            <c:if test="${requestScope.role == 3}"> <a href="student/home">Home</a></c:if>
            <c:if test="${requestScope.role != 3}"> <a href="lecture/home">Home</a></c:if>
            </div>
            <div class="header-chat"> <h2> WebSocket Chat Room</h2></div>
           




            <div class="container-fluid body-content">
                <div class="row fix-component">
                    <div class="col-lg-3 no-padding-margin">   
                        <div class="chat-box-left">
                            <h3>Chats</h3>
                            <div id="groupList" class="group-list ">
                            <c:forEach items="${requestScope.studentGroups}" var="g">
                                <div class="group-item" onclick="showGroup(${g.id})" id="group${g.id}">
                                    <h3>${g.name}</h3>
                                    <h3>${g.course.code}</h3>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>


                <div class="col-lg-9 no-padding-margin">
                    <div class="box-chat-main">
                        <div id="groupContent" class="group-content-container">

                            <c:forEach items="${requestScope.studentGroups}" var="g">
                                <div id="groupContent${g.id}" class="group-content">
                                    <div class="box-chat-main-header"><h2>Group ${g.name} - ${g.course.code}</h2></div>
                                    <div class="box-chat-main-body"> <textarea id="textAreaMessageGroup${g.id}" rows="10" cols="50" readonly="true"></textarea></div>
                                    <div class="box-chat-main-footer"> 
                                        <input id="textMessageGroup${g.id}" type="text" />
                                        <input onclick="sendMessageToGroup${g.id}()" value="Send Message" id="sendMess" type="button" /> 
                                    </div>


                                </div>
                            </c:forEach>
                        </div>
                    </div> 
                </div>


            </div>
        </div>




        <script type="text/javascript">


            <c:forEach items="${requestScope.studentGroups}" var="g">
            var websocketGroup${g.id} = new WebSocket("ws://localhost:9999/websocket/chatRoomServer/group${g.id}");
            websocketGroup${g.id}.onopen = function (event) {
                var username = "${requestScope.username}"; // Lấy username từ servlet sang
                websocketGroup${g.id}.send(username.toUpperCase());
            };
            </c:forEach>

            <c:forEach items="${requestScope.studentGroups}" var="g">
            function processMessageGroup${g.id}(message) {
                console.log(message);
                document.getElementById("textAreaMessageGroup${g.id}").value += message.data + " \n";
            }
            </c:forEach>

            <c:forEach items="${requestScope.studentGroups}" var="g">
            websocketGroup${g.id}.onmessage = processMessageGroup${g.id};

            </c:forEach>



            <c:forEach items="${requestScope.studentGroups}" var="g">
            function sendMessageToGroup${g.id}() {
                var message = document.getElementById("textMessageGroup${g.id}").value;
                if (websocketGroup${g.id}.readyState === WebSocket.OPEN) {
                    websocketGroup${g.id}.send(message);
                    document.getElementById("textMessageGroup${g.id}").value = "";
                }
            }
            </c:forEach>

        </script>
        <script type="text/javascript">


            function showGroup(groupId) {
                // Ẩn nhóm hiện tại nếu có
                var groupContents = document.querySelectorAll('.group-content');
                groupContents.forEach(function (group) {
                    group.style.display = 'none';
                });

                // Hiển thị nhóm được chọn
                var selectedGroup = document.getElementById('groupContent' + groupId);
                console.log(selectedGroup);
                selectedGroup.style.display = 'block';


                var allGroups = document.querySelectorAll('.group-item');
                console.log(allGroups);
                allGroups.forEach(function (group) {
                    group.classList.remove('highlighted');
                });

                // Thêm lớp in đậm cho phần tử được chọn
                var clickedElement = document.getElementById('group' + groupId);
                clickedElement.classList.add('highlighted');
            }

        </script>

    </body>
</html>
