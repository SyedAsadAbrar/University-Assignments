/*
Haider Nadeem Mirza
16L-4045
Section A
l164045@lhr.nu.edu.pk

Syed Asad Abrar
16L-4292
Section A
l164292@lhr.nu.edu.pk

This program finds all the sentences, or consecutive sequence of sentences, 
in the provided input string, where the length of the sentence(s) is greater or 
equal to the minimum and lesser or equal to the maximum given by the user. Given
text must end with a full stop.
*/
/*
    Test Cases:
        {Text: "Black is white. Day is night. Understanding is ignorance.
                Truth is fiction. Safety is danger." 
        
        Min:16 Max:17
        Output:         //multiple outputs
         Truth is fiction.
         Safety is danger.

        Min:17 Max:18
        Output:         //multiple outputs
         Truth is fiction.
         Safety is danger.

        Min:30 Max:37
        Output:
         Truth is fiction. Safety is danger.

        }
        {Text: "The grass was greener. The light was brighter. The taste 
                was sweeter. The endless river. Forever and ever." 

        Min:0 Max:60
        Output:
        The grass was greener.
        The grass was greener. The light was brighter.
         The light was brighter.
         The light was brighter. The taste was sweeter.
         The taste was sweeter.
         The taste was sweeter. The endless river.
         The taste was sweeter. The endless river. Forever and ever.
         The endless river.
         The endless river. Forever and ever.
         Forever and ever.

        Min:10 Max:14
        Output:
        

        Min:20 Max:30
        Output:
        The grass was greener.
         The light was brighter.
         The taste was sweeter.
        }
*/

package AP_Assignment_1;

import java.util.Scanner;

public class AP_Assignment_Q2 {

    
    public static void main(String[] args) {
    
        //Decleration of variables
        int Min = -1, Max = -1;
        boolean CorrectInput = false;
        int iterator = 1;
    
        System.out.println("This program finds sentences from the given text that are within the Minimum/Maximum range");
        
        Scanner input = new Scanner(System.in);
        String Text = "No Text Entered";
        
        //Checking if user has entered any command line arguments
        if (args.length > 2)
        {
            CorrectInput = true;
            Text = args[0];
            try{
                Min = Integer.parseInt(args[1]); //Converting Minimum to integer, checking if its a valid argument
                if (Min < 0 || Min > 1000)
                {
                    System.out.println("Min entered in command line is not valid");
                    CorrectInput = false;
                }
            }
            catch(Exception e)
            {
                System.out.println("Min entered in command line is not valid"); //Exception thrown if argument is invalid
                CorrectInput = false;
            }
            
            try{
                Max = Integer.parseInt(args[2]);
                if (Max > 1000 || Max < 0 || Max < Min) //Converting Minimum to integer, checking if its a valid argument
                {
                    System.out.println("Max entered in command line is not valid");
                    CorrectInput = false;
                }
            }
            catch(Exception e)
            {
                System.out.println("Max entered in command line is not valid");
                CorrectInput = false;   //Exception thrown if argument is invalid
            }
            
        }
        
        if (!CorrectInput)  //If no/invalid arguments entered, taking input from user
        {
            System.out.println("Assuming that the text entered is correct format (Text MUST end with full stop otherwise program will end)");
            Text = input.nextLine();
            
            System.out.println("Enter the Min: ");
            do  //This section keeps asking for a valid input until user enters a correct Maximum
            {
                try{
                    Min = input.nextInt();
                    if (Min < 0 || Min > 1000)
                    {
                        System.out.println("Please enter a valid integer");
                    }
                    else 
                    {
                        CorrectInput = true;
                    }
                    
                }
                catch(Exception e)
                {
                    System.out.println("Please enter a valid integer");
                    input.next();
                }
            }while(!CorrectInput);
            
            CorrectInput = false;
            
            System.out.println("Enter the Max: ");
            do  //This section keeps asking for a valid input until user enters a correct Maximum
            {
                try{
                    Max = input.nextInt();
                    if (Max > 1000 || Max < Min || Max < 0)
                    {
                        System.out.println("Please enter a valid integer");
                    }
                    else 
                    {
                        CorrectInput = true;
                    }
                }
                catch(Exception e)
                {
                    System.out.println("Please enter a valid integer");
                    input.next();
                }
            }while(!CorrectInput);
            
        }
            //Echoing input before computing output
            System.out.println("Your Minimum: " + Min);
            System.out.println("Your Maximum: " + Max);
            System.out.println("Your Text: " + Text);
            
            if (Text.length() < Min) //Incase the text to be searched is less than minimum entered
            {
                System.out.println("The sentences entered are smaller in length than the range given.");
            }
            else
            {
                if (Min != -1 && Max != -1 && Text.charAt(Text.length() - 1) == '.')    //To make sure all data entered is valid
                {
                    int size = 0, k = 0;
                    System.out.println("\nResults:");
                    
                    for (int i = 0; k < Text.length(); i++) //Loop that runs until all possibilities have been checked
                    {
                        size++; //size variable that checks length if a sentence falls in the Min/Max range
                        
                        if((i >= Text.length() && k != i) || size > Max)    //updates k, points it to begining of next sentence when i reaches end of text
                        {
                            {
                                while (Text.charAt(k) != '.' && Text.charAt(k) != '?' && Text.charAt(k) != '!' && k < Text.length())
                                {
                                    k++;
                                }
                                k++;
                                i = k;  //i iteration stars with next sentence
                                size = 0;   
                            }
                        }
                        
                        else if (Text.charAt(i) == '.' || Text.charAt(i) == '?' || Text.charAt(i) == '!') //when i lands on end of sentence, size is checked. if its between the range, the substring is outputed
                        {
                            if (size <= Max && size >= Min)
                            {
                                System.out.println("Result " + (iterator++) + ":");
                                System.out.println(Text.substring(k, i + 1) + "\n");   //substring outputed
                            }
                        }
                        
                    }
                    
                }
                else if (Text.charAt(Text.length() - 1) != '.') //Entered text is not accepted if fullstop not given at end, this is to make sure the entered text is valid
                {
                    System.out.println("Text does not end with fullstop");
                }
            }
            
            
            
    }
    
}
