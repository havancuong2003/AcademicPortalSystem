/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

/**
 *
 * @author -MSI-
 */
public class AccountDBContext extends DBContext<Account> {

    public Account getAccount(String username, String password) {
        Account a = null;
        try {
            String sql = "select username,[password],rolid from account\n"
                    + "where username like ? and [password] like ?";
            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                a = new Account();
                a.setUsername(username);
                a.setPassword(password);
                a.setRole(rs.getString("rolid"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;

    }

    public Account getAccountForChangePassWord(int role, String username) {
        Account a = null;
        try {
            String sql = "select username, [password],rolid from account\n"
                    + "where rolid = ? and userName = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, role);
            stm.setString(2, username);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                a = new Account();
                a.setUsername(rs.getString("userName"));
                a.setPassword(rs.getString("password"));
                a.setRole(rs.getString("rolid"));
                return a;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void changePassWord(String username, String password) {
        try {
            String sql = "update account\n"
                    + "set password = ?\n"
                    + "where username = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, password);
            stm.setString(2, username);
            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getEmailByUserName(String username) {
        String s ="";
        try {
            String sql = "SELECT COALESCE(s.email, l.email) AS email\n"
                    + "FROM Account a\n"
                    + "LEFT JOIN Student s ON a.userName = s.userName\n"
                    + "LEFT JOIN Lecture l ON a.userName = l.userName\n"
                    + "WHERE a.userName = ?";
            PreparedStatement stm =connection.prepareStatement(sql);
            stm.setString(1, username);
           ResultSet rs = stm.executeQuery();
           if(rs.next()){
               s = rs.getString("email");
           }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    @Override
    public ArrayList<Account> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Account entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Account entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Account entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Account get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
