/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.mark;

import dal.MarkDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Mark;

/**
 *
 * @author -MSI-
 */
public class UpdateMarkByLecture extends HttpServlet {

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
            out.println("<title>Servlet UpdateMarkByLecture</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateMarkByLecture at " + request.getContextPath() + "</h1>");
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
        String gradeItem = request.getParameter("gradeItem");
        String groupid = request.getParameter("groupid");

        MarkDBContext mdbc = new MarkDBContext();

        ArrayList<Mark> insertMarkForCourse = mdbc.insertMarkForCourse(Integer.parseInt(groupid), gradeItem);
        request.setAttribute("listStudentByCourse", insertMarkForCourse);

        request.setAttribute("gradeItem", gradeItem);

        request.getRequestDispatcher("../view/mark/lectureInputMark.jsp").forward(request, response);
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
        String gradeItem = request.getParameter("gradeitem");
        String groupid = request.getParameter("groupid");

        MarkDBContext mdbc = new MarkDBContext();

        ArrayList<Mark> insertMarkForCourse = mdbc.insertMarkForCourse(Integer.parseInt(groupid), gradeItem);
        for (Mark mark : insertMarkForCourse) {
          String value =  request.getParameter("value"+mark.getStudent().getId());
          request.setAttribute("test"+value, value);
        }
        request.getRequestDispatcher("../view/test.jsp").forward(request, response);
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
