import java.util.*;
import java.io.*;  

public class Driver {

	public static void main(String[] args) throws IOException {
		LexicalAnalyser lexicalAnalyser = new LexicalAnalyser();
		Parser parser = new Parser();
		Translator translator = new Translator();
		MachineCodeGenerator machineCodeGenerator = new MachineCodeGenerator();
		VirtualMachine virtualMachine = new VirtualMachine();

		System.out.println("Please enter filename.");
		
		Scanner input = new Scanner(System.in);

		String fileName = input.nextLine();
		
		while (!lexicalAnalyser.analyse(fileName))
		{
			System.out.println("Invalid file name entered. Please try again!");
			fileName = input.nextLine();
		}
		
		lexicalAnalyser.output();
		
		fileName = "words.txt";
		
		if (!parser.openFile(fileName))
		{
			System.out.println("File doesn't exist. Please try again!");
			//fileName = input.nextLine();
		}
		
		if(!parser.parse()) {
			System.out.println("The code cannot be parsed due to syntax errors.");
		}
		parser.output();
		
		if (!translator.openFile(fileName))
		{
			System.out.println("File doesn't exist. Please try again!");
			//fileName = input.nextLine();
		}
		
		if(!translator.translate()) {
			System.out.println("The code cannot be translated due to semantic errors.");
		}
		translator.output();

		fileName = "tac.txt";
		
		if (!machineCodeGenerator.readSymbolTable("translator-symboltable.txt"))
		{
			System.out.println("File doesn't exist. Please try again!");
			//fileName = input.nextLine();
		}
		if (!machineCodeGenerator.openFile(fileName))
		{
			System.out.println("File doesn't exist. Please try again!");
			//fileName = input.nextLine();
		}
		machineCodeGenerator.generate();

		machineCodeGenerator.output();

		if (!virtualMachine.readMachineCode("machine-code.txt"))
		{
			System.out.println("File doesn't exist. Please try again!");
			//fileName = input.nextLine();
		}

		fileName = "translator-symboltable.txt";

		if (!virtualMachine.readSymbolTable(fileName))
		{
			System.out.println("File doesn't exist. Please try again!");
			//fileName = input.nextLine();
		}

		virtualMachine.execute();

		input.close();
	}

}
