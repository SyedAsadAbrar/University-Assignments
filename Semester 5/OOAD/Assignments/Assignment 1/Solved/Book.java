/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lms_final;
import java.util.*;
import java.io.*;
import java.sql.*;

/**
 *
 * @author Asad
 */
public class Book {
    ArrayList<Integer> ids;         //since ISBN is given to the title of the book, not individual copies
    String isbn;
    String name;
    String author;
    String subject;
    ArrayList<Loan> loanedDetails;
    ArrayList<String> onHoldList;
    ArrayList<String> requestList;
    
    Book()
    {
        name = author = isbn = subject = null;
        ids  = null;
        loanedDetails = null;
        onHoldList = requestList = null;
    }
    
    Book(String isbn, String name, String author, String subject)
    {
        this.name = name;
        this.author = author;
        this.isbn = isbn;
        this.subject = subject;
        
        onHoldList = new ArrayList<>();
        requestList = new ArrayList<>();
        loanedDetails = new ArrayList<>();
        ids = new ArrayList<>();
        
        String temp;
        
        int id = 0;
        
        java.util.Date todaysDate = new java.util.Date();
        
        for(int i = Library.loans.size() - 1; i >= 0; i--)
        {
            if(Library.loans.get(i).onLoan && Library.loans.get(i).isbn.equals(isbn))
            {
                loanedDetails.add(Library.loans.get(i));
            }
        }
        
        try
        {   
            //populating ids arraylist
            Library.pStmt = Library.con.prepareStatement("select id from Copies where isbn = ?");
            Library.pStmt.setString(1, isbn);
            ResultSet rs = Library.pStmt.executeQuery();
            while(rs.next())
            {
                id = rs.getInt(1);
                ids.add(id);
            }
            
            //populating onHoldList arraylist
            Library.pStmt = Library.con.prepareStatement("select username from Requests where isbn = ? AND status = 'On Hold'");
            Library.pStmt.setString(1, isbn);
            rs = Library.pStmt.executeQuery();
            while(rs.next())
            {
                temp = rs.getString(1);
                onHoldList.add(temp);
            }
            
            //populating requestList arraylist
            Library.pStmt = Library.con.prepareStatement("select username from Requests where isbn = ? AND status = 'Requested'");
            Library.pStmt.setString(1, isbn);
            rs = Library.pStmt.executeQuery();
            while(rs.next())
            {
                temp = rs.getString(1);
                requestList.add(temp);
            }
        }
        catch (SQLException e) 
        {
            System.out.println(e);
        }
    }
    
    boolean isEqual(Book obj)
    {
        //Two books with different ids, but all the other attributes same are
        //two instances of the SAME book
        if (obj.isbn.equals(isbn))
        {
            if(obj.name.equals(name))
            {
                if(obj.author.equals(author))
                {
                    if(obj.subject.equals(subject))
                    {
                        return true;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }
    
    void addIDs(int num)
    {
        for (int i = 0; i < num; i++) {
            
            if(ids != null)
            {
                ids.add(ids.get(ids.size()-1) + 1);
            }
            else
            {
                ids.add(i+1);
            }
        }
    }
}
