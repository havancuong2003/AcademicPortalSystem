/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.Mark;
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
public class MarkDBContext extends DBContext<Mark> {

    public ArrayList<Mark> getMarkByTermAndCourse(String username, String termid, int courseid) {
         ArrayList<Mark> marks =new ArrayList<>();
        try {
            String sql = "select m.grade_category,m.grade_item,m.[weight],m.[value],m.comment,g.courseId,g.id as gid from mark m join student_group sg on m.student_group_id = sg.id\n" +
" join Student s on s.id=sg.Studentid join [group] g on g.id=sg.groupid\n" +
" where s.userName like ? and g.courseId = ? and g.termID like ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(3, termid);
            stm.setInt(2, courseid);
            ResultSet rs =stm.executeQuery();
            while(rs.next()){
               Mark m= new Mark();
                m.setGradeCategory(rs.getString("grade_category"));
                m.setGradeItem(rs.getString("grade_item"));
                m.setWeight(rs.getFloat("weight"));
                m.setValue(rs.getFloat("value"));
                m.setComment(rs.getString("comment"));
                m.setGroup(getGroupByID(rs.getInt("gid")));
                marks.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarkDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return marks;
    }

    public ArrayList<Mark> getMarkStudent(int gid, String username) {
        ArrayList<Mark> marks = new ArrayList<>();
        try {
            String sql = "select m.id,sg.Studentid,sg.groupid,g.courseId,g.termID as termID,m.grade_category,m.grade_item,m.[weight],m.[value],m.comment  from mark m join student_group sg  on m.student_group_id=sg.id\n"
                    + "join [group] g on sg.groupid=g.id\n"
                    + "join student st on st.id=sg.Studentid\n"
                    + "where st.userName like ? and groupid = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setInt(2, gid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Mark m = new Mark();
                m.setId(rs.getInt("id"));
                m.setGradeCategory(rs.getString("grade_category"));
                m.setGradeItem(rs.getString("grade_item"));
                m.setWeight(rs.getFloat("weight"));

                m.setValue(rs.getFloat("value"));
                m.setComment(rs.getString("comment"));
                m.setStudent(getStudentByID(rs.getString("studentid")));
                m.setGroup(getGroupByID(rs.getInt("groupid")));
                marks.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarkDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return marks;
    }

    public ArrayList<Group> getTermOfStudentLearned(String username) {
        ArrayList<Group> groups = new ArrayList<>();
        try {
            String sql = "select distinct g.termID from [group] g join student_group sg on g.id=sg.groupid join Student s on s.id=sg.Studentid and s.userName like ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Group g = new Group();
                g.setTerm(getTermByID(rs.getString("termID")));
                groups.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarkDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return groups;

    }

    public ArrayList<Group> getSelectTermToSeeMark(String username, String termId) {
        ArrayList<Group> groups = new ArrayList<>();
        try {
            String sql = "select g.termID,g.courseId,g.[name],g.timeStart,g.timeEnd from [group] g \n"
                    + "	join student_group sg on sg.groupid=g.id\n"
                    + "	join student s on s.id=sg.Studentid\n"
                    + "	where s.userName like ? and g.termId like ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, termId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Group g = new Group();
                g.setTerm(getTermByID(rs.getString("termID")));
                g.setCourse(getCourseByID(rs.getInt("courseId")));
                g.setName(rs.getString("name"));
                g.setTimeStart(rs.getDate("timeStart"));
                g.setTimeEnd(rs.getDate("timeEnd"));
                groups.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarkDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return groups;
    }

//    public ArrayList<Group> getGroupIdToTakeCourse() {
//        ArrayList<Group> groups = new ArrayList<>();
//        try {
//            String sql = "select distinct sg.groupid,g.courseId  \n"
//                    + "from mark m join student_group sg  on m.student_group_id=sg.id\n"
//                    + "                   join [group] g on sg.groupid=g.id\n"
//                    + "                   join student st on st.id=sg.Studentid\n"
//                    + "                   where st.userName like 'cuonghv' ";
//            PreparedStatement stm = connection.prepareStatement(sql);
//            ResultSet rs = stm.executeQuery();
//            while (rs.next()) {
//                Group g = new Group();
//                g.setId(rs.getInt("groupid"));
//                g.setCourse(getCourseByID(rs.getInt("courseId")));
//                groups.add(g);
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(MarkDBContext.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return groups;
//    }

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
//                g.setRoom(getRoomByID(rs.getInt("roomID")));
//                g.setTimeslot(getTimeSlotByID(rs.getInt("timeSlotID")));
//                g.setTeacher(getLectureByID(rs.getString("lectureid")));
                g.setTimeStart(rs.getDate("timeStart"));
                g.setTimeEnd(rs.getDate("timeEnd"));
                g.setFirstDay(rs.getDate("firstday"));
                g.setSecondDay(rs.getDate("secondday"));
            }
        } catch (SQLException e) {
        }
        return g;
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
                s.setUsername(rs.getString("userName"));
            }
        } catch (SQLException e) {
        }
        return s;
    }

    @Override
    public ArrayList<Mark> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

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
