/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.ChangeClass;

/**
 *
 * @author -MSI-
 */
public class Main {

    public static void main(String[] args) {
        GroupDBContext g = new GroupDBContext();
      AccountDBContext a = new AccountDBContext();
        System.out.println(a.getStudentIDByUserName("cuonghv"));
        
          ChangeClassDBContext ccdbc = new ChangeClassDBContext();
        ArrayList<ChangeClass> allRequiredForToStudent = ccdbc.getAllRequiredForToStudent("cuonghv");
        System.out.println(allRequiredForToStudent.size());
        
    }
}
