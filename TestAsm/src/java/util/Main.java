/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import dal.MarkDBContext;
import java.util.ArrayList;
import model.Mark;

/**
 *
 * @author -MSI-
 */
public class Main {
    public static void main(String[] args) {
        TotalMarkHelper t =new TotalMarkHelper();
        String statusMark = t.getStatusMark("cuonghv", 7);
       // System.out.println(statusMark);
        MarkDBContext mdbc =new MarkDBContext();
        ArrayList<Mark> markForTotal = mdbc.getMarkForTotal("cuonghv", 7);
     //   System.out.println(t.calculateAverageValue(markForTotal, "Final Exam Resit"));
     
     MarkDBContext m = new MarkDBContext();
     m.updateTotalForMark("cuonghv", 5, "5", "passs","nice");
    }
   
}
