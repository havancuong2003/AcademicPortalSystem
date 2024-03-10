/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.attendance;

import dal.AttendanceDBContext;
import dal.MarkDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Account;
import model.Attendance;
import model.Group;

/**
 *
 * @author -MSI-
 */
public class CheckAttendanceStudent extends HttpServlet {

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
            out.println("<title>Servlet CheckAttendanceStudent</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckAttendanceStudent at " + request.getContextPath() + "</h1>");
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
        MarkDBContext mdb = new MarkDBContext();
        AttendanceDBContext adbc = new AttendanceDBContext();
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");

        ArrayList<Group> getTermOfStudentLearned = mdb.getTermOfStudentLearned(a.getUsername());
        request.setAttribute("term", getTermOfStudentLearned);

        String getTermID = request.getParameter("term");
        if (getTermID == null) {
            getTermID = getTermOfStudentLearned.get(getTermOfStudentLearned.size() - 1).getTerm().getId();
        }
        request.setAttribute("termid", getTermID);

        ArrayList<Group> selectTermGetCourse = mdb.getSelectTermToSeeMark(a.getUsername(), getTermID);
        request.setAttribute("courseByTerm", selectTermGetCourse);

        String getCourseid = request.getParameter("course");
        if (getCourseid == null) {
            getCourseid = "" + selectTermGetCourse.get(0).getCourse().getId();
        }
        request.setAttribute("active", getTermID);
        request.setAttribute("activeCourse", getCourseid);
        ArrayList<Attendance> attendanceStatusForAll = adbc.getAttendanceStatusForAll(getCourseid, getTermID, a.getUsername());
        request.setAttribute("attendance", attendanceStatusForAll);

        String totalAbsent = adbc.getTotalAbsent(a.getUsername(), getCourseid, getTermID);
        String[] absents = totalAbsent.split(";");
        request.setAttribute("absent", absents[0]);
        request.setAttribute("total", absents[1]);
        request.setAttribute("percent", absents[2]);
        request.getRequestDispatcher("../view/attendance/checkAttendance.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
