/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import dal.StudentDBContext;
import java.util.ArrayList;
import model.Student;

/**
 *
 * @author -MSI-
 */
public class getStudentHelper {

    public  Student getStudentById(String id) {
        StudentDBContext sdbc =new StudentDBContext();
        ArrayList<Student> students = sdbc.list();
 
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }
 

}
