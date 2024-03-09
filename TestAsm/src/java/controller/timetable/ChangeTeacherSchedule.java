/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.timetable;

import dal.LectureDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Session;
import model.Teacher;

/**
 *
 * @author -MSI-
 */
public class ChangeTeacherSchedule extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String date = request.getParameter("date");
        String slot = request.getParameter("slot");
        request.setAttribute("check", "hidden");
        if (date != null && slot != null) {

            LectureDBContext ldbc = new LectureDBContext();
            ArrayList<Teacher> teacherChangeSchedule = ldbc.getTeacherChangeSchedule(date, Integer.parseInt(slot));
            request.setAttribute("teachers", teacherChangeSchedule);

            request.setAttribute("date", date);
            request.setAttribute("slot", slot);
            request.setAttribute("check", "show");
        }
        request.getRequestDispatcher("../view/admin/controlTimeTable/changeschedule.jsp").forward(request, response);
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
        String tid = request.getParameter("selectTeacher");

        String date = request.getParameter("date");
        String slot = request.getParameter("slot");

        LectureDBContext ldbc = new LectureDBContext();
        ArrayList<Teacher> teacherChangeSchedule = ldbc.getTeacherChangeSchedule(date, Integer.parseInt(slot));

        String teacherid = teacherChangeSchedule.get(Integer.parseInt(tid) - 1).getId();

        Session s = ldbc.getSessionForChange(date, Integer.parseInt(slot));

        ldbc.updateLectureChange(teacherid, s.getId());

       response.sendRedirect("timetablelecture?info="+teacherid);
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
