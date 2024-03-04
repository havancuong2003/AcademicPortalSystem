/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.Teacher;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author -MSI-
 */
public class LectureDBContext extends DBContext<Teacher> {

    public ArrayList<Teacher> getTeacherForSearch(String lectureInfo) {
        ArrayList<Teacher> teachers = new ArrayList<>();
        try {
            String sql = "select id,name,dob,email,username from lecture where username like ? or id  like ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%"+lectureInfo+"%");
            stm.setString(2, "%"+lectureInfo+"%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Teacher t = new Teacher();
                t.setId(rs.getString("id"));
                t.setName(rs.getString("name"));
                t.setDob(rs.getDate("dob"));
                t.setEmail(rs.getString("email"));
                t.setUsername(rs.getString("username"));
                teachers.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LectureDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return teachers;
    }

    public void updateLectureAfterEdit(String oldID, String newid, String name, String dob, String email, String username) {
        try {
            String sql = "update lecture\n"
                    + "set id = ? ,name= ? ,dob = ?  ,email = ? ,userName = ? \n"
                    + "where id = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, newid);
            stm.setString(2, name);
            stm.setDate(3, java.sql.Date.valueOf(dob));
            stm.setString(4, email);
            stm.setString(5, username);
            stm.setString(6, oldID);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void insert(Teacher entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Teacher entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Teacher entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Teacher get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Teacher> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
