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
       MarkDBContext mdbc = new MarkDBContext();
        ArrayList<Group> selectTermToSeeMark = mdbc.getSelectTermToSeeMark("cuonghv", "fa23");
        System.out.println(mdbc.getMarkForTotal("cuonghv", 11).size());
    }
}
