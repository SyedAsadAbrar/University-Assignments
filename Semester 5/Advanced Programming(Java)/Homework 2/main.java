/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ap_homework_3;
import java.io.*;
import java.util.*;
import java.sql.*;

/**
 *
 * @author Asad
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connection con;
        PreparedStatement pStmt;
        CallableStatement cStmt;  
        
        //PART A
        
        try
        {
	    //Databse connectivity
            String s = "jdbc:sqlserver://localhost\\DESKTOP-O2UFFF2:1433;databaseName=db1;integrated‌​Security=true";
            con = DriverManager.getConnection(s,"sa","123");

            pStmt = con.prepareStatement("Insert into Students values(?,?,?,?)");
            
            //Name, Roll Number, Section (all are strings)
            //the first column has serial no of students starting from 1
            
            pStmt.setInt(1, 1);
            pStmt.setString(2, "Syed Asad Abrar");
            pStmt.setString(3, "L16-4292");
            pStmt.setString(4, "A");
            
            int retVal = pStmt.executeUpdate();
            
            //set on autocommit, so records will be updated
            
            pStmt = con.prepareStatement("Update Students set Name = ?, RollNo = ?, Section = ? where ID = 1");
            
            pStmt.setString(1, "Syed Asad Abrar");
            pStmt.setString(2, "L16-4292");
            pStmt.setString(3, "A");
            
            retVal = pStmt.executeUpdate();
            
            //PART B
            
            //PROCEDURE
            //
            //GO 
            //CREATE PROCEDURE ReturnRollNo 
            //@Name varchar(50), 
            //@RollNo varchar(50) OUTPUT 
            //AS 
            //Select @RollNo = RollNo from Students where Name = @Name; 
            //RETURN 
            //GO
            
            cStmt = con.prepareCall("{call ReturnRollNo(?, ?)}");

            cStmt.setString(1, "Syed Asad Abrar");
            
            cStmt.registerOutParameter(2, Types.VARCHAR);

            cStmt.execute();
            
            //output value is returned here
            String rollNo = cStmt.getString(2);
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        
        
    }
    
}
