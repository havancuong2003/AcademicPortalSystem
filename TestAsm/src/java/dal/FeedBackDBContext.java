/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import jakarta.servlet.jsp.jstl.sql.Result;
import java.util.ArrayList;
import model.FeedBack;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Course;
import model.Group;
import model.Room;
import model.Student;
import model.Teacher;
import model.Term;
import model.TimeSlot;

/**
 *
 * @author -MSI-
 */
public class FeedBackDBContext extends DBContext<FeedBack> {

    public ArrayList<FeedBack> getAllFeedBackForStudent(String userName) {
        ArrayList<FeedBack> feedBacks = new ArrayList<>();
        try {
            String sql = "select f.id,content,studentid,groupid,[status] from feedback f join student s on s.id=f.studentid where s.userName = ? and [status] = 0";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, userName);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                FeedBack f = new FeedBack();
                f.setId(rs.getInt("id"));
                f.setContent(rs.getString("content"));
                f.setStatus(rs.getBoolean("status"));
                f.setStudent(getStudentByID(rs.getString("studentid")));
                f.setGroup(getGroupByID(rs.getInt("groupid")));
                feedBacks.add(f);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FeedBackDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return feedBacks;
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

    public FeedBack getFeedBackByID(int id, String username) {
        FeedBack f = null;
        try {
            String sql = "select f.id,content,studentid,groupid,[status] from feedback f join student s on s.id=f.studentid where s.userName =? and f.id=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(2, id);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                f = new FeedBack();
                f.setId(rs.getInt("id"));
                f.setContent(rs.getString("content"));
                f.setStudent(getStudentByID(rs.getString("studentid")));
                f.setGroup(getGroupByID(rs.getInt("groupid")));
                f.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FeedBackDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return f;

    }

    public void updateFeedBack(int fid, String content) {
        try {
            String sql = "update feedback\n"
                    + "set content = ?,status = 1\n"
                    + "where id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, content);
            stm.setInt(2, fid);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FeedBackDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<FeedBack> getAllFeedBackForStudentUpdate(String userName) {
        ArrayList<FeedBack> feedBacks = new ArrayList<>();
        try {
            String sql = "select f.id,content,studentid,groupid,[status] from feedback f join student s on s.id=f.studentid where s.userName = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, userName);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                FeedBack f = new FeedBack();
                f.setId(rs.getInt("id"));
                f.setContent(rs.getString("content"));
                f.setStatus(rs.getBoolean("status"));
                f.setStudent(getStudentByID(rs.getString("studentid")));
                f.setGroup(getGroupByID(rs.getInt("groupid")));
                feedBacks.add(f);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FeedBackDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return feedBacks;
    }

    public ArrayList<FeedBack> lectureSeeAllFeedBack(String username) {
        ArrayList<FeedBack> feedBacks = new ArrayList<>();
        try {
            String sql = "select f.id,content,studentid,groupid,[status] from feedback f join [group] g on g.id=f.groupid\n"
                    + "join Lecture l on l.id=g.lectureid\n"
                    + "where l.userName=?\n"
                    + "";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                FeedBack f = new FeedBack();
                f.setId(rs.getInt("id"));
                f.setContent(rs.getString("content"));
                f.setStatus(rs.getBoolean("status"));
                f.setStudent(getStudentByID(rs.getString("studentid")));
                f.setGroup(getGroupByID(rs.getInt("groupid")));
                feedBacks.add(f);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FeedBackDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return feedBacks;
    }

    @Override
    public ArrayList<FeedBack> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(FeedBack entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(FeedBack entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(FeedBack entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public FeedBack get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
