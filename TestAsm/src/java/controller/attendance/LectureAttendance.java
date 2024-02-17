/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.attendance;

import dal.AttendanceDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Attendance;

/**
 *
 * @author -MSI-
 */
public class LectureAttendance extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LectureAttendance</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LectureAttendance at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String a_raw = request.getParameter("sessionid");
        int ssid = Integer.parseInt(a_raw);

        AttendanceDBContext adb = new AttendanceDBContext();
        ArrayList<Attendance> studentsBySessionID = adb.getStudentsBySessionID(ssid);

        request.setAttribute("list", studentsBySessionID);
        request.getRequestDispatcher("../view/attendance/lecture.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String a_raw = request.getParameter("sessionid");
        int ssid = Integer.parseInt(a_raw);

        AttendanceDBContext adb = new AttendanceDBContext();
        ArrayList<Attendance> studentsBySessionID = adb.getStudentsBySessionID(ssid);

        for (Attendance attendance : studentsBySessionID) {
            String attendanceValue = request.getParameter("attendance" + attendance.getStudent().getId());
            String status = "";
            if (attendanceValue.equals("present")) {
                status = "true";
            } else {
                status = "false";
            }
            attendance.setStatus(status);

        }

        adb.updateAttendanceStatus(studentsBySessionID, ssid);
        response.sendRedirect("timetable");

    }
// check cai nay de biet la giang vien da diem danh chua, neu khong ton tai co nghia la giaing vien chua diem danh
// sau do add data moi
// giang vien diem danh roi thi update lai data 

    public boolean checkSessionExist(int id, ArrayList<Attendance> attendances) {
        for (Attendance a : attendances) {
            if (a.getSession().getId() == id) {
                return true;
            }
        }
        return false;
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
