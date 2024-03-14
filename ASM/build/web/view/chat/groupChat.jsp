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
    </head>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f0f0f0;
        }

        .grid-container {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(400px, 1fr));
            grid-gap: 20px;
            padding: 20px;
        }

        .grid-item {
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }

        h2 {
            color: #333;
            margin: 0;
            padding: 0;
            border-bottom: 1px solid #ccc;
        }

        input[type="text"] {
            width: 90%;
            padding: 10px;
            margin-bottom: 10px;
        }

        input[type="button"] {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }

        textarea {
            resize: none;
            border: 1px solid #ccc;
            height: 300px;
        }
        a:hover {
            background-color: #45a049;
        }
        .home a {
            color: black;
            font-size: 25px;
            padding: 5px 10px;
            text-decoration: none;
            background-color: #ccc;
        }
        .home {
            padding: 10px;
            border-radius: 5px;
            margin: 25px 0;
        }


    </style>
    <body>	
        <div class="home">
            <c:if test="${requestScope.role == 3}"> <a href="student/home">Home</a></c:if>
            <c:if test="${requestScope.role != 3}"> <a href="lecture/home">Home</a></c:if>
            </div>
            <h2> WebSocket Chat Room</h2>


            <div class="grid-container">
            <c:forEach items="${requestScope.studentGroups}" var="g">
                <div class="grid-item">
                    <h2>Group ${g.name} - ${g.course.code}</h2>
                    <input id="textMessageGroup${g.id}" type="text" />
                    <input onclick="sendMessageToGroup${g.id}()" value="Send Message" type="button" /> <br/><br/>
                    <textarea id="textAreaMessageGroup${g.id}" rows="10" cols="50"></textarea>
                </div>
            </c:forEach>
        </div>



        <script type="text/javascript">


            <c:forEach items="${requestScope.studentGroups}" var="g">
            var websocketGroup${g.id} = new WebSocket("ws://localhost:9999/websocket/chatRoomServer/group${g.id}");
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
    </body>
</html>
