/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.Group;

/**
 *
 * @author -MSI-
 */
public class Main {

    public static void main(String[] args) {
      SendingEmailDBContext s =new SendingEmailDBContext();
        System.out.println(s.getAllStudentAbsentThan10().size());
    }
}
