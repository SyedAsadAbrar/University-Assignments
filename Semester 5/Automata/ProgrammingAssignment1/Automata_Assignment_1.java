/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automata_assignment_1;
import java.util.*;
import java.io.*;
/**
 *
 * @author Asad
 */
public class Automata_Assignment_1 {
    
    //helper functions
    //not used in program as logic was changed later
    
    /*static char retAlphabet(int index)
    {
        char error = 0;
        if(index == 0)
        {
            return 'a';
        }
        else if(index == 1)
        {
            return 'b';
        }
        else if(index == 2)
        {
            return 'c';
        }
        else if(index == 3)
        {
            return 'd';
        }
        else if(index == 4)
        {
            return 'e';
        }
        else if(index == 5)
        {
            return 'f';
        }
        else if(index == 6)
        {
            return 'g';
        }
        else if(index == 7)
        {
            return 'h';
        }
        else if(index == 8)
        {
            return 'i';
        }
        else if(index == 9)
        {
            return 'j';
        }
        else if(index == 10)
        {
            return 'k';
        }
        else if(index == 11)
        {
            return 'l';
        }
        else if(index == 12)
        {
            return 'm';
        }
        else if(index == 13)
        {
            return 'n';
        }
        else if(index == 14)
        {
            return 'o';
        }
        else if(index == 15)
        {
            return 'p';
        }
        else if(index == 16)
        {
            return 'q';
        }
        else if(index == 17)
        {
            return 'r';
        }
        else if(index == 18)
        {
            return 's';
        }
        else if(index == 19)
        {
            return 't';
        }
        else if(index == 20)
        {
            return 'u';
        }
        else if(index == 21)
        {
            return 'v';
        }
        else if(index == 22)
        {
            return 'w';
        }
        else if(index == 23)
        {
            return 'x';
        }
        else if(index == 24)
        {
            return 'y';
        }
        else if(index == 25)
        {
            return 'z';
        }
        else if(index == 26)
        {
            return '.';
        }
        else
        {
            return error;
        }
    }
    static int retIndex(char Alphabet)
    {
        int error = -1;
        if(Alphabet == 'a')
        {
            return 0;
        }
        switch(Alphabet)
        {
            case 'a':
                return 0;
            case 'b':
                return 1;
            
                
        }
    }*/
            
    public static void main(String[] args) {
        //Specification 1
        System.out.println("Syed Asad Abrar\nL16-4292\nThis program validates strings which are accepted by Language L and gives appropriate state while traversing each character to the end. At the end, it tells if the string was accepeted or rejected by the Language L.");
        
        //declaration of variables
        String inputString = null;
        String decision = null;
        boolean input = true;
        Scanner in = new Scanner(System.in);
        int iterator = 0;
        int state = 0;
        int alphabet = 0;
        
        //Error Handling
        //π is any character except lower-case letters, upper-case letters and fullstops, i.e. every letter not contained in the alphabet.
        
        // Alphabets    a     b     c     d     e     f     g     h     i     j     k     l     m     n     o     p     q     r     s     t     u     v     w     x     y     z     .     π                                                                                                                                                                                                            States
        int DFA[][] = {{1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  9  ,  1  ,  1  ,  1  ,  19 ,  19},  // q0
                       {1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  2  ,  19},  // q1
                       {19 ,  19 ,  3  ,  19 ,  4  ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  5  ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19},  // q2
                       {19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  6  ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19},  // q3
                       {19 ,  19 ,  19 ,  7  ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19},  // q4
                       {19 ,  19 ,  19 ,  7  ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  8  ,  19 ,  19 ,  19 ,  7  ,  19 ,  19 ,  19 ,  19 ,  19 ,  7  ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19},  // q5
                       {19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  8  ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19},  // q6
                       {19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  8  ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19},  // q7
                       {19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19},  // q8
                       {1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  10 ,  1  ,  1  ,  1  ,  2  ,  19},  // q9
                       {1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  11 ,  1  ,  1  ,  1  ,  2  ,  19},  // q10
                       {1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  12 ,  19},  // q11
                       {1  ,  1  ,  13 ,  1  ,  14 ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  15 ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  19 ,  19},  // q12
                       {1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  16 ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  2  ,  19},  // q13
                       {1  ,  1  ,  1  ,  17 ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  2  ,  19},  // q14
                       {1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  18 ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  2  ,  19},  // q15
                       {1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  18 ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  2  ,  19},  // q16
                       {1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  18 ,  1  ,  1  ,  1  ,  1  ,  1  ,  2  ,  19},  // q17
                       {1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  1  ,  2  ,  19},  // q18
                       {19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19 ,  19}   // q19
        }; 
        
        //So that program doesn't exit after a single string
        while(true)
        {
            //Specification 2
            //Initial prompt to user and input by user
            System.out.println("\nDo you want to input a string? Press y for yes, n for no.");
            decision = in.nextLine();
            while(input)  
            {
                if(decision.length() == 1)
                {
                    if(decision.equalsIgnoreCase("n"))
                    {
                        //exiting in case of no 
                        System.out.println("\nYou chose to not enter a string, program is exiting.");
                        return;
                    }
                    else if(decision.equalsIgnoreCase("y"))
                    {
                        //in case of yes, user is asked to input string
                        System.out.println("\nYou chose to enter a string, please enter string.");
                        inputString = in.nextLine();
                        input = false;
                    }
                    else
                    {
                        //Error handling
                        System.out.println("Invalid input, please enter either y for yes or n for no.");
                        decision = in.nextLine();
                    }
                }
                else
                {
                    //Error handling
                    System.out.println("Invalid input, please enter either y for yes or n for no.");
                    decision = in.nextLine();
                }
            }
            
            //Specification 3
            //Initial state
            System.out.println("\nStart State: Q" + state);

            //while string is processed entirely
            while(iterator != inputString.length())    
            {
                //Printing out current character
                System.out.println("\nCurrent Alphabet: " + inputString.charAt(iterator));

                //To get the relative index of the character inputted with reference to 'A' in case of upper-case
                //letter input and 'a' in case of lower-case letter input
                if(inputString.charAt(iterator) >= 'A' && inputString.charAt(iterator) <= 'Z')
                {
                    alphabet = inputString.charAt(iterator) - 'A';
                }
                else if(inputString.charAt(iterator) >= 'a' && inputString.charAt(iterator) <= 'z')
                {
                    alphabet = inputString.charAt(iterator) - 'a';
                }
                else if(inputString.charAt(iterator) == '.')
                {
                    alphabet = 26;
                }
                
                //error handling, for all other characters(which are not in language)
                else
                {
                    alphabet = 27;
                }
                
                //next state for the character currently being processed
                state = DFA[state][alphabet];

                //output for each state
                System.out.println("Current State: Q" + state);
                if(state == 19)
                {
                    System.out.println("DFA is in TRAP state.");
                }

                //to move onto next character
                iterator++;
            }
            
            //Specification 4
            if(state == 18 || state == 8)
            {
                System.out.println("\nThe string \"" + inputString + "\" is accepted.");
            }
            else
            {
                System.out.println("\nThe string \"" + inputString + "\" is rejected.");
            }
            
            //resetting variables for possible next string
            input = true;
            state = 0;
            iterator = 0;
        }
   }
    
}
