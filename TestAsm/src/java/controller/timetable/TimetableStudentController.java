/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.timetable;

import dal.AttendanceDBContext;
import dal.SlotDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import model.Account;
import util.DateTimeHelper;

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
        
        
        DateTimeHelper dateTimeHelper = new DateTimeHelper();
        LocalDate today = LocalDate.now();

        int weekOfYear = dateTimeHelper.getWeekOfYear(today);

        if (session.getAttribute("dropDownWeek") == null) {
            session.setAttribute("dropDownWeek", weekOfYear);
            session.setAttribute("startDate", dateTimeHelper.getFirstDayOfWeek(today));
            session.setAttribute("endDate", dateTimeHelper.getLastDayOfWeek(today));

        }

        ArrayList<java.sql.Date> dates = dateTimeHelper.getWeekDaysAsSqlDates((LocalDate) session.getAttribute("startDate"));
        request.setAttribute("dates", dates);
        if (session.getAttribute("dropDownYear") == null) {
            session.setAttribute("dropDownYear", "2024");
        }

        String username = a.getUsername();

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

        if (!session.getAttribute("dropDownYear").equals(dropDownYear)) {
            session.setAttribute("dropDownWeek", 1);
        }

        if (dropDownWeek == null) {

            session.setAttribute("dropDownWeek", 1);
        } else {
            session.setAttribute("dropDownWeek", dropDownWeek);
        }

        String valueChange = request.getParameter("yearChanged");

        if (valueChange.equals("")) {

            session.setAttribute("getValueChange", "false");
        } else {
            session.setAttribute("getValueChange", valueChange);
        }
        if (valueChange.equals("true")) {
            session.setAttribute("dropDownWeek", 1);
        }

        session.setAttribute("t11", session.getAttribute("dropDownWeek"));
        String startDate = request.getParameter("startDate" + session.getAttribute("dropDownWeek"));
        String endDate = request.getParameter("endDate" + session.getAttribute("dropDownWeek"));

        LocalDate startDates = LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate endDates = LocalDate.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE);
        session.setAttribute("startDate", startDates);
        session.setAttribute("endDate", endDates);
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
