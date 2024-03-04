/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import controller.sendingemail.EmailUtility;
import dal.AccountDBContext;
import dal.SendingEmailDBContext;
import jakarta.mail.MessagingException;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.SendEmail;
import model.Student;
import util.getStudentHelper;

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

        req.getRequestDispatcher("../view/admin/noticeChangPassword.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy địa chỉ email từ form
        AccountDBContext a = new AccountDBContext();
        String recipient = request.getParameter("getAccount");
        String email = a.getEmailByUserName(recipient);
        // Nội dung email
        String subject = "CHANGE PASSWORD";
        String content = "Password Change successfully. If not you, please contact with havancuong07122003@gmail.com";

        try {
            // Gửi email
            EmailUtility.sendEmail(host, port, user, pass, email, subject, content);

            // Chuyển hướng về trang thông báo đã gửi email thành công
            response.sendRedirect("changepassword");
        } catch (MessagingException ex) {
        } finally {
            response.sendRedirect("changepassword");
        }
    }

}
