/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.attendance;

import dal.AttendanceDBContext;
import java.util.ArrayList;
import model.Attendance;

/**
 *
 * @author -MSI-
 */
public class Main {

    public static void main(String[] args) {
        AttendanceDBContext adb = new AttendanceDBContext();
        ArrayList<Attendance> s = adb.getStudentsBySessionID(1);
  
    }
}
