/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.studentChangeClass;

import com.google.gson.Gson;
import dal.AccountDBContext;
import dal.ChangeClassDBContext;
import dal.GroupDBContext;
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

/**
 *
 * @author -MSI-
 */
public class SeeRequiredsController extends HttpServlet {

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
            out.println("<title>Servlet SeeRequiredsController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SeeRequiredsController at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        GroupDBContext gdbc = new GroupDBContext();
        Account a = (Account) session.getAttribute("account");
        
        ArrayList<Group> groupToChangeClassStudent = gdbc.getGroupToChangeClassStudent(a.getUsername());
        request.setAttribute("groups", groupToChangeClassStudent);
        
        AccountDBContext adbc = new AccountDBContext();
        request.setAttribute("studentid", adbc.getStudentIDByUserName(a.getUsername()));
        
        ChangeClassDBContext ccdbc = new ChangeClassDBContext();
        ArrayList<model.ChangeClass> allRequired = ccdbc.getAllRequiredForToStudent(a.getUsername());
        request.setAttribute("requests", allRequired);
        request.getRequestDispatcher("../view/changeClass/seeRequired.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        ChangeClassDBContext ccdbc = new ChangeClassDBContext();
        ArrayList<model.ChangeClass> allRequired = ccdbc.getAllRequiredForToStudent(a.getUsername());
        for (model.ChangeClass changeClass : allRequired) {
            String action = request.getParameter("action" + changeClass.getId());
            if (action != null) {
                if (action.equals("reject")) {
                    ccdbc.reject(changeClass.getId());
                } else if (action.equals("accept")) {
                    String ccid = request.getParameter("requestId");
                    String fromStudent = request.getParameter("fromST" + changeClass.getId());
                    String fromGr = request.getParameter("fromGr" + changeClass.getId());
                    String toStudent = request.getParameter("toSt" + changeClass.getId());
                    String toGroup = request.getParameter("toGr" + changeClass.getId());
                    ccdbc.changeClass(fromStudent, Integer.parseInt(fromGr), toStudent, Integer.parseInt(toGroup), Integer.parseInt(ccid));
                  
                }
            }
            
        }
        response.sendRedirect("seerequired");
        
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
