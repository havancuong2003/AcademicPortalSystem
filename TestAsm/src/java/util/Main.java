/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import dal.FeedBackDBContext;
import java.util.ArrayList;
import model.FeedBack;

public class Main {

    public static void main(String[] args) {
        FeedBackDBContext f= new FeedBackDBContext();
        ArrayList<FeedBack> allFeedBackForStudent = f.getAllFeedBackForStudent("cuonghv");
        System.out.println(allFeedBackForStudent.size());
         }
}

   






