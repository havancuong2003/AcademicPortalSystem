/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.Mark;

/**
 *
 * @author -MSI-
 */
public class Main {
    public static void main(String[] args) {
        MarkDBContext mdb=new MarkDBContext();
      //  ArrayList<Mark> markStudent = mdb.getMarkStudent(5, "cuonghv");
        ArrayList<Mark> markByTermAndCourse = mdb.getMarkByTermAndCourse("cuonghv","fa23", 12);
        System.out.println(markByTermAndCourse);
                
        
    }
}
