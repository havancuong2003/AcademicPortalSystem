/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.ArrayList;
import model.Student;

/**
 *
 * @author -MSI-
 */
public class getStudentHelper {

    public Student getStudentById(String id) {
        ArrayList<Student> students = new ArrayList<>();
        Student s1 = new Student("he2", "Linh Do", "dothuylinh2673@gmail.com", "MAS");
        Student s2 = new Student("he1", "Ha Cuong", "cuonghvhe176362@fpt.edu.vn", "PRJ");
        students.add(s1);
        students.add(s2);
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

}
