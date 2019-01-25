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
public class main {

    public static void main(String[] args) throws FileNotFoundException {
        HashMap<String, ArrayList<String>> mapBagOfTasks = new HashMap<>();
        
        ArrayList<String> arrBag = null;
        
        int wordLength;
        String word = new String();
        Scanner inFile;
        
        //number of worker threads, take input from user
        int w = 1;
        boolean workStatus = false;
        int threadVar = 0;
        int temp = threadVar;
        
        ArrayList<String> sharedArr = new ArrayList<String>();
        
        ArrayList<Thread> testThreads = new ArrayList<Thread>();
        
        Thread eeeoooeeee = null;
        
        for(int i=0;i<w;i++)
        {
            eeeoooeeee = new Thread(new PalindromeWorker(mapBagOfTasks, sharedArr, i + 1));
            testThreads.add(eeeoooeeee);
        }
        
        
        //File input and filenotfound exception handling
        try
        {
            inFile = new Scanner(new FileReader("C:\\Users\\Asad\\Desktop\\words.txt"));
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File Doesn't Exist.");
            return;
        }
        
        while(inFile.hasNext())
        {
            word = inFile.next();
            wordLength = word.length();
            
            if(mapBagOfTasks.get(Integer.toString(wordLength)) == null)
            {
                arrBag = new ArrayList<String>();
                mapBagOfTasks.put(Integer.toString(wordLength), arrBag);
            }

            mapBagOfTasks.get(Integer.toString(wordLength)).add(word);
        }
        
        while(!workStatus)
        {
            if(w >= mapBagOfTasks.size())
            {
                while(threadVar < mapBagOfTasks.size())
                {
                    testThreads.get(threadVar++).start();
                }
            }
            
            else
            {
                while(threadVar < w)
                {
                    testThreads.get(threadVar++).start();
                }
                temp = threadVar + 1;
                threadVar = 0;
                while(temp < mapBagOfTasks.size())
                {
                    while(testThreads.get(threadVar).getState() != Thread.State.TERMINATED)
                    {
                        threadVar++;
                        if(threadVar == w)
                        {
                            threadVar = 0;
                        }
                    }
                    eeeoooeeee = new Thread(new PalindromeWorker(mapBagOfTasks, sharedArr, temp++));
                    testThreads.set(threadVar, eeeoooeeee);
                    testThreads.get(threadVar).start();
                }
            }
            
            
//            while(testThreads.get(0).getState() == Thread.State.RUNNABLE)
//            {
//                System.out.println(testThreads.get(0).getName() + " : " + testThreads.get(0).getState());
//                break;
//            }
//            System.out.println(testThreads.get(0).getState());
            
            workStatus = true;
        }
        
        threadVar = 0;
        
        while(threadVar < w)
        {
            try
            {
                testThreads.get(threadVar++).join();
            }
            catch(InterruptedException e)
            {
                
            }
        }
        
        eeeoooeeee = new Thread(new PalindromeWriter(sharedArr));
        eeeoooeeee.start();
        
//        for (int i = 0; i < mapBagOfTasks.get(1).size(); i++) {
//            System.out.println(mapBagOfTasks.get(1).get(i));
//        }
    }
    
}
