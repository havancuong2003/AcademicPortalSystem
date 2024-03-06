/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.Mark;
import util.TotalMarkHelper;

/**
 *
 * @author -MSI-
 */
public class Main {

    public static void main(String[] args) {
        MarkDBContext mdbc = new MarkDBContext();
        ArrayList<Mark> markForTotal = mdbc.getMarkForTotal("cuonghv", 11);
        TotalMarkHelper t = new TotalMarkHelper();
        Double calculateAverageValue = t.calculateAverageValue(markForTotal, "Final Exam Resit");
        System.out.println(calculateAverageValue);
        
        
        
        System.out.println("aaa");
        System.out.println(t.calculateCategoryTotal(markForTotal, "Final Exam"));
        System.out.println(t.calculateCategoryTotal(markForTotal, "Final Exam Resit"));
    }
}
