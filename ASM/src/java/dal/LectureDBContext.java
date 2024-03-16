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
import model.Session;

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
            stm.setString(1, "%" + lectureInfo + "%");
            stm.setString(2, "%" + lectureInfo + "%");
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

    public ArrayList<Teacher> getTeacherChangeSchedule(String date, int slotid) {
        ArrayList<Teacher> teachers = new ArrayList<>();
        try {
            String sql = "SELECT l.id, l.name\n"
                    + "FROM Lecture l\n"
                    + "WHERE l.id NOT IN (\n"
                    + "    SELECT s.lectureid\n"
                    + "    FROM [Group] g\n"
                    + "    INNER JOIN [Session] s ON g.id = s.group_id\n"
                    + "    WHERE s.date = ? -- Ngày cụ thể\n"
                    + "    AND g.timeSlotID = ? -- Slot thời gian cụ thể\n"
                    + ")";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setDate(1, java.sql.Date.valueOf(date));
            stm.setInt(2, slotid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Teacher t = new Teacher();
                t.setId(rs.getString("id"));
                t.setName(rs.getString("name"));
                teachers.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LectureDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return teachers;
    }

    public Teacher getLectureByID(String id) {
        Teacher t = null;
        try {
            String sql = "select * from lecture where id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                t = new Teacher();
                t.setId(rs.getString("id"));
                t.setName(rs.getString("name"));
                t.setDob(rs.getDate("dob"));
                t.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
        }
        return t;
    }

    public Session getSessionForChange(String date, int timeslot) {
        Session s = null;
        try {
            String sql = "select s.id,s.lectureid from Session s \n"
                    + "join [Group] g on s.group_id=g.id\n"
                    + "where s.date= ? and g.timeSlotID=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setDate(1, java.sql.Date.valueOf(date));
            stm.setInt(2, timeslot);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                s = new Session();
                s.setId(rs.getInt("id"));
                s.setTeacher(getLectureByID(rs.getString("lectureid")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(LectureDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    public void updateLectureChange(String lid, int sid) {
        try {
            String sql = "update [Session]\n"
                    + "  set lectureid = ?\n"
                    + "  where id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, lid);
            stm.setInt(2, sid);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LectureDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

      public Teacher getLectureByUserName(String username) {
        Teacher t = null;
        try {
            String sql = "select * from lecture where username = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                t = new Teacher();
                t.setId(rs.getString("id"));
                t.setName(rs.getString("name"));
                t.setDob(rs.getDate("dob"));
                t.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
        }
        return t;
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
