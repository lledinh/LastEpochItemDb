package com.ledinh.lastepochparser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class FillDatabase {
    public static void main(String args[]){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://192.168.1.43:3306/LastEpochItemDB?useTimezone=true&serverTimezone=GMT%2B8","lam","Azertyuiop123!");

            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from Affix");
            while(rs.next())
                System.out.println(rs.getInt(1));
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }
}
