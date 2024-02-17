/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.timetable;

import dal.AttendanceDBContext;
import java.util.ArrayList;
import model.Attendance;

/**
 *
 * @author -MSI-
 */
public class Main {

    public static void main(String[] args) {
        AttendanceDBContext a = new AttendanceDBContext();
        ArrayList<Attendance> listInfoStudent = a.listInfoStudent();
        for (Attendance attendance : listInfoStudent) {
            System.out.println(attendance.getSession().getGroup().getName());
        }
    }

}
