/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.attendance;

import dal.AttendanceDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Attendance;

/**
 *
 * @author -MSI-
 */
public class LectureAttendance extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String a_raw = request.getParameter("sessionid");
        int ssid = Integer.parseInt(a_raw);
        
        AttendanceDBContext adb = new AttendanceDBContext();
        ArrayList<Attendance> studentsBySessionID = adb.getStudentsBySessionID(ssid);
        
        request.setAttribute("list", studentsBySessionID);
        request.getRequestDispatcher("../view/attendance/lecture.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String a_raw = request.getParameter("sessionid");
        int ssid = Integer.parseInt(a_raw);
        AttendanceDBContext adb = new AttendanceDBContext();
        ArrayList<Attendance> studentsBySessionID = adb.getStudentsBySessionID(ssid);
        
        for (Attendance attendance : studentsBySessionID) {
            String attendanceValue = request.getParameter("attendance" + attendance.getStudent().getId());
            String descriptionValue = request.getParameter("description" + attendance.getStudent().getId());
            String status = null;
            if (attendanceValue != null) {
                
                if (attendanceValue.equals("present")) {
                    status = "true";
                } else if (!attendanceValue.equals("present")) {
                    status = "false";
                }
            }
            attendance.setDescription(descriptionValue);
            attendance.setStatus(status);
        }
        
        adb.updateAttendanceStatus(studentsBySessionID, ssid);
        response.sendRedirect("timetable");
    }
// check cai nay de biet la giang vien da diem danh chua, neu khong ton tai co nghia la giaing vien chua diem danh
// sau do add data moi
// giang vien diem danh roi thi update lai data 

    public boolean checkSessionExist(int id, ArrayList<Attendance> attendances) {
        for (Attendance a : attendances) {
            if (a.getSession().getId() == id) {
                return true;
            }
        }
        return false;
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
