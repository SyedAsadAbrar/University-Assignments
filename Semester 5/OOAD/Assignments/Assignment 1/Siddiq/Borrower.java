/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooad_assignment_1;
import java.util.*;
import java.io.*;
import java.sql.*;
import java.text.*;
import static ooad_assignment_1.Clerk.searchBookbyName;

/**
 *
 * @author Asad
 */
public class Borrower extends User{
    //ArrayList<Loan> loanedBooks;
    
    //Constructors
    Borrower()
    {
        username = password = null;
        name = null;
        address = null;
        phone = 0;
        //loanedBooks = null;
    }
    Borrower(String username, String password, String name, String address, int phone)
    {
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.phone = phone;
        //loanedBooks = new ArrayList<Loan>();
    }
    
    //Search functions
    static ResultSet searchBookbyName( String bookName)
    {   
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;    
        PreparedStatement pStmt = null;
        
        try
        {
            String s = "jdbc:sqlserver://localhost\\LMS:1433;databaseName=LMS";
            con=DriverManager.getConnection(s,"Asad","123");

            stmt = con.createStatement(); 
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
                
        try
        {
            pStmt = con.prepareStatement("select * from Books where name = ?");
//            pStmt.setString(1, title);
//            pStmt.setString(2, author);
//            pStmt.setString(3, subject);
            rs = pStmt.executeQuery();
        }
        catch(SQLException e)
        {
            
        }
        return rs;
            

//        for (int i = 0; i < Books.size(); i++)
//        {
//            if(Books.get(i).name == bookName)
//            {
//                System.out.println("Book found.");
//                //System.out.println("Book ID: " + Books.get(i).id);
//                System.out.println("ISBN: " + Books.get(i).isbn);
//                System.out.println("Name: " + Books.get(i).name);
//                System.out.println("Author: " + Books.get(i).author);
//                System.out.println("Subject: " + Books.get(i).subject);
//                System.out.println("Quantity left: " + Books.get(i).quantity);
//                System.out.println();
//            }
//        }
    }
    static ResultSet searchBookbyAuthor(String author)
    {          
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;    
        PreparedStatement pStmt = null;
        
        try
        {
            String s = "jdbc:sqlserver://localhost\\LMS:1433;databaseName=LMS";
            con=DriverManager.getConnection(s,"Asad","123");

            stmt = con.createStatement(); 
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
                
        try
        {
            pStmt = con.prepareStatement("select * from Books where author = ?");
//            pStmt.setString(1, title);
//            pStmt.setString(2, author);
//            pStmt.setString(3, subject);
            rs = pStmt.executeQuery();
        }
        catch(SQLException e)
        {
            
        }
        return rs;

//        for (int i = 0; i < Books.size(); i++)
//        {
//            if(Books.get(i).author == author)
//            {
//                 System.out.println("Book found.");
//                //System.out.println("Book ID: " + Books.get(i).id);
//                System.out.println("ISBN: " + Books.get(i).isbn);
//                System.out.println("Name: " + Books.get(i).name);
//                System.out.println("Author: " + Books.get(i).author);
//                System.out.println("Subject: " + Books.get(i).subject);
//                System.out.println("Quantity left: " + Books.get(i).quantity);
//                System.out.println();
//            }
//        }
    }
    static ResultSet searchBookbySubject( String subject)
    {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;    
        PreparedStatement pStmt = null;
        
        try
        {
            String s = "jdbc:sqlserver://localhost\\LMS:1433;databaseName=LMS";
            con=DriverManager.getConnection(s,"Asad","123");

            stmt = con.createStatement(); 
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
                
        try
        {
            pStmt = con.prepareStatement("select * from Books where subject = ?");
//            pStmt.setString(1, title);
//            pStmt.setString(2, author);
//            pStmt.setString(3, subject);
            rs = pStmt.executeQuery();
        }
        catch(SQLException e)
        {
            
        }
        return rs;
        
//        for (int i = 0; i < Books.size(); i++)
//        {
//            if(Books.get(i).subject == subject)
//            {
//                 System.out.println("Book found.");
//                //System.out.println("Book ID: " + Books.get(i).id);
//                System.out.println("ISBN: " + Books.get(i).isbn);
//                System.out.println("Name: " + Books.get(i).name);
//                System.out.println("Author: " + Books.get(i).author);
//                System.out.println("Subject: " + Books.get(i).subject);
//                System.out.println("Quantity left: " + Books.get(i).quantity);
//                System.out.println();
//            }
//        }
    }
    static ResultSet BorrowedBooks(String username)
    {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;    
        PreparedStatement pStmt = null;
        
        try
        {
            String s = "jdbc:sqlserver://localhost\\LMS:1433;databaseName=LMS";
            con=DriverManager.getConnection(s,"Asad","123");

            stmt = con.createStatement(); 
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
                
        try
        {
            pStmt = con.prepareStatement("select from Loans where  username = ?");
//            pStmt.setString(1, title);
//            pStmt.setString(2, author);
//            pStmt.setString(3, subject);
            rs = pStmt.executeQuery();
        }
        catch(SQLException e)
        {
            
        }
        return rs;
        
    }
    void DisplayScreen() throws SQLException
    {
        System.out.println("Press 1 for Searching Books.");        
        System.out.println("Press 4 for Viewing your Profile.");
        System.out.println("Press 5 for Viewing Borrowed Books.");
        System.out.println("Press 6 for taking a book on loan.");
        Scanner reader = new Scanner(System.in);
        int search = reader.nextInt();
        if(search == 1)
        {
            System.out.println("Press 1 for Searching Books by Name.");
            System.out.println("Press 2 for Searching Books by Author Name.");
            System.out.println("Press 3 for Searching Books by Subject.");
            search = reader.nextInt();

            if(search == 1)
            {
                System.out.print("Enter book name: ");
                String s=reader.nextLine();
                searchBookbyName(s);                
            }
            else if(search == 2)
            {
                System.out.print("Enter author name: ");
                String s=reader.nextLine();
                searchBookbyAuthor(s);
            }
            else if(search == 3)
            {
                System.out.print("Enter subject of the book: ");
                String s=reader.nextLine();
                searchBookbySubject(s);
            }
            else
            {
                //error message
            }

        }
        else if(search == 4)
        {
            System.out.println("Username: "+username);
            System.out.println("Name: "+name);
            System.out.println("Address: "+address);
            System.out.println("Phone: "+phone);            
        }
        else if(search == 5)
        {
            ResultSet rs=BorrowedBooks(username);
            while(rs.next())
            {
                System.out.println("Book Name:"+rs.getString(1));
                System.out.println("ISBN:"+rs.getString(3));                
            }
        }
        else if(search == 6)
        {
            //if(qty==0)
//                {
//                    System.out.println("Press 1 to put book on hold. Press any other key to exit");
//                    int loan=reader.nextInt();
//                    if(loan==1)
//                    {
//
//                    }
//                    else
//                    {
//
//                    }
//                }   
        }
        else
        {
            //error message
        }

    }
    //Place book on hold if not available
//    void putOnHold(Book obj)
//    {
//        if(obj.quantity == 0)
//        {
//            obj.putOnHold(username);
//        }
////        else
////        {
////            obj.quantity--;
////            Date issDate = new Date();
////            Date retDate = new Date(new Date().getTime() + 604800000);
////            Loan a = new Loan(obj.isbn, username, issDate, retDate);
////            loanedBooks.add(a);
////            obj.addLoan(a);
////        }
//    }
    
/*    void getInfo(ArrayList<Book> Books)
    {
        System.out.println("Username: " + username);
        System.out.println("Name: " + name);
        System.out.println("Phone No.: " + phone);
        System.out.println("Adress: " + address);
        System.out.println("Books loaned: ");
        for (int i = 0; i < loanedBooks.size(); i++)
        {
            System.out.println("ISBN: " + loanedBooks.get(i).isbn);
            for(int j = 0; j < Books.size(); j++)
            {
                if(loanedBooks.get(i).isbn == Books.get(j).isbn)
                {
                    System.out.println("Name: " + Books.get(j).name);
                    System.out.println("Author: " + Books.get(j).author);
                    System.out.println("Subject: " + Books.get(j).subject);
                    System.out.println();
                }
            }
        }
    }*/
}
