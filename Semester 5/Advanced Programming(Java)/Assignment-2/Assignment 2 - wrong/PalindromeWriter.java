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
    ArrayList<String> sharedArr;
    
    PalindromeWriter(ArrayList<String> sharedArr)
    {
        this.sharedArr = sharedArr;
    }
    
    @Override
    public void run()
    {
        Iterator it = sharedArr.iterator();
        
        try{
            FileWriter writer = new FileWriter("C:\\Users\\Asad\\Desktop\\results.txt");
            while(it.hasNext())
            {
                //Each platform use different line seperators, "\n" didn't work for me so
                //on googling, I learnt that Windows uses "/r/n" line separator, the line
                //seperator for your specific platform can be obtained from the code written
                //below which makes it platform-independent. Hence, this was used.
                writer.write(it.next().toString() + System.getProperty("line.separator"));
            }
            System.out.println("Total count for Palindromes = " + PalindromeWorker.tCount);
            writer.write("Total count for Palindromes = " + PalindromeWorker.tCount);
            writer.close();
        }
        catch(IOException e)
        {
            
        }
    }
}
