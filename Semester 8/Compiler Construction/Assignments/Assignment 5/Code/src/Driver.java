import java.util.*;
import java.io.*;  

public class Driver {

	public static void main(String[] args) throws IOException {
		LexicalAnalyser lexicalAnalyser = new LexicalAnalyser();
		Parser parser = new Parser();
		Translator translator = new Translator();
		
		System.out.println("Please enter filename.");
		
		Scanner input = new Scanner(System.in);
        String fileName = input.nextLine();
        
		// String fileName = "test.cmm";
		
		while (!lexicalAnalyser.analyse(fileName))
		{
			System.out.println("Invalid file name entered. Please try again!");
			fileName = input.nextLine();
		}
		input.close();
		
		lexicalAnalyser.output();
		
		fileName = "words.txt";
		
		if (!parser.openFile(fileName))
		{
			System.out.println("File doesn't exist. Please try again!");
			//fileName = input.nextLine();
		}
		
		if(!parser.parse()) {
			System.out.println("The code cannot be parsed due to syntax errors.");
			return;
		}
		parser.output();
		
		if (!translator.openFile(fileName))
		{
			System.out.println("File doesn't exist. Please try again!");
			//fileName = input.nextLine();
		}
		
		if(!translator.translate()) {
			System.out.println("The code cannot be translated due to semantic errors.");
			return;
		}
		translator.output();
	}

}
