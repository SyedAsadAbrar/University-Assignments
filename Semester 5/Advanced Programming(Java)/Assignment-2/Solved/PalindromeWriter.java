/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ap_assignment_2;
import java.util.*;
import java.io.*;
/**
 *
 * @author Asad
 */
public class PalindromeWriter implements Runnable{
    //member variables
    ArrayList<String> sharedArr;
    String infoWork = null;
    
    //to check if worker threads have done their work
    static boolean workDone = false;
    
    //parameterized constructor
    PalindromeWriter(ArrayList<String> sharedArr)
    {
        this.sharedArr = sharedArr;
        infoWork = new String();
    }
    
    @Override
    public void run()
    {
        //The word to savee to file
        String wordToPrint = null;
        
        try{
            FileWriter writer = new FileWriter("results.txt");
            
            //If writer thread dies before ALL worker threads, we would lose 
            //valuable information, so we run it in a while loop waiting
            //for all worker threads to die, meanwhile doing our work.
            while(!workDone)
            {
                synchronized(sharedArr)
                {
                    //if array is empty, there is no word to write
                    //to avoid null pointer exception, this if condition is used
                    if(!sharedArr.isEmpty())
                    {
                        //initially, iterator had been used to iterate over
                        //shared array but it caused concurrent modification
                        //exception so a simple get function to get the first
                        //index was used, a much simpler implementation
                        wordToPrint = sharedArr.get(0);
                        sharedArr.remove(0);
                        if(!wordToPrint.startsWith("Worker name: "))
                        {
                            //Each platform use different line seperators, "\n" didn't work for me so
                            //on googling, I learnt that Windows uses "/r/n" line separator, the line
                            //seperator for your specific platform can be obtained from the code written
                            //below which makes it platform-independent. Hence, this was used.
                            writer.write(wordToPrint + System.getProperty("line.separator"));
                        }
                        else
                        {
                            //store in infoWork String which stores the information
                            //of work done by each thread
                            infoWork += wordToPrint + System.getProperty("line.separator");
                        }
                    }
                }
            }
            
            //shared array may not be empty after the workDone variable
            //achieves a 'true' value, so this while loop prints all the words
            //still left in the array
            while(sharedArr.size() != 0)
            {
                wordToPrint = sharedArr.get(0);
                writer.write(wordToPrint + System.getProperty("line.separator"));
                sharedArr.remove(0);
            }
            
            //writing infoWork string and total count on file and closing stream
            writer.write(infoWork);
            writer.write("Total count for Palindromes = " + PalindromeWorker.tCount);
            writer.close();
        }
        catch(IOException e)
        {
            System.out.println("File couldn't be created.");
            return;
        }
    }
    
//--------------------------------    
//  previous implementation idea 
//-------------------------------- 
//    Iterator it = sharedArr.iterator();
//    String wordToPrint = null;
//    while(sharedArr.size() > 1 && !workDone)
//    {
//        try{
//            FileWriter writer = new FileWriter("C:\\Users\\Asad\\Desktop\\results.txt");
//            while(it.hasNext())
//            {
//                synchronized(sharedArr)
//                {
//                    wordToPrint = it.next().toString();
//                    if(!wordToPrint.startsWith("Worker name: "))
//                    {
//                        //Each platform use different line seperators, "\n" didn't work for me so
//                        //on googling, I learnt that Windows uses "/r/n" line separator, the line
//                        //seperator for your specific platform can be obtained from the code written
//                        //below which makes it platform-independent. Hence, this was used.
//                        writer.write(wordToPrint + System.getProperty("line.separator"));    
//                    }
//                    else
//                    {
//                        infoWork += wordToPrint + "\n";
//                    }
//                    it.remove();
//                }             
//            }
//            it = sharedArr.iterator();
//            while(sharedArr.size() != 0)
//            {
//                wordToPrint = it.next().toString();
//                writer.write(wordToPrint + System.getProperty("line.separator"));
//                it.remove();
//            }
//
//            //System.out.println("Total count for Palindromes = " + PalindromeWorker.tCount);
//            writer.write("Total count for Palindromes = " + PalindromeWorker.tCount);
//            writer.close();
//        }
//        catch(ConcurrentModificationException e)
//        {
//        }
//        catch(IOException e)
//        {
//        
//        }
//    }
//    while(threadVar < threads.size())
//    {
//        try
//        {
//            //Joining all threads
//            threads.get(threadVar++).join();
//        }
//        catch(InterruptedException e)
//        {
//        }
//    }
//    System.out.print(infoWork);
//    System.out.println("Total count for Palindromes = " + PalindromeWorker.tCount);
//    }
}
