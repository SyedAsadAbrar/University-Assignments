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
public class PalindromeWorker implements Runnable{
    //member variable
    int count;
    HashMap<String, ArrayList<String>> mapBagOfTasks;
    ArrayList<String> sharedArr;
    int key;
    String name;
    static int num = 1;
    static int tCount = 0;
    
    PalindromeWorker()
    {
        count = 0;
        name = "Thread " + num;
        num++;
    }
    
    PalindromeWorker(HashMap<String, ArrayList<String>> mapBagOfTasks, ArrayList<String> sharedArr, int key)
    {
        name = "Thread " + num;
        num++;
        this.mapBagOfTasks = mapBagOfTasks;
        this.sharedArr = sharedArr;
        this.key = key;
    }
    
    void changeKey(int key)
    {
        this.key = key;
    }
    
    @Override
    public void run()
    {
        Iterator i = mapBagOfTasks.entrySet().iterator();
        int iCount = 1;
        Map.Entry me;
        
        if(mapBagOfTasks.get(Integer.toString(key)) != null)
        {
            ListIterator<String> arrayListIterator = mapBagOfTasks.get(Integer.toString(key)).listIterator();
            StringBuffer toCompare = null;

            while(i.hasNext() && iCount <= key) {
                me = (Map.Entry)i.next();
                iCount++;
            }
            while(arrayListIterator.hasNext())
            {
                toCompare = new StringBuffer(arrayListIterator.next());
                toCompare.reverse();

                if(mapBagOfTasks.get(Integer.toString(key)).contains(toCompare.toString()))
                {
                    count++;
                    synchronized(sharedArr)
                    {
                        sharedArr.add((toCompare.reverse()).toString());
                        tCount++;
                    }
                }
                toCompare = null;
            }
        }
        
        synchronized(sharedArr)
        {
            sharedArr.add("Worker name: " + name + " , palindrome_count: " + count);
        }
            
        System.out.println("Worker name: " + name + " , palindrome_count: " + count);
        
        return;
    }
}
