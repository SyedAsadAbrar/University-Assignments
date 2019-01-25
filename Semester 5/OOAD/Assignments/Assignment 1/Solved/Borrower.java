/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lms_final;
import java.util.*;
import java.io.*;
import java.sql.*;
import java.text.*;

/**
 *
 * @author Asad
 */
public class Borrower extends User{
    
    Borrower()
    {
        username = password = name = address = null;
        phone = 0;
        loanedBooks = null;
        finePaid = 0;
    }
    Borrower(String username, String password, String name, String address, long phone, int fine)
    {
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.finePaid = fine;
        
        loanedBooks = new ArrayList<>();
        
        for(int i = 0; i < Library.loans.size(); i++)
        {
            if(Library.loans.get(i).username.equals(this.username) && Library.loans.get(i).onLoan)
            {
                loanedBooks.add(Library.loans.get(i));
            }
        }
    }
    
    //Search functions

    ArrayList<Book> searchBookbyName(String bookName) 
    {   
        ArrayList<Book> searchResults = new ArrayList<>();
        for (int i = 0; i < Library.books.size(); i++)
        {
            if(Library.books.get(i).name.toLowerCase().contains(bookName.toLowerCase()))
            {
                searchResults.add(Library.books.get(i));
            }
        }
        //Book.filterSameBooks(searchResults);
        return searchResults;
    }

    ArrayList<Book> searchBookbyAuthor(String author)
    {   
        ArrayList<Book> searchResults = new ArrayList<>();
        for (int i = 0; i < Library.books.size(); i++)
        {
            if(Library.books.get(i).author.toLowerCase().contains(author.toLowerCase()))
            {
                searchResults.add(Library.books.get(i));
            }
        }
        //Book.filterSameBooks(searchResults);
        return searchResults;
    }

    ArrayList<Book> searchBookbySubject(String subject)
    {   
        ArrayList<Book> searchResults = new ArrayList<>();
        for (int i = 0; i < Library.books.size(); i++)
        {
            if(Library.books.get(i).subject.toLowerCase().contains(subject.toLowerCase()))
            {
                searchResults.add(Library.books.get(i));
            }
        }
        //Book.filterSameBooks(searchResults);
        return searchResults;
    }    
    
    boolean requestBook(Book obj)
    {
        //will request book if available subject to Clerk or Librarian's approval,
        //if not available will automatically be put on hold list
        
        Book toCompare = null;
        
        boolean result = false;
        
        for(int i = 0; i < Library.books.size(); i++)
        {
            if(Library.books.get(i).isEqual(obj))
            {
                toCompare = Library.books.get(i);
                
                //book isn't on loan rn
                if(toCompare.loanedDetails.isEmpty())
                {
                    toCompare.requestList.add(this.username);
                    result = true;
                }
                
                //book is on loan rn (either all copies, or some)
                else
                {
                    //not all copies are on loan
                    if(toCompare.loanedDetails.size() < toCompare.ids.size())
                    {
                        toCompare.requestList.add(this.username);
                        result = true;
                    }
                    //all copies are on loan
                    else
                    {
                        toCompare.onHoldList.add(this.username);
                        result = false;
                    }
                }
            }
        }
        return result;
    }
    //viewInfo would be implemented in GUI
}
