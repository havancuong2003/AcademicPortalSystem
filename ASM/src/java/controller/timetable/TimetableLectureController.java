/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.timetable;

import dal.AttendanceDBContext;
import dal.SlotDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import model.Account;
import model.Session;
import util.DateTimeHelper;

/**
 *
 * @author -MSI-
 */
public class TimetableLectureController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AttendanceDBContext adb = new AttendanceDBContext();
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        String username = a.getUsername();

        request.setAttribute("role", a.getRole());

        String lid = request.getParameter("lid");
        String lidSession = (String) session.getAttribute("lidsearch");
        if (lidSession == null) {
            request.setAttribute("a", "null");
        }

        request.setAttribute("taaaa", lidSession);
        if (lid == null) {
            if (lidSession == null) {
                request.setAttribute("userNameMain", username);
                request.setAttribute("userNamefind", username);
                ArrayList<Session> listInfoLecture = adb.listInfoLecture(username);
                request.setAttribute("listLecture", listInfoLecture);
            } else {
                request.setAttribute("userNameMain", username);
                request.setAttribute("userNamefind", lidSession);
                ArrayList<Session> listInfoLecture = adb.listInfoLecture(lidSession);
                request.setAttribute("listLecture", listInfoLecture);
            }

        } else {
            request.setAttribute("userNameMain", username);
            request.setAttribute("userNamefind", lid);
            ArrayList<Session> listInfoLecture = adb.listInfoLecture(lid);
            request.setAttribute("lid", lid);
            request.setAttribute("listLecture", listInfoLecture);
        }

        SlotDBContext sdbc = new SlotDBContext();

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

        request.setAttribute("slots", sdbc.list());
        request.getRequestDispatcher("../view/timetable/testlecture.jsp").forward(request, response);
    }

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

        String lid = request.getParameter("lidsearch");

        if (!lid.equals("")) {
            session.setAttribute("lidsearch", lid);
        }

        response.sendRedirect("timetable");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
