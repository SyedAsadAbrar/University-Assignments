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
 * @author BC
 */
public class Ooad_assignment_1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.print("Username: ");
        String username = reader.nextLine();
        System.out.print("Password: ");

        String password = reader.nextLine();
        Library l=new Library("Justice Gul Muhammad Library");
        int check=l.login(username,password);
        if(check == 1)
        {
            String[] type=new String[3];
            type[0]="B";
            type[1]="C";
            type[2]="L";
            System.out.println("Login Success");
            ResultSet rs=stmt.executeQuery("select * from LoggedIn");
            if(rs.getString(6).equals(type[0]))
            {
                Borrower B=new Borrower(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
                B.DisplayScreen();
                
            }
            else if(rs.getString(6).equals(type[1]))
            {
                Clerk C=new Clerk(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
                C.DisplayScreen();                
            }
            else if(rs.getString(6).equals(type[2]))
            {
                Librarian L=new Librarian(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
                L.DisplayScreen();                
            }
            
        }
        else
        {
            System.out.println("Login Failure");            
        }
//        String n = reader.nextLine();   //B for Borrower, C for Clerk,L for Librarian
//        if(n == "B")
//        {            
//        }        
//        if(n == "C")
//        {            
//        }
//        if(n == "L")
//        {            
//        }

    }
    
}
