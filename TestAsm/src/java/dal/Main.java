/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.Session;

/**
 *
 * @author -MSI-
 */
public class Main {

    public static void main(String[] args) {
        AttendanceDBContext a = new AttendanceDBContext();
        ArrayList<Session> listInfoLecture = a.listInfoLecture("yennth");
        System.out.println(listInfoLecture.get(0).getGroup().getCourse().getCode());
    }
}
