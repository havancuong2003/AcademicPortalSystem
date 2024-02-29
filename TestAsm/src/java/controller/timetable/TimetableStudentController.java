/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.timetable;

import dal.AttendanceDBContext;
import dal.SlotDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author -MSI-
 */
@WebServlet(name = "AttendanceStudentController", urlPatterns = {"/student/attendance"})
public class TimetableStudentController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        AttendanceDBContext adb = new AttendanceDBContext();
        SlotDBContext sdbc = new SlotDBContext();

        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");

        String username = a.getUsername();
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        // Xử lý logic để tạo danh sách chứa tất cả các ngày giữa startDate và endDate
        // Ví dụ: bạn có thể sử dụng một phương thức trong lớp utiliy hoặc thư viện thời gian để làm điều này
        // In ra để kiểm tra
        request.setAttribute("t1", startDate + "a");
        request.setAttribute("t2", endDate);

        request.setAttribute("list", adb.listInfoStudent(username));
        request.setAttribute("slots", sdbc.list());
        request.getRequestDispatcher("../view/timetable/studentTimeTB.jsp").forward(request, response);
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
        String dropDownYear = request.getParameter("dropdownYear");
        String dropDownWeek = request.getParameter("dropdownWeek");
        HttpSession session = request.getSession();
        session.setAttribute("dropDownYear", dropDownYear);

        if (dropDownWeek == null) {

            session.setAttribute("dropDownWeek", 1);
        } else {
            session.setAttribute("dropDownWeek", dropDownWeek);
        }

        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        
        
        session.setAttribute("startDate", startDate);
        session.setAttribute("endDate", endDate);
        response.sendRedirect("timetable");
        
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
