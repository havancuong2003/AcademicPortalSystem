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
                c.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
        }
        return c;
    }
    
    public Group getGroupByID(int id) {
        Group g = null;
        try {
            String sql = "select * from group where id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                g = new Group();
                g.setId(rs.getInt("id"));
                g.setName(rs.getString("name"));
                g.setCourse(getCourseByID(rs.getInt("course_id")));
            }
        } catch (SQLException e) {
        }
        return g;
    }
    
    public Room getRoomByID(int id) {
        Room r = null;
        try {
            String sql = "select * from room where id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                r = new Room();
                r.setId(rs.getInt("id"));
                r.setDesription(rs.getString("name"));
                
            }
        } catch (SQLException e) {
        }
        return r;
    }
    
    public TimeSlot getTimeSlotByID(int id) {
        TimeSlot ts = null;
        try {
            String sql = "select * from timeslot where id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                ts = new TimeSlot();
                ts.setId(rs.getInt("id"));
                ts.setDescription(rs.getString("description"));
            }
        } catch (SQLException e) {
        }
        return ts;
    }
    
    public Teacher getLectureByID(int id) {
        Teacher t = null;
        try {
            String sql = "select * from teacher where id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                t = new Teacher();
                t.setId(rs.getInt("id"));
                t.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
        }
        return t;
    }
    
    public Session getSessionByID(int id) {
        Session s = null; // Khởi tạo đối tượng Session là null
        try {
            String sql = "select * from Session where id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                s = new Session();
                s.setId(rs.getInt("id"));
                s.setStatus(rs.getBoolean("status"));
                s.setGroup(getGroupByID(rs.getInt("group_id")));
                s.setRoom(getRoomByID(rs.getInt("room_id")));
                s.setTimeSlot(getTimeSlotByID(rs.getInt("timeslot_id")));
                s.setTeacher(getLectureByID(rs.getInt("lecture_id")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
    
    public ArrayList<Attendance> listInfoStudent() {
        ArrayList<Attendance> attendances = new ArrayList<>();
        
        try {
            String sql = "select a.id as attendanceID,st.id as studentID,s.id as sessionID,a.status,a.description, st.name as studentName, c.name as courseName,g.name as groupName,ts.id as slot from Attendance a\n"
                    + "join student st on a.student_id=st.id\n"
                    + "join [Session] s on s.id=a.session_id\n"
                    + "join [group] g on g.id=s.group_id\n"
                    + "join room r on r.id=s.room_id\n"
                    + "join course c on c.id =g.course_id\n"
                    + "join TimeSlot ts on ts.id=s.timeSlot_id";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                
                Attendance a = new Attendance();
                a.setId(rs.getInt("attendanceID"));
                a.setDescription(rs.getString("description"));
                a.setStatus(rs.getBoolean("status"));
                Student s = new Student();
                s.setId(rs.getInt("studentID"));
                s.setName(rs.getString("studentName"));
                a.setStudent(s);
                a.setSession(getSessionByID(rs.getInt("sessionID")));
                attendances.add(a);
            }
        } catch (SQLException e) {
        }
        return attendances;
    }
    
    public int test() {
        ArrayList<Student> students = new ArrayList<>();
        try {
            String sql = "select id,name from student";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                students.add(s);
            }
        } catch (SQLException e) {
        }
        return students.size();
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
