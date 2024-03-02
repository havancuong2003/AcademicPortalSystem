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
import model.Term;
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
                g.setRoom(getRoomByID(rs.getInt("roomID")));
                g.setTimeslot(getTimeSlotByID(rs.getInt("timeSlotID")));
                g.setTeacher(getLectureByID(rs.getString("lectureid")));
                g.setTimeStart(rs.getDate("timeStart"));
                g.setTimeEnd(rs.getDate("timeEnd"));
                g.setFirstDay(rs.getDate("firstday"));
                g.setSecondDay(rs.getDate("secondday"));
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
                r.setDescription(rs.getString("name"));

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
                s.setStatus(rs.getString("status"));
                s.setGroup(getGroupByID(rs.getInt("group_id")));
                s.setDate(rs.getDate("date"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    public ArrayList<Attendance> getStudentsBySessionID(int id) {
        ArrayList<Attendance> students = new ArrayList<>();
        try {
            String sql = "select * from attendance \n"
                    + "where session_id=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Attendance a = new Attendance();
                a.setStudent(getStudentByID(rs.getString("student_id")));
                a.setStatus(rs.getString("status"));
                a.setSession(getSessionByID(rs.getInt("session_id")));
                a.setId(rs.getInt("id"));
                a.setDescription(rs.getString("description"));
                students.add(a);
            }
        } catch (SQLException e) {
        }
        return students;
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

    public ArrayList<Attendance> listInfoStudent(String username) {
        ArrayList<Attendance> attendances = new ArrayList<>();

        try {
            String sql = "select a.id as attendanceid,a.[status],a.[description],a.student_id,a.session_id from attendance a\n"
                    + "join student s on a.student_id = s.id\n"
                    + "where s.userName like ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                Attendance a = new Attendance();
                a.setId(rs.getInt("attendanceid"));
                a.setDescription(rs.getString("description"));

                if ("true".equals(rs.getString("status"))) {
                    a.setStatus("Present");
                } else if ("false".equals(rs.getString("status"))) {
                    a.setStatus("Absent");
                } else {
                    a.setStatus("not yet");
                }

                a.setStudent(getStudentByID(rs.getString("student_id")));
                a.setSession(getSessionByID(rs.getInt("session_id")));
                attendances.add(a);
            }
        } catch (SQLException e) {
        }
        return attendances;
    }

    public ArrayList<Session> listInfoLecture(String username) {
        ArrayList<Session> sessions = new ArrayList<>();
        try {
            String sql = "select s.id,s.date,s.group_id,s.status from [Session] s join [Group] g\n"
                    + " on g.id=s.group_id \n"
                    + " join Lecture l on l.id=g.lectureid\n"
                    + " where l.userName like ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Session s = new Session();
                s.setId(rs.getInt("id"));
                s.setDate(rs.getDate("date"));
                s.setGroup(getGroupByID(rs.getInt("group_id")));

                if ("1".equals(rs.getString("status"))) {
                    s.setStatus("attendanced");
                } else {
                    s.setStatus("Take attendance");
                }

                sessions.add(s);
            }
        } catch (SQLException e) {
        }
        return sessions;
    }

    public void updateAttendanceStatus(ArrayList<Attendance> attendances, int id) {
        try {
            for (Attendance a : attendances) {
                String sql = "update Attendance "
                        + "set status = ? ,description = ? "
                        + "where session_id = ? and student_id = ?; "
                        + "update [Session] "
                        + "set status = ? "
                        + "where id = ?;";
                PreparedStatement stm = connection.prepareStatement(sql);

                stm.setString(1, a.getStatus());
                stm.setString(2, a.getDescription());
                stm.setInt(3, id);
                stm.setString(4, a.getStudent().getId());
                stm.setString(5, "true");
                stm.setInt(6, id);
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            // Xử lý ngoại lệ SQL
        }
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
