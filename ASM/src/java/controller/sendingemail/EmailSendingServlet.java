package controller.sendingemail;

import dal.SendingEmailDBContext;
import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.SendEmail;

import model.Student;
import util.getStudentHelper;

public class EmailSendingServlet extends HttpServlet {

    private String host;
    private String port;
    private String user;
    private String pass;

    @Override
    public void init() {
        host = getServletContext().getInitParameter("host");
        port = getServletContext().getInitParameter("port");
        user = getServletContext().getInitParameter("user");
        pass = getServletContext().getInitParameter("pass");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SendingEmailDBContext sedbc = new SendingEmailDBContext();
        ArrayList<SendEmail> students = sedbc.getAllStudentAbsentThan10();
        req.setAttribute("students", students);
        req.getRequestDispatcher("../view/admin/sendEmail.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] selectedStudents = request.getParameterValues("selectedStudents");
        String resultMessage = "";
        getStudentHelper g = new getStudentHelper();
        ArrayList<String> sidAndCids = new ArrayList<>();
        String sidAndCid = "";
        try {
            if (selectedStudents == null || selectedStudents.length == 0) {
                throw new IllegalArgumentException("No students selected.");
            }

            for (String studentId : selectedStudents) {
                Student student = g.getStudentById(studentId);

                if (student == null) {
                    throw new IllegalArgumentException("Student with ID " + studentId + " not found.");
                }
                String courseid = request.getParameter("courseid");
                String course = request.getParameter("course-" + studentId); // Lấy môn học của sinh viên
                String emailContent = "You absent more than 10% of " + course +" please attention about lession"; // Nội dung email có thể được tạo dựa trên môn học
                sidAndCid = (student.getId() + "-" + courseid);
                sidAndCids.add(sidAndCid);
                sidAndCid = "";
                EmailUtility.sendEmail(host, port, user, pass, student.getEmail(), "ATTENDANCE STATUS FOR " + course, emailContent);
            }

            resultMessage = "The emails were sent successfully";
        } catch (Exception ex) {
            ex.printStackTrace();
            resultMessage = "There was an error: " + ex.getMessage();
        } finally {
            request.setAttribute("Message", resultMessage);
            SendingEmailDBContext sedbc = new SendingEmailDBContext();
            sedbc.updateEmailCheck(sidAndCids);
            response.sendRedirect("home");
        }
    }

}
