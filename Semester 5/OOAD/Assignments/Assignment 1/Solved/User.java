/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lms_final;
import java.io.*;
import java.util.*;

/**
 *
 * @author Asad
 */
public abstract class User {
    protected String username;
    protected String password;
    protected String name;
    protected String address;
    protected long phone;
    
    protected int finePaid;
    
    //record of bookss currently loaned by User
    ArrayList<Loan> loanedBooks;
}
