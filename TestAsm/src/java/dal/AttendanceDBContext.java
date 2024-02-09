/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.Attendance;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Course;
import model.Group;
import model.Room;
import model.Session;
import model.Student;
import model.Teacher;
import model.TimeSlot;

/**
 *
 * @author -MSI-
 */
public class AttendanceDBContext extends DBContext<Attendance> {

    @Override
    public ArrayList<Attendance> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<Session> listInfoTeacher() {
        ArrayList<Session> sessions = new ArrayList<>();
        try {
               String sql = "select s.id, st.name,c.name as coursename,g.id as gid,"
                    + "r.id as rid,r.name as roomname,a.status,a.description,"
                    + "s.timeSlot_id,t.id as teacherid,t.name as TeacherName,"
                    + "s.date from Attendance a "
                    + "join Session s on a.session_id=s.id "
                    + "join Student st on st.id=a.student_id "
                    + "join Teacher t on t.id=s.lecture_id "
                    + "join Room r on r.id=s.room_id "
                    + "join [Group] g on g.id=s.group_id "
                    + "join course c on c.id=g.course_id";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Session s = new Session();
                s.setId(rs.getInt("id"));
                s.setStatus(rs.getBoolean("status"));
                s.setGroup(rs.getObject("gid", Group.class));
                s.setRoom(rs.getObject("rid", Room.class));
                s.setTimeSlot(rs.getObject("timeSlot_id", TimeSlot.class));
                s.setTeacher(rs.getObject("teacherid", Teacher.class));
                s.setDate(rs.getDate("date"));
                sessions.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sessions;
    }

    @Override
    public void insert(Attendance entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Attendance entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Attendance entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Attendance get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
