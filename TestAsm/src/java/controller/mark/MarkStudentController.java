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
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Account;
import model.Group;
import model.Mark;

/**
 *
 * @author -MSI-
 */
public class MarkStudentController extends HttpServlet {

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
            out.println("<title>Servlet MarkStudentController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MarkStudentController at " + request.getContextPath() + "</h1>");
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

        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");

        ArrayList<Group> getTermOfStudentLearned = mdb.getTermOfStudentLearned(a.getUsername());
        request.setAttribute("termMark", getTermOfStudentLearned);
        String getTermID = request.getParameter("termid");
        ArrayList<Group> selectTermToSeeMark = mdb.getSelectTermToSeeMark(a.getUsername(), getTermID);
        request.setAttribute("courseByTerm", selectTermToSeeMark);

        // neu nhu lay tu url xuong ma chua click vao course thi trang web se tra thi loi can not parse null
        String getCourseid = request.getParameter("courseid");

        request.setAttribute("activeTerm", getTermID);
        request.setAttribute("activeCourse", getCourseid);
        
        if (getCourseid != null) {
            ArrayList<Mark> m = mdb.getMarkByTermAndCourse(a.getUsername(), getTermID, Integer.parseInt(getCourseid));
            request.setAttribute("markOfCourse", m);

        }

        // neu chua click vao course thi se an bang diem di, con roi thi show bang diem ra
        if (getCourseid != null) {

            request.setAttribute("checkClick", "show");
        } else {
            request.setAttribute("checkClick", "hidden");
        }
        // request.setAttribute("markOfCourse", mdb.getMarkByTermAndCourse(a.getUsername(), "sp24", 19));
        //  request.setAttribute("listGroup", mdb.getGroupIdToTakeCourse());

        request.getRequestDispatcher("../view/mark/markPart2.jsp").forward(request, response);
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
