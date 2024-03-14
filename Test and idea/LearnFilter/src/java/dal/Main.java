/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Account;

/**
 *
 * @author -MSI-
 */
public class Main {
    public static void main(String[] args) {
        AccountDBContext adb = new AccountDBContext();
         Account a = adb.getAccount("cuonghv", "123");
         System.out.println(a.getUserName());
    }
}
