/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;

/**
 *
 * @author -MSI-
 */
public class Main {

    public static void main(String[] args) {
      MarkDBContext m = new MarkDBContext();
        ArrayList<String> academicTranscript = m.getAcademicTranscript("he1");
        for (String string : academicTranscript) {
            System.out.println(string);
        }
    }
}
