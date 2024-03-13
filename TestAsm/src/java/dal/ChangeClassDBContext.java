/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.ChangeClass;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Course;
import model.Group;
import model.Student;
import model.Term;

/**
 *
 * @author -MSI-
 */
public class ChangeClassDBContext extends DBContext<ChangeClass> {

    public ArrayList<ChangeClass> getAllRequired(String username) {
        ArrayList<ChangeClass> changeClasses = new ArrayList<>();
        try {
            String sql = "select cc.id,fromStudent,fromGroup,toStudent,toGroup,status from changeClass cc join Student s on s.id=cc.fromStudent\n"
                    + "where s.userName = ? and status = 'processing'";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ChangeClass c = new ChangeClass();
                c.setId(rs.getInt("id"));
                c.setFromStudent(getStudentByID(rs.getString("fromStudent")));
                c.setToStudent(getStudentByID(rs.getString("toStudent")));
                c.setFromGroup(getGroupByID(rs.getInt("fromGroup")));
                c.setToGroup(getGroupByID(rs.getInt("toGroup")));
                c.setStatus(rs.getString("status"));
                changeClasses.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChangeClassDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return changeClasses;
    }

    public ArrayList<ChangeClass> getAllRequiredForToStudent(String username) {
        ArrayList<ChangeClass> changeClasses = new ArrayList<>();
        try {
            String sql = "select cc.id,fromStudent,fromGroup,toStudent,toGroup,status from changeClass cc join Student s on s.id=cc.toStudent\n"
                    + "where s.userName = ? and status = 'processing'";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ChangeClass c = new ChangeClass();
                c.setId(rs.getInt("id"));
                c.setFromStudent(getStudentByID(rs.getString("fromStudent")));
                c.setToStudent(getStudentByID(rs.getString("toStudent")));
                c.setFromGroup(getGroupByID(rs.getInt("fromGroup")));
                c.setToGroup(getGroupByID(rs.getInt("toGroup")));
                c.setStatus(rs.getString("status"));
                changeClasses.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChangeClassDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return changeClasses;
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

    public Term getTermByID(String id) {
        Term t = null;
        try {
            String sql = "select * from term where id like ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                t = new Term();
                t.setId(rs.getString("id"));
                t.setDescription(rs.getString("description"));
                t.setTimeStart(rs.getDate("timeStart"));
                t.setTimeEnd(rs.getDate("timeEnd"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }

    public Group getGroupByID(int id) {
        Group g = null;
        try {
            String sql = "select * from [group] where id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                g = new Group();
                g.setId(rs.getInt("id"));
                g.setName(rs.getString("name"));
                g.setCourse(getCourseByID(rs.getInt("courseid")));
                g.setTerm(getTermByID(rs.getString("termID")));

                g.setTimeStart(rs.getDate("timeStart"));
                g.setTimeEnd(rs.getDate("timeEnd"));
                g.setFirstDay(rs.getDate("firstday"));
                g.setSecondDay(rs.getDate("secondday"));
            }
        } catch (SQLException e) {
        }
        return g;
    }

    public void changeClass(String fromStudent, int fromGroup, String toStudent, int toGroup, int changeID) {
        try {
            String sql = "update student_group\n"
                    + "set groupid = ?\n"
                    + "where id = (select id from student_group where Studentid =? and groupid= ?)\n"
                    + "\n"
                    + "update student_group\n"
                    + "set groupid = ?\n"
                    + "where id = (select id from student_group where Studentid =? and groupid= ?)"
                    + " update changeClass\n"
                    + "set status = 'Accepted'\n"
                    + "where id = ? "
                    + "";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, toGroup);
            stm.setString(2, fromStudent);
            stm.setInt(3, fromGroup);
            stm.setInt(4, fromGroup);
            stm.setString(5, toStudent);
            stm.setInt(6, toGroup);
            stm.setInt(7, changeID);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ChangeClassDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void reject(int changeID) {
        try {
            String sql = "update changeClass\n"
                    + "set status = 'Reject'\n"
                    + "where id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, changeID);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ChangeClassDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<ChangeClass> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(ChangeClass entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(ChangeClass entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(ChangeClass entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ChangeClass get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
