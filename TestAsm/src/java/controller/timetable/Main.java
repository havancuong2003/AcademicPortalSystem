/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.timetable;

import dal.AttendanceDBContext;
import java.util.ArrayList;
import model.Attendance;
import model.Course;
import model.Session;
import model.TimeSlot;

/**
 *
 * @author -MSI-
 */
public class Main {

    public static void main(String[] args) {
        AttendanceDBContext a = new AttendanceDBContext();
        ArrayList<Session> listInfoLecture = a.listInfoLecture();
        for (Session session : listInfoLecture) {
            //System.out.println(session.getGroup().getTimeslot().getDescription());
        }
        ArrayList<Attendance> listInfoStudent = a.listInfoStudent();
       int count =0;
        for (Attendance attendance : listInfoStudent) {
            System.out.println(attendance.getSession().getGroup().getTeacher().getName());
        }
        System.out.println(count);
        //System.out.println(listInfoLecture.size());
//        TimeSlot t=a.getTimeSlotByID(1);
//        System.out.println(t.getDescription());
        
    }

}
