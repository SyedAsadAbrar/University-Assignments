/*
    Syed Asad Abrar
    L164292@lhr.nu.edu.pk
    Advanced Programming Section A
    
    This program is for the Q1 of Homework 1, to demonstrate use of
    String and StringBuffer classes.
*/

package homework;
import java.io.*;
import java.util.*;


public class Homework_1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {                                                //Test input: "asad abrar"
        
        /*
            If the arguments are not 2, the program should return. If they are 2,
            then the program should proceed.
        */
        if(args.length!=2)
        {
           return;
        }
        
        if(args.length==2)
        {
            // Initial print statement and printing the arguments entered
            System.out.println("This program accepts string arguments and manipulates them.\n");
            System.out.println("The arguments entered are:");
            for (int i = 0; i < 2; i++) {
                System.out.println("Argument " + (i+1) + ": " + args[i]);
            }
            
            //Declaring two String objects, s1 and s2
            String s1 = args[0];
            String s2 = args[1];
            
            //Experimenting as instructed in question
            System.out.println("\nThe length of string s1 is " + s1.length());          //Test output: 4
            for (int i = 0; i < s1.length(); i++) {
                System.out.println("The character at " + i + "th index of s1 is " + s1.charAt(i));
            }
            
            //checking s1 and s2 for equality
            System.out.println("\nThe answer of s1.equals(s2) is: " + s1.equals(s2));                                           //test output: false
            System.out.println("The result of s1.equalsIgnoreCase(s2) is: " + s1.equalsIgnoreCase(s2));                         //test output: false
            System.out.println("The result of s1.compareTo(s2) is: " + s1.compareTo(s2));                                       //test output: 17
            
            //variables and scanner object to take input from user
            int toffset, ooffset, len;
            Scanner obj = new Scanner(System.in);
            
            //prompting user to input
            System.out.println("\nPlease enter value of offset of s1 to begin comparing with s2.");
            
            //checking if user entered a non-numeric literal, asking them to input again in such a case
            while(!obj.hasNextInt())
            {
                System.out.println("Invalid input, please only input a valid number.");
                obj.nextLine();
            }
       
            //giving the value inputted to the variable and displaying it
            toffset=obj.nextInt();                                                                                              //test input: 0
            System.out.println("You entered " + toffset + " for value of offset of s1 to begin comparing with s2.\n");
            
            //prompting user to input
            System.out.println("Please enter value of offset of s2 to begin comparing with s1.");
            
            //In order to move to the next line, otherwise it would fail the while loop twice upon inputting a non-numeric literal
            obj.nextLine();
            
            //checking if user entered a non-numeric literal, asking them to input again in such a case
            while(!obj.hasNextInt())
            {
                System.out.println("Invalid input, please only input a valid number.");
                obj.nextLine();
            }
            
            //giving the value inputted to the variable and displaying it    
            ooffset = obj.nextInt();                                                                                             //test input: 0
            System.out.println("You entered " + ooffset + " for value of offset of s2 to begin comparing with s1.\n");
            
            //prompting user to input
            System.out.println("Please enter value of length for comparison.");
            
            //In order to move to the next line, otherwise it would fail the while loop twice upon inputting a non-numeric literal
            obj.nextLine();
            
            //checking if user entered a non-numeric literal, asking them to input again in such a case
            while(!obj.hasNextInt())
            {
                System.out.println("Invalid input, please only input a valid number.");
                obj.nextLine();
            }
            
            //giving the value inputted to the variable and displaying it  
            len = obj.nextInt();                                                                                                 //test input: 3
            System.out.println("You entered " + len + " for value of length for comparison.\n");
            
            //Experimenting with user inputted values of arguments
            System.out.println("The result of s1.regionMatches(int toffset, s2, int offset, int len) with above inputted arguments is: " + s1.regionMatches(toffset, s2, ooffset ,len));        //test output: false
            
            //to take input from user for next function
            boolean ignoreCase;
            
            //prompting user to input
            System.out.println("\nDo you want to consider case in next comparison? Reply with true for no, false for yes.");
            
            //In order to move to the next line, otherwise it would fail the while loop twice upon inputting a non-numeric literal
            obj.nextLine();
            
            //checking if user entered a non-boolean literal, asking them to input again in such a case
            while(!obj.hasNextBoolean())
            {
                System.out.println("Invalid input, please only input a boolean value.");
                obj.nextLine();
            }
            
            //giving the value inputted to the variable and displaying it
            ignoreCase=obj.nextBoolean();                                                           //test input: true
            System.out.print("You chose to ");
            if(ignoreCase)
            {
                System.out.println("ignore case while comparison.");
            }
            else
            {
                System.out.println("consider case while comparison.");
            }
            
            //Experimenting with user inputted values of arguments
            System.out.println("\nThe result of s1.regionMatches(boolean ignoreCase, int toffset, s2, int offset, int len) with above inputted arguments is: " + s1.regionMatches(ignoreCase, toffset, s2, ooffset , len));             //test output: false
            
            //Finding out index of a random character c and index i
            //to take input from user for next function
            char c;
            int i;
            
            //prompting user to input
            System.out.println("\nInput character you want to find index of in s1.");
            
            //In case of character, this function would choose the first character so it works for all int, char, string and boolean values and would give no error, hence there is no need to handle it.
            c=obj.next().charAt(0);                                                                 //test input: s
            
            //displaying the value
            System.out.println("You entered \"" + c + "\".");
            
            //prompting user to input index to start search
            System.out.println("\nPlease enter the index from where you want to start search.");
            
            //In order to move to the next line, otherwise it would fail the while loop twice upon inputting a non-numeric literal
            obj.nextLine();
            
            //checking if user entered a non-numeric literal
            while(!obj.hasNextInt())
            {
                System.out.println("Invalid input, please only input a valid number.");
                obj.nextLine();
            }
            
            //giving the value inputted to the variable and displaying it
            i=obj.nextInt();                                                                        //test input: 0
            System.out.println("You input " + i + " as the index from where you want to start searching.");
            
            //Experimenting with user inputted values of arguments
            System.out.println("\nThe result of s1.indexOf(c,i) for the inputted values of c and i is: " + s1.indexOf(c,i));            //test output: 1
            
            //concat, replace, uppercase and lowercase functions
            System.out.println("\nThe result of s1.concat(s2) is : " + s1.concat(s2));                                                  //test output: asadabrar
            
            //declaring variables to be used as arguments for replace function
            char c1, c2;
            System.out.println("\nPlease enter a character found in s1, which you want to replace.");
            
            //giving the value inputted to the variable and displaying it
            c1=obj.next().charAt(0);                                                                                                    //test input: a 
            System.out.println("You entered \"" + c1 + "\" as the character you want to replace.");
            
            //prompting user to input
            System.out.println("\nPlease enter a character with which you want to replace c1.");
            
            //giving the value inputted to the variable and displaying it
            c2=obj.next().charAt(0);                                                                                                    //test input: p
            System.out.println("You entered \"" + c2 + "\" as the character with which you want c1 to be replaced in s1.");
            
            //Experimenting with user inputted values of arguments
            System.out.println("\nThe result of s1.replace(c1,c2) with the above inputted arguments is: " + s1.replace(c1,c2));         //test output: pspd
            
            //The document mentions to use s1.upperCase(), upon study of the documentation of string class, there was no such function. The closest one was s1.toUpperCase() which was used instead. Similarly, s1.toLowerCase() was used.
            System.out.println("\nThe result of s1.toUpperCase() is: " + s1.toUpperCase());                                             //test output: ASAD
            
            System.out.println("\nThe result of s1.toLowerCase() is: " + s1.toLowerCase());                                             //test output: asad
            
            //creating two stringBuffer objects sbuf1 and sbuf2 and giving them values of first and second argument respectively
            StringBuffer sbuf1 = new StringBuffer(args[0]);
            StringBuffer sbuf2 = new StringBuffer(args[1]);
            
            //new scanner object to take input
            obj = new Scanner(System.in);
            
            //Initial statement to tell user about program
            System.out.println("\nThis program manipulates StringBuffer arguments.\n");
            
            //Experimenting and printing results
            System.out.println("The result of sbuf1.length() is: " + sbuf1.length());                                                   //test output: 4
            
            //variables for sbuf.delete function
            int start, end;
            
            //prompting user to input
            System.out.println("\nPlease enter the index from which to start deleting.");
            
            //checking if user entered a non-numeric literal
            while(!obj.hasNextInt())
            {
                System.out.println("Invalid input, please only input a valid number.");
                obj.nextLine();
            }
            
            //giving the value inputted to the variable and displaying it
            start=obj.nextInt();                                                                                                        //test input: 3
            System.out.println("You entered " + start + " index from which to start deleting.");
            
            //prompting user to input
            System.out.println("\nPlease enter the index till which you want to delete.");
            
            //In order to move to the next line, otherwise it would fail the while loop twice upon inputting a non-numeric literal
            obj.nextLine();
            
            //checking if user entered a non-numeric literal
            while(!obj.hasNextInt())
            {
                System.out.println("Invalid input, please only input a valid number.");
                obj.nextLine();
            }
            
            //giving the value inputted to the variable and displaying it
            end=obj.nextInt();                                                                                                          //test input: 3
            System.out.println("You entered " + end + " index till which you want to delete.");
            
            //Experimenting with user inputted values of arguments
            System.out.println("\nThe result of sbuf1.delete(int start, int end) with the above inputted arguments is: " + sbuf1.delete(start,end));            //test output: asad
            
            //variable for sbuf.deleteCharAt function
            int index;
            
            //prompting user to input
            System.out.println("\nPlease input the index from which you want to delete the character in sbuf1.");
            
            //In order to move to the next line, otherwise it would fail the while loop twice upon inputting a non-numeric literal
            obj.nextLine();
            
            //checking if user entered a non-numeric literal
            while(!obj.hasNextInt())
            {
                System.out.println("Invalid input, please only input a valid number.");
                obj.nextLine();
            }
            
            //giving the value inputted to the variable and displaying it
            index=obj.nextInt();                                                                                                        //test input: 2
            
            //error handling
            while(index >= sbuf1.length() || index < 0)
            {
                System.out.println("Please enter a valid index.");
                index=obj.nextInt();   
            }
            
            System.out.println("You entered " + index + " index from which you want to delete the character in sbuf1.");
            
            //Experimenting with user inputted values of arguments
            System.out.println("\nThe result of sbuf1.deleteCharAt(int index) for above inputted argument is: " + sbuf1.deleteCharAt(index));       //test output: asd
            System.out.println("\nThe result of sbuf1.reverse() is: " + sbuf1.reverse());                                                           //test output: dsa
            
            //variable for sbuf.replace function
            String substring;
            
            //prompting user to input
            System.out.println("\nPlease input substring with which you want to replace a character sequence in sbuf1.");
            
            //In order to move to the next line, otherwise it would fail the while loop twice upon inputting a non-numeric literal
            obj.nextLine();
            
           //inputting string and displaying it
            substring=obj.nextLine();                                                                                                               //test input: as
            System.out.println("You entered \"" + substring + "\" as the substring.");
            
            //prompting user to input
            System.out.println("\nPlease input starting index from which to start replacing in sbuf1.");
            
            //checking if user entered a non-numeric literal
            while(!obj.hasNextInt())
            {
                System.out.println("Invalid input, please only input a valid number.");
                obj.nextLine();
            }
            
            //giving the value inputted to the variable and displaying it
            start=obj.nextInt();                                                                                                                    //test input: 0
            System.out.println("You entered " + start + " as the starting index from which to start replacing in sbuf1.");
            
            //prompting user to input
            System.out.println("\nPlease input ending index from which to start replacing in sbuf1.");
            
            //In order to move to the next line, otherwise it would fail the while loop twice upon inputting a non-numeric literal
            obj.nextLine();
            
            //checking if user entered a non-numeric literal
            while(!obj.hasNextInt())
            {
                System.out.println("Invalid input, please only input a valid number.");
                obj.nextLine();
            }
            
            //giving the value inputted to the variable and displaying it
            end=obj.nextInt();                                                                                                                    //test input: 3
            System.out.println("You entered " + end + " as the ending index from which to start replacing in sbuf1.");
            
            //Experimenting and displaying results
            System.out.println("\nThe result of sbuf1.replace(int start, int end, String str) with above inputted arguments is: " + sbuf1.replace(start, end, substring));     //test output: as       
            
            //prompting user to input
            System.out.println("Please input integer to call sbuf1.append() in a chain of method calls.");
            
            //variable for sbuf1.append function
            int integer;
            
            //In order to move to the next line, otherwise it would fail the while loop twice upon inputting a non-numeric literal
            obj.nextLine();
            
            //checking if user entered a non-numeric literal
            while(!obj.hasNextInt())
            {
                System.out.println("Invalid input, please only input a valid number.");
                obj.nextLine();
            }
           
            //giving the value inputted to the variable and displaying it
            integer=obj.nextInt();                                                                                                              //test input: 12
            System.out.println("You entered " + integer + " as the integer.");
            
            //Experimenting
            System.out.println("\nThe result of sbuf1.append().append() with inputted integer and sbuf2 as arguments is: " + sbuf1.append(integer).append(sbuf2));      //test output: as12abrar
        
            //variable for MyClass type for sbuf1.append function
            MyClass myclassobject = new MyClass();
            
            //Displaying results
            System.out.println("\nThe result of sbuf1.append() with an object of MyClass: " + sbuf1.append(myclassobject.toString()));                     //test output: as12abrarThis is my object.
            
            System.out.println("\nThe result of sbuf1.insert().insert() with previously inputted integer(for append chaining) and sbuf2 as arguments at index 0 is: " + sbuf1.insert(0,integer).insert(0,sbuf2));           //test output: abrar12as12abrarThis is my object.
        }
    }
       
}

//MyClass class made to invoke sbuf1.append with a MyClass class object
class MyClass{
    public String toString(){
        String str = "This is my object.";
        return str;
    }
}
