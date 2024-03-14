/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.modelAdminControl;

import dal.LectureDBContext;
import dal.StudentDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Student;
import model.Teacher;

/**
 *
 * @author -MSI-
 */
public class LectureControlByAdmin extends HttpServlet {

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
            out.println("<title>Servlet LectureControlByAdmin</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LectureControlByAdmin at " + request.getContextPath() + "</h1>");
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
        String lectureInfo = request.getParameter("lectureInfo");
        LectureDBContext ldbc = new LectureDBContext();
        ArrayList<Teacher> lectureForSerach = ldbc.getTeacherForSearch(lectureInfo);
        if (lectureInfo != null) {

            request.setAttribute("lectures", lectureForSerach);
        }
        request.getRequestDispatcher("../view/admin/controlModel/lecture.jsp").forward(request, response);
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
        String lectureInfo = request.getParameter("lectureInfo");
        LectureDBContext ldbc = new LectureDBContext();
        ArrayList<Teacher> lectureForSerach = ldbc.getTeacherForSearch(lectureInfo);
        if (!lectureForSerach.isEmpty()) {
            for (Teacher lecture : lectureForSerach) {
                String value = request.getParameter("editStatus_" + lecture.getId());
//                request.setAttribute("value" + lecture.getId(), value);
                if (value.equals("true")) {
                    String lid = request.getParameter("lectureID_" + lecture.getId());
//                    request.setAttribute("id" + lecture.getId(), sid);

                    String lname = request.getParameter("lectureName_" + lecture.getId());
//                    request.setAttribute("name" + lecture.getId(), sname);

                    String dob = request.getParameter("lectureDOB_" + lecture.getId());
//                    request.setAttribute("dob" + lecture.getId(), dob);

                    String email = request.getParameter("lectureEmail_" + lecture.getId());
//                    request.setAttribute("email" + lecture.getId(), email);

                    String username = request.getParameter("lectureUsername_" + lecture.getId());

                    if (username.equals("")) {
//                        request.setAttribute("username" + lecture.getId(), "null roiiiiiii");
                        ldbc.updateLectureAfterEdit(lecture.getId(), lid, lname, dob, email, null);
                    } else {
                        request.setAttribute("username" + lecture.getId(), "sap nul");
                        ldbc.updateLectureAfterEdit(lecture.getId(), lid, lname, dob, email, username);

                    }
//                    request.setAttribute("done" + lecture.getId(), lecture.getId());
                }
            }
        }

//        request.setAttribute("s", lectureForSerach.size());
        // request.getRequestDispatcher("../Result.jsp").forward(request, response);
        response.sendRedirect("controlLecture");
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
