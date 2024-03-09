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
        System.out.println(t.calculateTotalAbsences("cuonghv", 11));
        System.out.println(t.calculateTotalClasses("cuonghv", 11));
    }
}
