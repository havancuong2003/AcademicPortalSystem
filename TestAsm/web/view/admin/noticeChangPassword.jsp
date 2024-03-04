<%-- 
    Document   : noticeChangPassword
    Created on : Mar 4, 2024, 1:26:02 PM
    Author     : -MSI-
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="homebutton.jsp"></jsp:include>

            <form id="emailForm" action="noticechangepassword" method="post">
                <input type="text" name="getAccount" value="${sessionScope.accountNotice}"/>
            <input type="submit" value="Send Email" />
        </form>

        <script>
            // Hàm tự động submit form sau 1 giây
            setTimeout(function () {
                document.getElementById('emailForm').submit();
            }, 1000);
        </script>
    </body>
</html>

