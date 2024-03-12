/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.Group;
import model.Session;

/**
 *
 * @author -MSI-
 */
public class Main {

    public static void main(String[] args) {
        GroupDBContext g = new GroupDBContext();
      AccountDBContext a = new AccountDBContext();
        System.out.println(a.getStudentIDByUserName("cuonghv"));
    }
}
