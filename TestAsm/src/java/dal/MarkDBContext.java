/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.Mark;
import java.sql.*;
import model.Student;

/**
 *
 * @author -MSI-
 */
public class MarkDBContext extends DBContext<Mark> {

    @Override
    public ArrayList<Mark> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Student getStudentByID(int id) {
        Student s = null;
        try {
            String sql = "select * from student where id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
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

//    public Course getCourseByID(int id) {
//        Course c = null;
//        try {
//            String sql = "select * from [course] where id = ?";
//            PreparedStatement stm = connection.prepareStatement(sql);
//            stm.setInt(1, id);
//            ResultSet rs = stm.executeQuery();
//            if (rs.next()) {
//                c = new Course();
//                c.setId(rs.getInt("id"));
//                c.setName(rs.getString("name"));
//            }
//        } catch (SQLException e) {
//        }
//        return c;
//    }

//    public ArrayList<Mark> getMarkStudent() {
//        ArrayList marks = new ArrayList();
//        try {
//            String sql = "select * from mark where studentid=1 and courseid=1";
//            PreparedStatement stm = connection.prepareStatement(sql);
//            ResultSet rs = stm.executeQuery();
//            while (rs.next()) {
//                Mark m = new Mark();
//                m.setId(rs.getInt("id"));
//                m.setStudent(getStudentByID(1));
//                m.setCourse(getCourseByID(1));
//                m.setGradeCategory(rs.getString("grade_category"));
//                m.setGradeItem(rs.getString("grade_item"));
//                m.setWeight(rs.getFloat("weight"));
//                m.setValue(rs.getFloat("value"));
//                m.setComment(rs.getString("comment"));
//                marks.add(m);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(MarkDBContext.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return marks;
//    }

    @Override
    public void insert(Mark entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Mark entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Mark entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Mark get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
