/*
    Syed Asad Abrar
    L164292@lhr.nu.edu.pk
    Advanced Programming Section A
    
    This program is for the Q1 of Homework 1, to demonstrate use of
    String and StringBuffer classes.
*/

package homework1_3;
import java.util.Random; 
/**
 *
 * @author Asad
 */
public class Homework1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Since the program requires no input, test inputs and outputs cannot be given.
        
        System.out.println("This program demonstrates the use of custom classes.");
        
        //declaring an array of Students
        Student[] std = new Student[25];
        
        //variable for random integer
        int random;
        
        //creating an object, setting marks and invoking toString function
        for (int i = 0; i < 25; i++) {
            std[i] = new Student();
            
            //Math.random() returns a psuedorandom double value that is greater than or equal to 0.0 and less than 1.0. Multiplying this double with (max - min) returns a double value greater or equal to 0 and less than max. Now, if we want it to be greater than min, we add min to this value but it still doesn't include max value. For that, we add 1 to the range. That is why this process is done as below. 
            random = (int)(Math.random() * ((100-50) + 1)) + 50;

            //invoking the setter for midterm 1 with a random integer
            std[i].SetMid1(random);
            
            //to give a new random value
            random = (int)(Math.random() * ((100-50) + 1)) + 50;
            
            //invoking the setter for midterm 1 with a random integer
            std[i].SetMid2(random);
            
            //to give a new random value
            random = (int)(Math.random() * ((100-50) + 1)) + 50;
            
            //invoking the setter for midterm 1 with a random integer
            std[i].SetFinal(random);
            
            //invoking the toString method
            System.out.println(std[i].toString());
        }
    }
    
}

class Student{
    //declaration of class with variables as specified
    String Name;
    int id, midterm1, midterm2, finalMarks;         //final is a reserved keyword
    static int nextId = 1;
        
    Student()
    {
        //The id value gets incremented by 1 for every student
        id = nextId++;
        Name = "Student" + String.valueOf(id);
        midterm1 = midterm2 = finalMarks = 0;
    }
    
    Student(String Name)
    {
        //Parameterized constructor where name is given
        //The id value gets incremented by 1 for every student
        id = nextId++;
        this.Name = Name;
        midterm1 = midterm2 = finalMarks = 0;
    }
    
    Student(String Name, int midterm1, int midterm2, int finalMarks)
    {
        //Parameterized constructor where name and all marks are given
        //The id value gets incremented by 1 for every student
        id = nextId++;
        this.Name = Name;
        this.midterm1 = midterm1;
        this.midterm2 = midterm2;
        this.finalMarks = finalMarks;
    }
    
    String calculateGrade()
    {
        //function to calculate grade
        //variables as needed are declared
        String grade;
        
        //The formula used is as specified, numbers are in double form to avoid getting typecasting into integers while multiplying
        double overallScore = ((30.00/100.00)*midterm1+(30.00/100.00)*midterm2+(40.00/100.00)*finalMarks)/100.00*4.00;
        
        //The grading system of FAST on its website as specified here: http://www.nu.edu.pk/Student/Grading
        if(overallScore == 4.00)
        {
            grade = "A";
        }
        else if(overallScore >= 3.67 && overallScore < 4.00)
        {
            grade = "A-";
        }
        else if(overallScore >= 3.33 && overallScore < 3.67)
        {
            grade = "B+";
        }
        else if(overallScore >= 3.00 && overallScore < 3.33)
        {
            grade = "B";
        }
        else if(overallScore >= 2.67 && overallScore < 3.00)
        {
            grade = "B-";
        }
        else if(overallScore >= 2.33 && overallScore < 2.67)
        {
            grade = "C+";
        }
        else if(overallScore >= 2.00 && overallScore < 2.33)
        {
            grade = "C";
        }
        else if(overallScore >= 1.67 && overallScore < 2.00)
        {
            grade = "C-";
        }
        else if(overallScore >= 1.33 && overallScore < 1.67)
        {
            grade = "D+";
        }
        else if(overallScore >= 1.00 && overallScore < 1.33)
        {
            grade = "D";
        }
        else
        {
            grade = "F";
        }
        return grade;
    }
    
    void SetMid1(int marks)
    {
        //Giving value from argument
        midterm1 = marks;
    }
    
    void SetMid2(int marks)
    {
        //Giving value from argument
        midterm2 = marks;
    }
    
    void SetFinal(int marks)
    {
        //Giving value from argument
        finalMarks = marks;
    }
    
    public String toString()
    {
        //The implementation of the toString() function as specified
        String str = "\nStudent Name: " + Name + "\nMidterm 1 Marks: " + midterm1 + "\nMidterm 2 Marks: " + midterm2 +"\nFinal Marks: " + finalMarks + "\nGrade given to Student: "+ calculateGrade();
        return str;
    }
}
