/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Session;

/**
 *
 * @author -MSI-
 */
public class Main {

    public static void main(String[] args) {
        LectureDBContext ldbc = new LectureDBContext();
        ldbc.updateLectureChange("10", 19);
        Session sessionForChange = ldbc.getSessionForChange("2024-03-06", 4);
        System.out.println(sessionForChange.getId());
      
    }
}
