/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.News;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author -MSI-
 */
public class NewsDBContext extends DBContext<News> {

    public ArrayList<News> getNewsByPage(int page) {
        ArrayList<News> newses = new ArrayList<>();
        try {
            String sql = "select id,title,content,timeUp from news\n"
                    + "where pagenum = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, page);
            ResultSet  rs = stm.executeQuery();
            while(rs.next()){
                News n =new News();
                n.setId(rs.getInt("id"));
                n.setTitle(rs.getString("title"));
                n.setContent(rs.getString("content"));
                n.setDateCreated(rs.getDate("timeUp"));
                newses.add(n);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewsDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  newses;
    }
    
    public int getTotalPage(){
        int a =0;
        try {
            String sql ="select top 1 pagenum from news order by pagenum desc";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                 a = rs.getInt("pagenum");
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewsDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

    @Override
    public ArrayList<News> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

    }

    @Override
    public void insert(News entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(News entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(News entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public News get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
