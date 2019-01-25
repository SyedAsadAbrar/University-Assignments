/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lms_final;
import java.util.*;
import java.io.*;

/**
 *
 * @author Asad
 */
public class Loan {
    int bookId;
    String isbn;
    String username;
    Date issD;
    Date retD;
    boolean onLoan;         //1 is for currently loaned out
    
    Loan()
    {
        bookId = 0;
        isbn = username = null;
        issD = retD = null;
        onLoan = false;
    }
    
    Loan(int bookId, String isbn, String username, Date issD, Date retD)
    {
        this.bookId = bookId;
        this.isbn = isbn;
        this.username = username;
        this.issD = issD;
        this.retD = retD;
    }
    
    void setStatus(boolean onLoan)
    {
        this.onLoan = onLoan;
    }
}
