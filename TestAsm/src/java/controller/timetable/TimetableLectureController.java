/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.timetable;

import dal.AttendanceDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Account;
import model.Session;

/**
 *
 * @author -MSI-
 */
public class TimetableLectureController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AttendanceDBContext adb = new AttendanceDBContext();
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        String username = a.getUsername();
        ArrayList<Session> listInfoLecture = adb.listInfoLecture(username);
  
        request.setAttribute("list", listInfoLecture);
        request.getRequestDispatcher("../view/timetable/lecture.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
