/*
Syed Asad Abrar
16L-4292
Section A
l164292@lhr.nu.edu.pk

Muhammad Haroon
16L-4301
Section A
l164301@lhr.nu.edu.pk

This program finds all the palindromes from the given "words.txt" file and
prints them in a new file "results.txt" using multi-threading and hashmap. The 
number of "worker" threads is input by the user. The program also prints the number 
of palindromes found by each thread at the end of the "results.txt" file.
*/
/*
    Test Cases:
    {
        Input w: 1
        Output:
        Worker name: Thread 1 , palindrome_count: 299
        Total count for Palindromes = 299 

        Input w: 20
        Output:
        Worker name: Thread 1 , palindrome_count: 26
        Worker name: Thread 2 , palindrome_count: 31
        Worker name: Thread 3 , palindrome_count: 151
        Worker name: Thread 20 , palindrome_count: 0
        Worker name: Thread 19 , palindrome_count: 0
        Worker name: Thread 18 , palindrome_count: 0
        Worker name: Thread 17 , palindrome_count: 0
        Worker name: Thread 16 , palindrome_count: 0
        Worker name: Thread 15 , palindrome_count: 0
        Worker name: Thread 14 , palindrome_count: 0
        Worker name: Thread 13 , palindrome_count: 0
        Worker name: Thread 12 , palindrome_count: 0
        Worker name: Thread 11 , palindrome_count: 0
        Worker name: Thread 10 , palindrome_count: 0
        Worker name: Thread 4 , palindrome_count: 76
        Worker name: Thread 5 , palindrome_count: 15
        Worker name: Thread 9 , palindrome_count: 0
        Worker name: Thread 8 , palindrome_count: 0
        Worker name: Thread 6 , palindrome_count: 0
        Worker name: Thread 7 , palindrome_count: 0
        Total count for Palindromes = 299

    }
*/

package ap_assignment_2;
import java.util.*;
import java.io.*;


public class main {

