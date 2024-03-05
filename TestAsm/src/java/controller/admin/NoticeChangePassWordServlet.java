/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import controller.sendingemail.EmailUtility;
import dal.AccountDBContext;
import jakarta.mail.MessagingException;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author -MSI-
 */
public class NoticeChangePassWordServlet extends HttpServlet {

    private String host;
    private String port;
    private String user;
    private String pass;

    @Override
    public void init() {
        host = getServletContext().getInitParameter("host");
        port = getServletContext().getInitParameter("port");
        user = getServletContext().getInitParameter("user");
        pass = getServletContext().getInitParameter("pass");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String recipient = (String) session.getAttribute("accountNotice");
        // Lấy địa chỉ email từ form
        AccountDBContext a = new AccountDBContext();

        String email = a.getEmailByUserName(recipient);
        // Nội dung email
        String subject = "CHANGE PASSWORD";
        String content = "Password Change successfully. If not you, please contact with havancuong07122003@gmail.com";

        try {
            // Gửi email
            EmailUtility.sendEmail(host, port, user, pass, email, subject, content);

            // Chuyển hướng về trang thông báo đã gửi email thành công
            resp.sendRedirect("changepassword");
        } catch (MessagingException ex) {
        } finally {
            resp.sendRedirect("changepassword");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
