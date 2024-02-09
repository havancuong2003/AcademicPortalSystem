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

/**
 *
 * @author -MSI-
 */
public class AttendanceDBContext extends DBContext<Attendance> {

    @Override
    public ArrayList<Attendance> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<Attendance> listInfoStudent() {
        ArrayList<Attendance> attendances = new ArrayList<>();
        try {
            String sql = "  select st.name,c.name as coursename,r.name as roomname,a.status,a.description,s.timeSlot_id,t.name as TeacherName,s.date "
                    + "from Attendance a join Session s on a.session_id=s.id "
                    + "join Student st on st.id=a.student_id join Teacher t on t.id=s.lecture_id "
                    + "join Room r on r.id=s.room_id join [Group] g on g.id=s.group_id "
                    + "join course c on c.id=g.course_id";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Attendance a = new Attendance();
                Student st = new Student();
                Session s = new Session();
                Teacher t=new Teacher();
                Group g =new Group();
                Course c=new Course();
                Room r =new Room();
                

            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return attendances;
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
