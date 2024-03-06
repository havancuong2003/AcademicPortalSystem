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
        TotalMarkHelper t = new TotalMarkHelper();
        ArrayList<Mark> markForTotal = mdbc.getMarkForTotal("cuonghv", 10);
//        for (Mark mark : markForTotal) {
//            System.out.println(mark.getGradeCategory()+" - "+t.calculateAverageValue(markForTotal,mark.getGradeCategory()));
//        }
        System.out.println(t.getStatusMark("cuonghv", 8));
    }
}
