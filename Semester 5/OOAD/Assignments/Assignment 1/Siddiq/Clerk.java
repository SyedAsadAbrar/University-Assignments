/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooad_assignment_1;
import java.util.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static ooad_assignment_1.Borrower.BorrowedBooks;

/**
 *
 * @author Asad
 */
public class Clerk extends User{
    //constructors
    Clerk()
    {
        username = password = null;
        name = null;
        address = null;
        phone = 0;
    }
    Clerk(String username, String password, String name, String address, int phone)
    {
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }
    
    //Search functions
    static ResultSet SearchBook(String title, String author, String subject)
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
            pStmt = con.prepareStatement("select * from Books where name = ? OR author = ? OR subject = ?");
            pStmt.setString(1, title);
            pStmt.setString(2, author);
            pStmt.setString(3, subject);
            rs = pStmt.executeQuery();
        }
        catch(SQLException e)
        {
            
        }
        return rs;
    }
    
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
    
    boolean addBorrower(Borrower b)
    {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;    
        PreparedStatement pStmt = null;
        username=b.username;
        password=b.password;
        name=b.name;
        address=b.address;
        phone=b.phone;
        boolean status=false;
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
            pStmt = con.prepareStatement("insert into users values(?,?,?,?,?,'B'));
            rs = pStmt.executeQuery();
            status=true;
        }
        catch(SQLException e)
        {
            
        }
        return status;
    }
    
    boolean removeBorrower(ArrayList<User> users, Borrower borrower)
    {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;    
        PreparedStatement pStmt = null;
        boolean status=false;
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
            pStmt = con.prepareStatement("Delete from Users where username = ? and type='B');
            rs = pStmt.executeQuery();
            status=true;
        }
        catch(SQLException e)
        {
            
        }
        return status;
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
        System.out.println("Press 1 for Searching a book.");
        System.out.println("Press 4 for Viewing your Profile.");
        System.out.println("Press 5 for Viewing Borrowed Books.");
        System.out.println("Press 6 for taking a book on loan.");
        System.out.println("Press 7 for Searching a borrower.");
        System.out.println("Press 8 for seeing fine list.");
        System.out.println("Press 9 to Check-in/Check-out an item.");
        System.out.println("Press 0 for updating borrower info.");
        System.out.println("Press - for adding a new borrower.");
        Scanner reader = new Scanner(System.in);
        String search = reader.nextLine();
        if(search == "1")
        {
            System.out.println("Press 1 for Searching Books by Name.");
            System.out.println("Press 2 for Searching Books by Author Name.");
            System.out.println("Press 3 for Searching Books by Subject.");
            search = reader.nextLine();

            if(search == "1")
            {
                System.out.print("Enter book name: ");
                search=reader.nextLine();
                searchBookbyName(search);                
            }
            else if(search == "2")
            {
                System.out.print("Enter author name: ");
                search=reader.nextLine();
                searchBookbyAuthor(search);
            }
            else if(search == "3")
            {
                System.out.print("Enter subject of the book: ");
                search=reader.nextLine();
                searchBookbySubject(search);
            }
            else
            {
                //error message
            }
        }
        else if(search == "4")
        {
            System.out.println("Username: "+username);
            System.out.println("Name: "+name);
            System.out.println("Address: "+address);
            System.out.println("Phone: "+phone);            
        }
        else if(search == "5")
        {
            ResultSet rs=BorrowedBooks(username);
            while(rs.next())
            {
                System.out.println("Book Name:"+rs.getString(1));
                System.out.println("ISBN:"+rs.getString(3));                
            }

        }
        else if(search == "6")
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
        else if(search == "7")
        {

        }
        else if(search == "8")
        {

        }
        else if(search == "9")
        {

        }
        else if(search == "0")
        {

        }
        else if(search == "-")
        {

        }

        else
        {
            //error message
        }

    }
    //Place book on hold if not available
//    void putOnHold(Book obj, Borrower borrower)
//    {
//        if(obj.quantity == 0)
//        {
//            obj.putOnHold(borrower.username);
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
    
    //not sure about this
//    void getInfo(ArrayList<Book> Books, Borrower borrower)
//    {
//        System.out.println("Username: " + borrower.username);
//        System.out.println("Books loaned: ");
//        for (int i = 0; i < borrower.loanedBooks.size(); i++)
//        {
//            System.out.println("ISBN: " + borrower.loanedBooks.get(i).isbn);
//            for(int j = 0; j < Books.size(); j++)
//            {
//                if(borrower.loanedBooks.get(i).isbn == Books.get(j).isbn)
//                {
//                    System.out.println("Name: " + Books.get(j).name);
//                    System.out.println("Author: " + Books.get(j).author);
//                    System.out.println("Subject: " + Books.get(j).subject);
//                    System.out.println();
//                }
//            }
//        }
//    }
    
    //Borrower related tasks
    
    //update Borrower
    
    //Check-in, check-out
//    void checkIn(Book book, Borrower borrower)
//    {
//        if(book.quantity != 0)
//        {
//            book.quantity--;
//            Date issDate = new Date();
//            Date retDate = new Date(new Date().getTime() + 604800000);
//            Loan a = new Loan(book.isbn, borrower.username, issDate, retDate);
//            borrower.loanedBooks.add(a);
//            book.addLoan(a);
//        }
//        else
//        {
//            System.out.println("Book is not available.");
//        }
//    }
//    
//    void checkOut(Book book, Borrower borrower)
//    {
//        if(borrower.loanedBooks.contains(book))
//        {
//            book.quantity++;
//            borrower.loanedBooks.remove(book);
//        }
//        else
//        {
//            System.out.println("Borrower has not borrowed this book.");
//        }
//    }
}
