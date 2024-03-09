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
         MarkDBContext mdbc = new MarkDBContext();
        ArrayList<Mark> markForTotal = mdbc.getMarkForTotal("cuonghv", 1);
        for (Mark mark : markForTotal) {
            System.out.println(mark.getValue());
        }
        System.out.println("aaa");
        TotalMarkHelper t = new TotalMarkHelper();
        System.out.println(t.allNull(markForTotal,"4"));
        System.out.println(markForTotal.get(0).getGroup().getTerm().getId());
        System.out.println("a");
        System.out.println(t.hasNullValue(markForTotal,"4"));
    }

}
