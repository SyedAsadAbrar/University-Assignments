/*
    Syed Asad Abrar
    L164292@lhr.nu.edu.pk
    Advanced Programming Section A
    
    This program is for the Q2 of Homework 1, to demonstrate use of
    StringBuffer class.
*/

package homework1_2;
import java.io.*;
import java.util.*;

public class Homework1_2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //variables declared as instructed in Step 1
        String longString, shortString;
        Scanner input = new Scanner(System.in);
        StringBuffer longSBuf;
        int index;
        
        //Initial statement
        System.out.println("This program demonstrates the use of the StringBuffer class.");
        
        //Step 1
        //Taking input for longer string and displaying it
        System.out.println("\nPlease input longer string.");
        longString = input.nextLine();                                                                      //test input: Hello Friends! Testing123..
        System.out.println("\nYou inputted the following string: \"" + longString + "\"");                  //test output: Hello Friends! Testing123..
        
        //Taking input for shorter string and displaying it
        System.out.println("\nPlease input shorter string that is contained inside the longer string.");
        shortString = input.nextLine();                                                                     //test input: Friends
        System.out.println("\nYou inputted the following string: \"" + shortString + "\"");                 //test output: Friends
        
        //Step 2
        //Creating a string buffer object from longString string
        longSBuf = new StringBuffer(longString);
        
        //Step 3
        //longSBuf = new StringBuffer();
        //longSBuf.append(longString);
        
        //Step 4
        //Finding shorter string within longer string string buffer object
        index = longSBuf.indexOf(shortString,0);
        
        //Step 5
        //Results of deleting shorter string within longer string string buffer object
        System.out.println("\nThe result of deleting the shorter string from the string buffer object is:\n" + longSBuf.delete(index, index + shortString.length()));       //test output: Hello ! Testing123..
        
        //Step 6
        //Result of inserting CS433 in longer string string buffer object
        System.out.println("\nThe result of inserting \"CS433\" into the position of the shorter string in string buffer object is:\n" + longSBuf.insert(index, "CS433"));  //test output: Hello CS433! Testing123..
        
        //Step 7
        //Removing the last word
        System.out.println("\nThe result of deleting the last word from the string buffer object is:\n" + longSBuf.delete(longSBuf.lastIndexOf(" ") + 1, longSBuf.length()));   //test output: Hello CS433! 
        
        //Step 8
        //Apending rocks at the end of the string buffer
        System.out.println("\nThe result of appending \"rocks\" to the string buffer object is:\n" + longSBuf.append("rocks"));     //test output: Hello CS433! rocks
        
        //Step 9
        //Deleting character at the half of the total length of the string buffer object
        System.out.println("\nThe result of deleting the character at position n/2(where n is the length of the StringBuffer) of the string buffer object is:\n" + longSBuf.deleteCharAt(longSBuf.length()/2));     //test output: Hello CS43! rocks
        
        //Step 10
        //Reversing the string buffer contentss
        System.out.println("\nThe result of reversing the string buffer object is:\n" + longSBuf.reverse());        //test output: skcor !34SC olleH
    }
    
}
