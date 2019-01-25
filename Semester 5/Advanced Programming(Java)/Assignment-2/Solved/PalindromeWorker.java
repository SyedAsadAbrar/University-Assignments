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
    //member variables
    
    //no of palindromes found by this thread
    int count;
    
    //BagsOfTask, sharedArr to write palindromes, arrayList of keys and name
    //of thread varibles
    HashMap<String, ArrayList<String>> BagsOfTask;
    ArrayList<String> sharedArr;
    ArrayList<Integer> keys;
    String name;
    
    //used for naming purposes
    static int num = 1;
    
    //The total of the palindromes found by all threads
    static int tCount = 0;
    
    //Default constructor
    PalindromeWorker()
    {
        count = 0;
        name = "Thread " + num;
        num++;
    }
    
    //Paramterized constructor
    //Passing BagsOfTask, sharedArr to write result, and list of keys
    PalindromeWorker(HashMap<String, ArrayList<String>> BagsOfTask, ArrayList<String> sharedArr, ArrayList<Integer> keys)
    {
        name = "Thread " + num;
        num++;
        this.BagsOfTask = BagsOfTask;
        this.sharedArr = sharedArr;
        this.keys = keys;
    }

//--------------------------------    
//  previous implementation idea 
//-------------------------------- 
//    void changeKey(int key)
//    {
//        this.key = key;
//    }
    
    @Override
    public void run()
    {
        //if keys are empty, in case of number of input threads greater than
        //size of hashmap
        if(!keys.isEmpty())
        {
            int i;
            //while arrayList has keys
            while(!keys.isEmpty())
            {
                //get first key
                i = keys.get(0);

                //convert it to string, since hashmap has string as key type
                String keyString = Integer.toString(i);

                //while values mapped to key exists
                while(BagsOfTask.get(keyString) != null)
                {
                    //Iterator to iterate over ArrayList
                    ListIterator<String> arrayListIterator = BagsOfTask.get(keyString).listIterator();

                    //StringBuffer variable to compare each string
                    StringBuffer toCompare = null;

                    //while there is a word in the arrayList of i length words
                    while(arrayListIterator.hasNext())
                    {
                        //assigning value of arrayListIterator.next() to stringBuffer
                        //variable which can be reversed to check if the reverse
                        //i.e. palindrome exists in the same list
                        toCompare = new StringBuffer(arrayListIterator.next());
                        toCompare.reverse();

                        //checking if the arrayList has the reverse of the word
                        //referenced by arrayListIterator.next()
                        if(BagsOfTask.get(keyString).contains(toCompare.toString()))
                        {
                            //if yes, then increase count of palindromes
                            count++;

                            //and write to sharedArr, tCount is also increased
                            //inside synchronized to avoid concurrent modification
                            synchronized(sharedArr)
                            {
                                sharedArr.add((toCompare.reverse()).toString());
                                tCount++;
                            }
                        }

                        //to avoid any errors
                        toCompare = null;
                    }
                    //when all words of keyString key has been checked, they are 
                    //removed from Hashmap
                    BagsOfTask.remove(keyString);
                }

                //the first key is also removed each time.
                keys.remove(0);
            }

            //After the thread has done its work, the work done by thread is saved
            //to shared array
            synchronized(sharedArr)
            {
                sharedArr.add("Worker name: " + name + " , palindrome_count: " + count);
            }

            //used for testing
            //System.out.println("Worker name: " + name + " , palindrome_count: " + count);
        }
        else
        {
            synchronized(sharedArr)
            {
                sharedArr.add("Worker name: " + name + " , palindrome_count: " + count);
            }
        }
    }
}
