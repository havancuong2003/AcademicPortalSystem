package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
        List<Student> students = new ArrayList<>();
        students.add(new Student("he2", "Linh Do", "dothuylinh2673@gmail.com", "MAS"));
        students.add(new Student("he1", "Ha Cuong", "cuonghvhe176362@fpt.edu.vn", "PRJ"));
        req.setAttribute("students", students);
        req.getRequestDispatcher("SendMail.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] selectedStudents = request.getParameterValues("selectedStudents");
        String resultMessage = "";
        getStudentHelper g = new getStudentHelper();

        try {
            if (selectedStudents == null || selectedStudents.length == 0) {
                throw new IllegalArgumentException("No students selected.");
            }

            for (String studentId : selectedStudents) {
                Student student = g.getStudentById(studentId);

                if (student == null) {
                    throw new IllegalArgumentException("Student with ID " + studentId + " not found.");
                }

                String course = request.getParameter("course-" + studentId); // Lấy môn học của sinh viên
                String emailContent = "Content for " + course; // Nội dung email có thể được tạo dựa trên môn học
                EmailUtility.sendEmail(host, port, user, pass, student.getEmail(), "Subject for " + course, emailContent);
            }

            resultMessage = "The emails were sent successfully";
        } catch (Exception ex) {
            ex.printStackTrace();
            resultMessage = "There was an error: " + ex.getMessage();
        } finally {
            request.setAttribute("Message", resultMessage);
            request.getRequestDispatcher("/Result.jsp").forward(request, response);
        }
    }

}
