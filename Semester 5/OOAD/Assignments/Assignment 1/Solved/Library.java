/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lms_final;
import java.io.*;
import java.util.*;
import java.sql.*;

/**
 *
 * @author Asad
 */
public class Library {
    static ArrayList<Book> books;
    static ArrayList<User> users;
    
    //record of all loans, current and previously loaned
    static ArrayList<Loan> loans;
    static Connection con;
    static Statement stmt;
    static PreparedStatement pStmt;
    
    Library()
    {
        //User
        String username = null;
        String password = null;
        String name = null;
        String address = null;
        long phone = 0;
        String type = null;
        User tempUser = null;
        int fine = 0;
        
        //Book
        int id;         //since ISBN is given to the title of the book, not individual copies
        String isbn;
        String bookName;
        String author;
        String subject;
        boolean onLoan;
        Book tempBook = null;
        
        //Loan
        int bookId;
        //username and isbn variables will be used 
        java.util.Date issD;
        java.util.Date retD;
        Loan tempLoan = null;
        
        books = new ArrayList<>();
        users = new ArrayList<>();
        loans = new ArrayList<>();
        
        try
        {
            String s = "jdbc:sqlserver://localhost\\DESKTOP-O2UFFF2:1433;databaseName=LMS;integrated‌​Security=true";
            con = DriverManager.getConnection(s,"sa","123");

            stmt = con.createStatement();
            
            //populating loans arraylist
            ResultSet rs=stmt.executeQuery("select * from Loans");
            while(rs.next())
            {
                bookId = rs.getInt(1);
                isbn = rs.getString(2);
                username = rs.getString(3);
                issD = rs.getDate(4);
                retD = rs.getDate(5);
                onLoan = rs.getBoolean(6);
                tempLoan = new Loan(bookId, isbn, username, issD, retD);
                tempLoan.setStatus(onLoan);
                loans.add(tempLoan);
            }
            
            //populating books arraylist
            rs=stmt.executeQuery("select * from Books");
            while(rs.next())
            {
                isbn = rs.getString(1);
                bookName = rs.getString(2);
                author = rs.getString(3);
                subject = rs.getString(4);
                tempBook = new Book(isbn, bookName, author, subject);
                books.add(tempBook);
            }  
            
            //Populating users arraylist
            rs=stmt.executeQuery("select * from Users");
            while(rs.next())
            {
                username = rs.getString("username");
                password = rs.getString("password");
                name = rs.getString("name");
                address = rs.getString("address");
                phone = rs.getLong("phone");
                type = rs.getString("type");
                fine = rs.getInt("fine");
                if(type.equals("B"))
                {
                    tempUser = new Borrower(username, password, name, address, phone, fine);
                }
                else if(type.equals("C"))
                {
                    tempUser = new Clerk(username, password, name, address, phone, fine);
                }
                else if(type.equals("L"))
                {
                    tempUser = new Librarian(username, password, name, address, phone, fine);
                }
                users.add(tempUser);
                tempUser = null;
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }
    
    void filterSameBooks(ArrayList<Book> listObj)
    {
        for(int i = 0; i < listObj.size(); i++)
        {
            for(int j = 0; j < listObj.size(); j++)
            {
                if(listObj.get(i).isEqual(listObj.get(j)) && j != i)
                {
                    listObj.get(i).ids.add(listObj.get(j).ids.get(0));
                    listObj.remove(j);
                }
            }
        }
    }
    
    int login(String username, String password)
    {        
        //0 for user not found
        //1 for borrower logged in
        //2 for clerk logged in
        //3 for librarian logged in
        int status = 0;
        
        for(int i = 0; i < users.size(); i++)
        {
            if(users.get(i).username.equals(username) && users.get(i).password.equals(password))
            {
                if(users.get(i) instanceof Borrower)
                {
                    status = 1;
                }
                else if(users.get(i) instanceof Clerk)
                {
                    status = 2;
                }
                else
                {
                    status = 3;
                }
            }
        }
        
        return status;
    }
}
