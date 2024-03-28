/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.SendEmail;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Course;
import model.Student;

/**
 *
 * @author -MSI-
 */
public class SendingEmailDBContext extends DBContext<SendEmail> {

    public ArrayList<SendEmail> getAllStudentAbsentThan10() {
        ArrayList<SendEmail> sendEmails = new ArrayList<>();
        try {
            String sql = "select s.id as stid,s.name as sname,c.id as cid, c.code,ap.absentCount,ap.totalClasses,ap.emailCheck from student_group sg \n"
                    + "join absentPercent ap on ap.student_group_id=sg.id\n"
                    + "join student s on s.id=sg.Studentid\n"
                    + "join [group] g on g.id=sg.groupid \n"
                    + "join course c on c.id=g.courseId and emailCheck = 0 \n";

            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                if (rs.getInt("absentCount") * 1.0 / (rs.getInt("totalClasses")) >= 0.1) {
                    SendEmail s = new SendEmail();
                    s.setStudent(getStudentByID(rs.getString("stid")));
                    s.setCourse(getCourseByID(rs.getInt("cid")));
                    s.setAbsentCount(rs.getInt("absentCount"));
                    s.setTotalClasses(rs.getInt("totalClasses"));
                    s.setEmailCheck(rs.getBoolean("emailCheck"));
                    sendEmails.add(s);
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(SendingEmailDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sendEmails;
    }

    public Course getCourseByID(int id) {
        Course c = null;
        try {
            String sql = "select * from course where id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                c = new Course();
                c.setId(rs.getInt("id"));
                c.setCode(rs.getString("code"));
                c.setDescription(rs.getString("description"));
            }
        } catch (SQLException e) {
        }
        return c;
    }

    public Student getStudentByID(String id) {
        Student s = null;
        try {
            String sql = "select * from student where id like ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                s = new Student();
                s.setId(rs.getString("id"));
                s.setName(rs.getString("name"));
                s.setDob(rs.getDate("dob"));
                s.setEmail(rs.getString("email"));
                s.setImgUrl(rs.getString("imgUrl"));

            }
        } catch (SQLException e) {
        }
        return s;
    }

    public void updateEmailCheck(ArrayList<String> sidAndCids) {
        if (sidAndCids.isEmpty()) {
            return;
        }

        try {
            for (String sidAndCid : sidAndCids) {
                String[] getData = sidAndCid.split("-");
                String sid = getData[0];
                String cid = getData[1];
                String sql = "   update absentPercent\n"
                        + "		   set emailCheck =1 \n"
                        + "		   where student_group_id = (select sg.id from student_group sg join [Group] g\n"
                        + "		 on sg.groupid = g.id\n"
                        + "		 where g.courseId=? and sg.Studentid=? and g.termid < 5)";
                PreparedStatement stm = connection.prepareStatement(sql);
                stm.setInt(1, Integer.parseInt(cid));
                stm.setString(2, sid);
                stm.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(SendingEmailDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<SendEmail> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(SendEmail entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(SendEmail entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(SendEmail entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public SendEmail get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
