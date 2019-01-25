/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooad_assignment_1;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.util.*;
import java.io.*;
import java.sql.*;
import java.text.*;

/**
 *
 * @author Asad
 */
public class Library {    
//    FrontPage frontPage;
    public String name;
    public Library(String name)
    {
        this.name=name;
    }
    
    int login(String username,String password)
    {
        Connection con = null;
        Statement stmt = null;
        
        try
        {
            String s = "jdbc:sqlserver://localhost:1433;databaseName=LMS;user=sa;password=12345";
            con=DriverManager.getConnection(s);

            stmt = con.createStatement(); 
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
        //0 for user not found, 1 for successful log-in, 2 for user already logged in
        int status = 0;
        PreparedStatement pStmt = null;
        ResultSet rs = null;     
        
        try
        {
            rs=stmt.executeQuery("select * from Users");
            while(rs.next())
            {
                if(username.equals(rs.getString(1)) && password.equals(rs.getString(2)))
                {
                    status = 1;
                    rs=stmt.executeQuery("select * from LoggedIn");
                    if(!rs.isBeforeFirst())
                    {
                        pStmt = con.prepareStatement("insert into LoggedIn values(?)");
                        pStmt.setString(1,username);
                        pStmt.executeUpdate();
                    }
                    else
                    {
                        while(rs.next())
                        {
                             if(username.equals(rs.getString(1)))
                             {
                                 status = 2;
                                 break;
                             }
                        }
                        if(status == 1)
                        {
                            pStmt = con.prepareStatement("insert into LoggedIn values(?)");
                            pStmt.setString(1,username);
                            pStmt.executeUpdate();
                        }
                    }
                    break;
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return status;
    }
    
    boolean logout(String username)
    {
        Connection con = null;
        Statement stmt = null;
        PreparedStatement pStmt = null;
        int result = 0;
        
        try
        {
            String s = "jdbc:sqlserver://localhost\\LMS:1433;databaseName=LMS";
            con=DriverManager.getConnection(s,"sa","12345");

            stmt = con.createStatement(); 
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
        try
        {
            pStmt = con.prepareStatement("delete from LoggedIn where username=?");
            pStmt.setString(1, username);
            result = pStmt.executeUpdate();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
        if (result == 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
//    public static void main(String[] args) {
        
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//	try {
//                Date date = formatter.parse("29/02/2018");
//                String output = formatter.format(date);
//		System.out.println("Date is: " + output);
//                
//                Date issDate = new Date();
//                Date retDate = new Date(new Date().getTime() + 604800000);
//                output = formatter.format(issDate);
//		System.out.println("Today's Date is: " + output);
//                output = formatter.format(retDate);
//		System.out.println("10 days after, date would be: " + output);
//        } 
//        catch (ParseException e) 
//        {
//            e.printStackTrace();
//        }
//    }
}
