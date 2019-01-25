/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lms_final;
import java.io.*;
import java.util.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Asad
 */
public class Librarian extends User{
    
    Librarian()
    {
        username = password = name = address = null;
        phone = 0;
        loanedBooks = null;
        finePaid = 0;
    }
    
    Librarian(String username, String password, String name, String address, long phone, int fine)
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

    //check-out and check-in functions
    void checkOut(User user, Book book)
    {
        Loan temp;
        
        int index = 0;
        
        int bookId = 0;
        
        java.sql.Date issD = new java.sql.Date((new java.util.Date()).getTime());
        java.sql.Date retD = new java.sql.Date((new java.util.Date()).getTime() + 604800000);
        
        for(int i = 0; i < book.requestList.size(); i++)
        {
            if(book.requestList.get(i).equals(user.username))
            {
                book.requestList.remove(i);
                index = i;
                
                bookId = book.loanedDetails.size() + 1;
                
                temp = new Loan(bookId, book.isbn, user.username, issD, retD);
                temp.setStatus(true);
                user.loanedBooks.add(temp);
                book.loanedDetails.add(temp);
                Library.loans.add(temp);
                break;
            }
        }
        
        if(book.loanedDetails.size() == book.ids.size())
        {
            while(book.requestList.size() > 0)
            {
                book.onHoldList.add(0, book.requestList.get(0));
                book.requestList.remove(0);
            }
        }
    }
    
    void checkIn(Loan loanObj)
    {
        Book book = null;
        User user = null;
        
        java.sql.Date todaysDate = new java.sql.Date((new java.util.Date()).getTime());
        
        java.util.Date returnDate = loanObj.retD;
        
        String username;
        
        for(int i = 0; i < Library.books.size(); i++)
        {
            if(loanObj.isbn.equals(Library.books.get(i).isbn))
            {
                book = Library.books.get(i);
                break;
            }
        }
        
        for(int i = 0; i < Library.users.size(); i++)
        {
            if(loanObj.username.equals(Library.users.get(i).username))
            {
                user = Library.users.get(i);
                break;
            }
        }
        
        //can't return book before return date specified on loan
        if(todaysDate.getTime() > returnDate.getTime() + 86399000)
        {
            user.finePaid =- 1000;
        }
        
        for(int i = 0; i < book.loanedDetails.size(); i++)
        {
            if(book.loanedDetails.get(i).username.equals(loanObj.username))
            {
                if(book.loanedDetails.get(i).bookId == loanObj.bookId)
                {
                    if(book.loanedDetails.get(i).issD.equals(loanObj.issD))
                    {
                        if(book.loanedDetails.get(i).retD.equals(loanObj.retD))
                        {
                            loanObj = book.loanedDetails.get(i);
                            loanObj.setStatus(false);
                        }
                    }
                }
            }
        }
        
        book.loanedDetails.remove(loanObj);
        user.loanedBooks.remove(loanObj);
        
        while(book.onHoldList.size() != 0)
        {
            username = book.onHoldList.get(0);
            book.onHoldList.remove(0);
            book.requestList.add(username);
        }
    }
    
    boolean renew(Loan loanObj)
    {
        boolean result = false;
        
        Book book = null;
        
        java.sql.Date issD = new java.sql.Date((new java.util.Date()).getTime());
        java.sql.Date retD = new java.sql.Date((new java.util.Date()).getTime() + 604800000);
                
        for(int i = 0; i < Library.books.size(); i++)
        {
            if(loanObj.isbn.equals(Library.books.get(i).isbn))
            {
                book = Library.books.get(i);
                break;
            }
        }
        
        if(!book.onHoldList.isEmpty())
        {
            result = false;
        }
        else
        {
            loanObj.issD = issD;
            loanObj.retD = retD;
            result = true;
        }
        
        return result;
    }
    
    void recordFine(int amount, User user)
    {
        user.finePaid -= amount;
    }
    
    void addBorrower(Borrower user)
    {
        Library.users.add(user);
    }
    
    //UPDATE BORROWER INFO
    
    void addBook(Book book)
    {
        Library.books.add(book);
    }
    
    boolean removeBook(Book book)
    {
        boolean result = false;
        
        if(!book.loanedDetails.isEmpty())
        {
            return result;
        }
        else
        {
            Library.books.remove(book);
            result = true;
        }
        return result;
    }
    
    //CHANGE INFO OF BOOK
}
