/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.Student;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author -MSI-
 */
public class StudentDBContext extends DBContext<Student> {

    @Override
    public ArrayList<Student> list() {
        ArrayList<Student> students = new ArrayList<>();
        try {
            String sql = "select id,name,dob,email,imgUrl,userName from student";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getString("id"));
                s.setName(rs.getString("name"));
                s.setDob(rs.getDate("dob"));
                s.setEmail(rs.getString("email"));
                s.setImgUrl(rs.getString("imgUrl"));
                s.setUsername(rs.getString("userName"));
                students.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return students;
    }

    public ArrayList<Student> getStudentForSerach(String studentInfo) {
        ArrayList<Student> students = new ArrayList<>();
        try {
            String sql = "select id,name,dob,email,imgUrl,userName from Student \n"
                    + "\n"
                    + "where [name] like ? or id like ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + studentInfo + "%");
            stm.setString(2, "%" + studentInfo + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getString("id"));
                s.setName(rs.getString("name"));
                s.setDob(rs.getDate("dob"));
                s.setEmail(rs.getString("email"));
                s.setImgUrl(rs.getString("imgUrl"));
                s.setUsername(rs.getString("userName"));
                students.add(s);

            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return students;
    }

    public void updateStudentAfterEdit(String oldID, String newid, String name, String dob, String img, String email, String username) {
        try {
            String sql = "update Student\n"
                    + "set id = ? ,name= ? ,dob = ? ,imgUrl = ? ,email = ? ,userName = ? \n"
                    + "where id = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, newid);
            stm.setString(2, name);
            stm.setDate(3, java.sql.Date.valueOf(dob));
            stm.setString(4, img);
            stm.setString(5, email);
            stm.setString(6, username);
            stm.setString(7, oldID);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<Student> getAllStudentInGroup(String gid) {
        ArrayList<Student> students = new ArrayList<>();
        try {
            String sql = "select distinct st.id ,st.name ,st.imgUrl from Attendance a\n"
                    + "join Session s on a.session_id=s.id\n"
                    + "join [Group] g on g.id = s.group_id\n"
                    + "join student st on st.id=a.student_id\n"
                    + "and g.id=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, gid);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Student s = new Student();
                s.setId(rs.getString("id"));
                s.setName(rs.getString("name"));
                s.setImgUrl(rs.getString("imgUrl"));
                students.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return students;
    }
    public Student getInfoStudent(String username){
        Student s =null;
        try {
            String sql =  "select * from student where username = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                s= new Student();
                s.setId(rs.getString("id"));
                s.setName(rs.getString("name"));
                s.setDob(rs.getDate("dob"));
                s.setEmail(rs.getString("Email"));
                s.setImgUrl(rs.getString("imgUrl"));
                s.setUsername(rs.getString("username"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
        
    }
    
    @Override
    public void insert(Student entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Student entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Student entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Student get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
