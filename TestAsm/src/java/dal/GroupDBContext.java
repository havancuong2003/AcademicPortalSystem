/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.Group;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Course;
import model.Mark;
import model.Term;

/**
 *
 * @author -MSI-
 */
public class GroupDBContext extends DBContext<Group> {

    public ArrayList<Group> getAllGroupOfLecture(String username) {
        ArrayList<Group> groups = new ArrayList<>();
        try {
            String sql = "select distinct g.id as gid,g.name as groupName,g.courseId as courseID,g.termID as termID from student_group sg join [Group] g on sg.groupid=g.id\n"
                    + "join student s on s.id=sg.Studentid\n"
                    + "join Lecture l on l.id=g.lectureid\n"
                    + "join mark_student ms on ms.student_group_id=sg.id\n"
                    + "join mark_course mc on mc.id=ms.mark_course_id\n"
                    + "where l.userName = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Group g = new Group();
                g.setId(rs.getInt("gid"));
                g.setName(rs.getString("groupName"));
                g.setCourse(getCourseByID(rs.getInt("courseID")));
                g.setTerm(getTermByID(rs.getString("termID")));
                groups.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return groups;

    }
// function nay dung de hien thi bang diem khi click vao xem diem cua 1 lop nao do cua giang vien.

    public ArrayList<Mark> getGradeTableForTeacher(String username, int groupId) {
        ArrayList<Mark> marks = new ArrayList<>();
        try {
            String sql = "select distinct g.id as gid,mc.id as mcid, g.courseId,mc.gradeCategory,mc.gradeItem,mc.[weight] from   mark_course mc join mark_student ms on ms.mark_course_id=mc.id\n"
                    + "\n"
                    + "join student_group sg on sg.id = ms.student_group_id\n"
                    + "join [Group] g on g.id=sg.groupid\n"
                    + "join Lecture l on g.lectureid=l.id\n"
                    + "join course c on c.id=g.courseId\n"
                    + "join student s on s.id = sg.Studentid\n"
                    + "\n"
                    + "where l.userName = ? and sg.groupid=?\n"
                    + "order by mcid";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setInt(2, groupId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Mark m = new Mark();
                m.setGroup(getGroupByID(rs.getInt("gid")));
                m.setGradeCategory(rs.getString("gradeCategory"));
                m.setGradeItem(rs.getString("gradeItem"));
                m.setWeight(Float.parseFloat(rs.getString("weight")));
                marks.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return marks;
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
//                g.setRoom(getRoomByID(rs.getInt("roomID")));
//                g.setTimeslot(getTimeSlotByID(rs.getInt("timeSlotID")));
//                g.setTeacher(getLectureByID(rs.getString("lectureid")));
//                g.setTimeStart(rs.getDate("timeStart"));
//                g.setTimeEnd(rs.getDate("timeEnd"));
//                g.setFirstDay(rs.getDate("firstday"));
//                g.setSecondDay(rs.getDate("secondday"));
            }
        } catch (SQLException e) {
        }
        return g;
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

    public ArrayList<Group> getListGroupForStudent(String username) {
        ArrayList<Group> groups = new ArrayList<>();
        try {
            String sql = "					select sg.groupid as id,g.name,c.id as cid from student_group sg\n"
                    + "                    join student s on sg.Studentid =s.id\n"
                    + "                    join [group] g on g.id = sg.groupid\n"
                    + "					join course c on c.id=g.courseId\n"
                    + "                    where s.userName =? and g.termID =4";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Group g = new Group();
                g.setId(rs.getInt("id"));
                g.setName(rs.getString("name"));
                g.setCourse(getCourseByID(rs.getInt("cid")));
                groups.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return groups;
    }

    public ArrayList<Group> getListGroupForLecture(String username) {
        ArrayList<Group> groups = new ArrayList<>();
        try {
            String sql = "select g.id,g.name,c.id as cid from [group] g \n"
                    + "                    join Lecture l on g.lectureid = l.id\n"
                    + "					join course c on c.id=g.courseId\n"
                    + "                    where l.userName =?  and g.termID=4";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Group g = new Group();
                g.setId(rs.getInt("id"));
                g.setName(rs.getString("name"));
                g.setCourse(getCourseByID(rs.getInt("cid")));
                groups.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return groups;
    }

    public int getRole(String username) {
        int role = 0;
        try {
            String sql = "select rolid from account where userName =?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                role = rs.getInt("rolid");
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return role;
    }

    public ArrayList<Group> getGroupToChangeClassStudent(String username) {
        ArrayList<Group> groups = new ArrayList<>();

        try {
            String sql = "select g.id as groupid,g.courseid as cid,g.name from student_group sg join [group] g on sg.groupid = g.id\n"
                    + "join student s on s.id =sg.studentid\n"
                    + "where g.termid = 2 and s.userName =?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs  = stm.executeQuery();
            while(rs.next()){
                Group g = new Group();
                g.setId(rs.getInt("groupid"));
                g.setName(rs.getString("name"));
                g.setCourse(getCourseByID(rs.getInt("cid")));
                groups.add(g);
            }

        } catch (SQLException ex) {
            Logger.getLogger(GroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return groups;
    }

    @Override
    public ArrayList<Group> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Group entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Group entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Group entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Group get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
