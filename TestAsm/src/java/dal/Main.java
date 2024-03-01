/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import model.Attendance;
import model.Mark;

/**
 *
 * @author -MSI-
 */
public class Main {

    public static void main(String[] args) {
       AttendanceDBContext a  = new AttendanceDBContext();
        ArrayList<Attendance> listInfoStudent = a.listInfoStudent("cuonghv");
        System.out.println(listInfoStudent.get(0).getStatus());
    }
}
