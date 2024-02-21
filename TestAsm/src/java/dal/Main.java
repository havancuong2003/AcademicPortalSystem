/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

/**
 *
 * @author -MSI-
 */
public class Main {
    public static void main(String[] args) {
        MarkDBContext mdb=new MarkDBContext();
        System.out.println(mdb.getMarkStudent().size());
    }
}
