import java.util.*;
import java.io.*;  

public class Driver {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		LexicalAnalyser lexicalAnalyser = new LexicalAnalyser();
		
		System.out.println("Please enter filename.");
		
		Scanner input = new Scanner(System.in);
        String fileName = input.nextLine();
		
		while (!lexicalAnalyser.analyse(fileName))
		{
			System.out.println("Invalid file name entered. Please try again!");
			fileName = input.nextLine();
		}
		lexicalAnalyser.output();
		input.close();
	}

}
