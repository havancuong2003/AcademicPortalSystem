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
import model.Room;
import model.Student;
import model.Teacher;
import model.Term;
import model.TimeSlot;

/**
 *
 * @author -MSI-
 */
public class MarkDBContext extends DBContext<Mark> {

    public ArrayList<Mark> getMarksForTeacher(String username, int courseId, String gradeCategory, String gradeItem) {
        ArrayList<Mark> marks = new ArrayList<>();
        try {
            String sql = "select s.id as studentID,s.[name] as studentName,mc.gradeCategory,mc.gradeItem,mc.[weight],ms.[value],ms.[comment] from student_group sg join [Group] g on sg.groupid=g.id\n"
                    + "join student s on s.id=sg.Studentid\n"
                    + "join Lecture l on l.id=g.lectureid\n"
                    + "join mark_student ms on ms.student_group_id=sg.id\n"
                    + "join mark_course mc on mc.id=ms.mark_course_id\n"
                    + "where l.userName = ?  and g.courseId=  ? and mc.gradeCategory = ?  and mc.gradeItem = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setInt(2, courseId);
            stm.setString(3, gradeCategory);
            stm.setString(4, gradeItem);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Mark m = new Mark();
                m.setStudent(getStudentByID(rs.getString("studentID")));
                m.setGradeCategory(rs.getString("gradeCategory"));
                m.setGradeItem(rs.getString("gradeItem"));
                m.setWeight(Float.parseFloat(rs.getString("weight")));
                m.setValue(rs.getString("value"));
                m.setComment(rs.getString("comment"));
                marks.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarkDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return marks;
    }

    public ArrayList<Mark> getMarkByTermAndCourse(String username, String termid, int courseid) {
        ArrayList<Mark> marks = new ArrayList<>();
        try {
            String sql = "select mc.gradeCategory as grade_category,mc.gradeItem as grade_item,mc.[weight],ms.[value],ms.comment,g.courseId,g.id as gid from mark_student ms \n"
                    + "join student_group sg on ms.student_group_id =sg.id\n"
                    + "join mark_course mc on mc.id=mark_course_id\n"
                    + "join [group] g on g.id=sg.groupid\n"
                    + "join Course c on c.id = mc.courseId\n"
                    + "join student s on sg.Studentid=s.id\n"
                    + "where s.userName =? and g.termID=? and c.id=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, termid);
            stm.setInt(3, courseid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Mark m = new Mark();
                m.setGradeCategory(rs.getString("grade_category"));
                m.setGradeItem(rs.getString("grade_item"));
                m.setWeight(rs.getFloat("weight"));
                m.setValue(rs.getString("value"));
                m.setComment(rs.getString("comment"));
                m.setGroup(getGroupByID(rs.getInt("gid")));
                marks.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarkDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return marks;
    }
//
//    public ArrayList<Mark> getMarkStudent(int gid, String username) {
//        ArrayList<Mark> marks = new ArrayList<>();
//        try {
//            String sql = "select m.id,sg.Studentid,sg.groupid,g.courseId,g.termID as termID,m.grade_category,m.grade_item,m.[weight],m.[value],m.comment  from mark m join student_group sg  on m.student_group_id=sg.id\n"
//                    + "join [group] g on sg.groupid=g.id\n"
//                    + "join student st on st.id=sg.Studentid\n"
//                    + "where st.userName like ? and groupid = ? ";
//            PreparedStatement stm = connection.prepareStatement(sql);
//            stm.setString(1, username);
//            stm.setInt(2, gid);
//            ResultSet rs = stm.executeQuery();
//            while (rs.next()) {
//                Mark m = new Mark();
//                m.setId(rs.getInt("id"));
//                m.setGradeCategory(rs.getString("grade_category"));
//                m.setGradeItem(rs.getString("grade_item"));
//                m.setWeight(rs.getFloat("weight"));
//
//                m.setValue(rs.getString("value"));
//                m.setComment(rs.getString("comment"));
//                m.setStudent(getStudentByID(rs.getString("studentid")));
//                m.setGroup(getGroupByID(rs.getInt("groupid")));
//                marks.add(m);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(MarkDBContext.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return marks;
//    }

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
                s.setUsername(rs.getString("userName"));
            }
        } catch (SQLException e) {
        }
        return s;
    }

    // insert mark for each grade item for each course
    public ArrayList<Mark> insertMarkForCourse(int gid, String gradeItem) {
        ArrayList<Mark> marks = new ArrayList<>();
        try {

            String sql = "select g.id as gid, s.id as studentid,s.[name],ms.[value],ms.comment,mc.gradeItem from [group] g \n"
                    + "join student_group sg on g.id =sg.groupid\n"
                    + "join mark_student ms on ms.student_group_id = sg.id\n"
                    + "join mark_course mc on mc.id=ms.mark_course_id\n"
                    + "join student s on s.id =sg.studentId\n"
                    + "where g.id=? and mc.gradeItem = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, gid);
            stm.setString(2, gradeItem);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Mark m = new Mark();

                m.setGroup(getGroupByID(rs.getInt("gid")));
                m.setStudent(getStudentByID(rs.getString("studentid")));
                m.setValue(rs.getString("value"));
                m.setComment(rs.getString("comment"));
                m.setGradeItem(rs.getString("gradeItem"));
                marks.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarkDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return marks;
    }
// ham ben tren chi de nhap thoi. xem thoi. ham nay de thuc hien viec insert vao database ne

    public void updateMarkOfStudent(String value, int gid, String gradeItem, String sid) {
        try {
            String sql = "update mark_student\n"
                    + "set [value] = ? \n"
                    + "where id = (select ms.id as msid from [group] g \n"
                    + "join student_group sg on g.id =sg.groupid\n"
                    + "join mark_student ms on ms.student_group_id = sg.id\n"
                    + "join mark_course mc on mc.id=ms.mark_course_id\n"
                    + "join student s on s.id =sg.studentId\n"
                    + "where g.id= ? and mc.gradeItem = ? and s.id = ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, value);
            stm.setInt(2, gid);
            stm.setString(3, gradeItem);
            stm.setString(4, sid);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MarkDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<Mark> getMarkForTotal(String username, int gid) {
        ArrayList<Mark> marks = new ArrayList<>();
        try {
            String sql = "select ms.id as msid,g.id as gid,mc.gradeCategory,mc.gradeItem,mc.weight,ms.[value],g.termID from mark_student ms\n"
                    + "join student_group sg on sg.id=ms.student_group_id\n"
                    + "join mark_course mc on mc.id=ms.mark_course_id\n"
                    + "join student s on sg.Studentid=s.id\n"
                    + "join [group] g on g.id=sg.groupid\n"
                    + "join course c on c.id=g.courseId\n"
                    + "\n"
                    + "where s.username= ? and g.id= ?\n";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setInt(2, gid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Mark m = new Mark();
                m.setId(rs.getInt("msid"));
                m.setGroup(getGroupByID(rs.getInt("gid")));
                m.setGradeCategory(rs.getString("gradeCategory"));
                m.setGradeItem(rs.getString("gradeItem"));
                m.setWeight(rs.getFloat("weight"));
                m.setValue(rs.getString("value"));
                marks.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarkDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return marks;
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
