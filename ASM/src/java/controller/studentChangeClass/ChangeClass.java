/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.studentChangeClass;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
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
public class ChangeClass extends HttpServlet {

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
            out.println("<title>Servlet ChangeClass</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangeClass at " + request.getContextPath() + "</h1>");
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
                   response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        GroupDBContext gdbc = new GroupDBContext();
        Account a = (Account) session.getAttribute("account");

        ArrayList<Group> groupToChangeClassStudent = gdbc.getGroupToChangeClassStudent(a.getUsername());
        request.setAttribute("groups", groupToChangeClassStudent);

        AccountDBContext adbc = new AccountDBContext();
        request.setAttribute("studentid", adbc.getStudentIDByUserName(a.getUsername()));

        ChangeClassDBContext ccdbc = new ChangeClassDBContext();
        ArrayList<model.ChangeClass> allRequired = ccdbc.getAllRequired(a.getUsername());

        request.setAttribute("requireds", allRequired);
    //    response.setContentType("application/json");
        Gson gson = new Gson();
        
        String json = gson.toJson(allRequired);
        response.getWriter().write(json);
        request.getRequestDispatcher("../view/changeClass/student.jsp").forward(request, response);

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        ChangeClassDBContext ccdbc = new ChangeClassDBContext();
        ArrayList<String> a = new ArrayList<>();
        switch (action) {
            case "add": {
                String course = request.getParameter("course");
                String fromStudent = request.getParameter("fromStudent");
                String toStudent = request.getParameter("toStudent");
                if (ccdbc.checkExist(fromStudent, course) == 0) {

                    ccdbc.insertRequired(course, fromStudent, toStudent);
                    out.print("{\"status\":\"ok\"}");
                }
                break;
            }

            case "findID": {
                String course = request.getParameter("course");
                String fromStudent = request.getParameter("fromStudent");
                int newRequestId = ccdbc.findID(fromStudent, course);
                JsonObject jsonResponse = new JsonObject();
                jsonResponse.addProperty("", newRequestId);
                Gson gson = new Gson();
                String json = gson.toJson(jsonResponse);
                response.getWriter().write(json);
                break;
            }
            case "cancel":
                String cid = request.getParameter("cid");
                ccdbc.deleteRequired(cid);
                out.print("{\"status\":\"ok\"}");
                break;
            default:
                break;
        }

        // Flush để đảm bảo dữ liệu được gửi đi
        out.flush();
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