    public static void main(String[] args) throws FileNotFoundException {
        //Hashmap as directed in the requirement
        HashMap<String, ArrayList<String>> mapBagOfTasks = new HashMap<>();
        
        //ArrayList to put in hashmap object
        ArrayList<String> arrBag = null;
        
        //variables used for inserting words in ArrayLists
        int wordLength;
        String word = new String();
        Scanner inFile;
        
        //number of worker threads, take input from user; scanner object to
        //take input
        int w = 0;
        Scanner input = new Scanner(System.in);
        boolean inputIsValidNumber = false;
        
        //variables used while giving keys as argument of PalindromeWorker constructor
        int startingIndex = 0;
        int endingIndex = 1;
        int noOfBags;
        ArrayList<Integer> keys = null;
        
        //variables to 
        boolean workStatus = false;
        int threadVar = 0;
        
        //Shared array to save output and finally output to file
        ArrayList<String> sharedArr = new ArrayList<>();
        
        //Array of Threads
        ArrayList<Thread> threads = new ArrayList<>();
        
        //Thread to work with
        Thread workerThread = null;
        
        //Thread to write on file
        Thread writerThread = new Thread(new PalindromeWriter(sharedArr));
        
        //Initial printed statement
        System.out.println("This program will find the number of palindromes"
                + "in the dictionary of words given in \"words.txt\".");
        
        //prompting user to input
        System.out.println("Please enter number of worker threads w.");
        
        //this check if a number is entered and it is a positive integer
        while(!inputIsValidNumber)
        {
            //checking if user entered a non-numeric literal, asking them to input again in such a case
            while(!input.hasNextInt())
            {
                System.out.println("Invalid input, please only input a number.");
                input.nextLine();
            }
            //assigning value to w if an integer is entered
            w = input.nextInt();
            
            //if a positive integer is not input
            if(w <= 0)
            {
                System.out.println("Invalid input, please only input a valid number.");
                input.nextLine();
            }
            //to break while loop
            else
            {
                inputIsValidNumber = true;
            }
        }
            
        //displaying the value input by user
        System.out.println("You entered " + w + " for value of worker threads w.\n");
        
        //File input and filenotfound exception handling
        try
        {
            inFile = new Scanner(new FileReader("words.txt"));
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File Doesn't Exist.");
            return;
        }
        
        //Mapping words to hashmap
        while(inFile.hasNext())
        {
            //Reading word from file
            word = inFile.next();
            wordLength = word.length();
            
            //If ArrayList of that specific word length doesn't exist, make one
            if(mapBagOfTasks.get(Integer.toString(wordLength)) == null)
            {
                arrBag = new ArrayList<String>();
                mapBagOfTasks.put(Integer.toString(wordLength), arrBag);
            }

            //Add word to its specific ArrayList
            mapBagOfTasks.get(Integer.toString(wordLength)).add(word);
        }
        
        //no of bags given to each thread, in case of a division which causes
        //a decimal, each thread will be given the type-casted value(to an int)
        //and the remaining would be given to the last thread.
        noOfBags = mapBagOfTasks.size()/w;
        
        //if input number of threads is greater than size of hashmap
        if(w > mapBagOfTasks.size())
        {
            noOfBags = 1;
        }
        
        //while work is being assigned, workStatus would be false
        while(!workStatus)
        {
            //startingIndex is the first key given to that specific thread,
            //the last key given to that specific thread is (endingIndex - 1)
            //all keys between these two figures are also given to that
            //specific thread
            startingIndex = endingIndex;
            
            //if endingIndex is less than input number of threads, increment
            //endingIndex of keys by the noOfBags
            if(endingIndex < w)
            {
                endingIndex = endingIndex + noOfBags;
            }
            //if it is equal to the input number of threads, all remaining bags
            //would be assigned to this thread
            else
            {
                endingIndex = mapBagOfTasks.size() + 1;
            }
            
            //making an arrayList of keys
            keys = new ArrayList<Integer>();
            
            //adding keys to the arrayList
            for(int i = startingIndex; i < endingIndex; i++)
            {
                keys.add(i);
            }
                
            //Adding a new worker thread to array of threads
            workerThread = new Thread(new PalindromeWorker(mapBagOfTasks, sharedArr, keys));
            threads.add(workerThread);
            
            //if the size of threads array becomes equal to input number of
            //threads, our work is complete
            if(threads.size() == w)
            {
                workStatus = true;
            }
        }
        
        //testing purposes
        //System.out.println("threads no: " + threads.size());
        
        //starting all worker threads
        for(int i = 0; i < threads.size(); i++)
        {
            threads.get(i).start();
        }
        
        //Starting writer thread
        writerThread.start();

//--------------------------------------------------------------------------------        
//    previously-written code which was edited after discussion with Sir Irfan
//--------------------------------------------------------------------------------
//        while(!workStatus)
//        {
//            if(w >= mapBagOfTasks.size())
//            {
//                while(threadVar < mapBagOfTasks.size())
//                {
//                    testThreads.get(threadVar++).start();
//                }
//            }
//            
//            else
//            {
//                while(threadVar < w)
//                {
//                    testThreads.get(threadVar++).start();
//                }
//                temp = threadVar + 1;
//                threadVar = 0;
//                while(temp < mapBagOfTasks.size())
//                {
//                    while(testThreads.get(threadVar).getState() != Thread.State.TERMINATED)
//                    {
//                        threadVar++;
//                        if(threadVar == w)
//                        {
//                            threadVar = 0;
//                        }
//                    }
//                    workerThread = new Thread(new PalindromeWorker(mapBagOfTasks, sharedArr, temp++));
//                    testThreads.set(threadVar, workerThread);
//                    testThreads.get(threadVar).start();
//                }
//            }
//            
//            
////            while(testThreads.get(0).getState() == Thread.State.RUNNABLE)
////            {
////                System.out.println(testThreads.get(0).getName() + " : " + testThreads.get(0).getState());
////                break;
////            }
////            System.out.println(testThreads.get(0).getState());
//            
//            workStatus = true;
//        }
    
        //while iteration variable is less than inputted number of threads
        while(threadVar < threads.size())
        {
            try
            {
                //Joining all threads
                threads.get(threadVar++).join();
            }
            catch(InterruptedException e)
            {

            }
        }

        PalindromeWriter.workDone = true;
        
        System.out.println("The palindromes have been found(if any), and have "
                + "been saved in \"results.txt\" file. Program is now exiting.");
    }
    
}
