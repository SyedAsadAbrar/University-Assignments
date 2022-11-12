import java.util.*;
import java.io.*;  

public class LexicalAnalyser {
	private ArrayList<Error> errors;
	private ArrayList<TokenLexemePair> pairs;
	
	public LexicalAnalyser() {
		errors = new ArrayList<>();
		pairs = new ArrayList<>();
	}
	
	public boolean analyse(String fileName)
	{
 		int lineNumber = 1;
		String tempString;
		char tempChar;
		int state;
		int beginIndex;
		try {  
			Scanner scanner=new Scanner(new File(fileName));

			state = 1;
			while(scanner.hasNextLine())  
			{  
				tempString = scanner.nextLine();
				beginIndex = 0;
				for (int i = 0; i <= tempString.length(); i++)
				{
					if(i < tempString.length())
					{
						tempChar = tempString.charAt(i);
					}
					else
					{
						tempChar = '\0';
					}
					switch(state) 
					{
						case 1:
						{
							if(tempChar == '<')
							{
								state = 2;
							}
							else if(Character.isDigit(tempChar) && tempChar > '0')
							{
								state = 20;
							}
							else if(tempChar == ','){
								state = 21;
							}
							else if(tempChar == '(')
							{
								state = 22;
							}
							else if(tempChar == ')')
							{
								state = 23;
							}
							else if(Character.isWhitespace(tempChar))
							{
								beginIndex++;
							}
							else if(tempChar == '\0'){
								state = 1;
							}
							else{
								state = 100;
							}
						}
						break;

						case 2:
						{
							if(tempChar == 'g'){
								state = 3;
							}
							else if(tempChar == 'n'){
								state = 9;
							}
							else if(tempChar == 'e'){
								state = 14;
							}
							else if(tempChar =='/')
							{
								state = 19;
							}
							else{
								state = 100;
							}
						}
						break;

						case 3:
						{
							if(tempChar == 'r'){
								state = 4;
							}
							else{
								state = 100;
							}
						}
						break;

						case 4:
						{
							if(tempChar == 'a'){
								state = 5;
							}
							else{
								state = 100;
							}
						}
						break;

						case 5:
						{
							if(tempChar == 'p'){
								state = 6;
							}
							else{
								state = 100;
							}
						}
						break;

						case 6:
						{
							if(tempChar == 'h'){
								state = 7;
							}
							else{
								state = 100;
							}
						}
						break;

						case 7:
						{
							if(tempChar == '>'){
								state = 8;
							}
							else{
								state = 100;
							}
						}
						break;

						case 8:
						{
							TokenLexemePair token = new TokenLexemePair("GS", null);
							pairs.add(token);
							beginIndex = i;
							i -= 1;
							state = 1;
						}
						break;

						case 9:
						{
							if(tempChar == 'o'){
								state = 10;
							}
							else{
								state = 100;
							}
						}
						break;

						case 10:
						{
							if(tempChar == 'd'){
								state = 11;
							}
							else{
								state = 100;
							}
						}
						break;

						case 11:
						{
							if(tempChar == 'e'){
								state = 12;
							}
							else{
								state = 100;
							}
						}
						break;

						case 12:
						{
							if(tempChar == '>'){
								state = 13;
							}
							else{
								state = 100;
							}
						}
						break;

						case 13:
						{
							TokenLexemePair token = new TokenLexemePair("NS", null);
							pairs.add(token);
							beginIndex = i;
							i -= 1;
							state = 1;
						}
						break;

						case 14:
						{
							if(tempChar == 'd'){
								state = 15;
							}
							else{
								state = 100;
							}
						}
						break;

						case 15:
						{
							if(tempChar == 'g'){
								state = 16;
							}
							else{
								state = 100;
							}
						}
						break;

						case 16:
						{
							if(tempChar == 'e'){
								state = 17;
							}
							else{
								state = 100;
							}
						}
						break;

						case 17:
						{
							if(tempChar == '>'){
								state = 18;
							}
							else{
								state = 100;
							}
						}
						break;

						case 18:
						{
							TokenLexemePair token = new TokenLexemePair("ES", null);
							pairs.add(token);
							beginIndex = i;
							i -= 1;
							state = 1;
						}
						break;

						case 19:
						{
							if(tempChar == 'g'){
								state = 24;
							}
							else if(tempChar == 'n'){
								state = 30;
							}
							else if(tempChar == 'e'){
								state = 35;
							}
							else{
								state = 100;
							}
						}
						break;

						case 20:
						{
							if(Character.isDigit(tempChar)){
								;
							}
							else{
								TokenLexemePair token = new TokenLexemePair("INT", tempString.substring(beginIndex, i));
								pairs.add(token);
								beginIndex = i;
								i -= 1;
								state = 1;
							}
						}
						break;

						case 21:
						{
							TokenLexemePair token = new TokenLexemePair(",", null);
							pairs.add(token);
							beginIndex = i;
							i -= 1;
							state = 1;
						}
						break;

						case 22:
						{
							TokenLexemePair token = new TokenLexemePair("(", null);
							pairs.add(token);
							beginIndex = i;
							i -= 1;
							state = 1;
						}
						break;
						
						case 23:
						{
							TokenLexemePair token = new TokenLexemePair(")", null);
							pairs.add(token);
							beginIndex = i;
							i -= 1;
							state = 1;
						}
						break;

						case 24:
						{
							if(tempChar == 'r'){
								state = 25;
							}
							else{
								state = 100;
							}
						}
						break;

						case 25:
						{
							if(tempChar == 'a'){
								state = 26;
							}
							else{
								state = 100;
							}
						}
						break;

						case 26:
						{
							if(tempChar == 'p'){
								state = 27;
							}
							else{
								state = 100;
							}
						}
						break;

						case 27:
						{
							if(tempChar == 'h'){
								state = 28;
							}
							else{
								state = 100;
							}
						}
						break;

						case 28:
						{
							if(tempChar == '>'){
								state = 29;
							}
							else{
								state = 100;
							}
						}
						break;

						case 29:
						{
							TokenLexemePair token = new TokenLexemePair("GE", null);
							pairs.add(token);
							beginIndex = i;
							i -= 1;
							state = 1;
						}
						break;

						case 30:
						{
							if(tempChar == 'o'){
								state = 31;
							}
							else{
								state = 100;
							}
						}
						break;

						case 31:
						{
							if(tempChar == 'd'){
								state = 32;
							}
							else{
								state = 100;
							}
						}
						break;

						case 32:
						{
							if(tempChar == 'e'){
								state = 33;
							}
							else{
								state = 100;
							}
						}
						break;

						case 33:
						{
							if(tempChar == '>'){
								state = 34;
							}
							else{
								state = 100;
							}
						}
						break;

						case 34:
						{
							TokenLexemePair token = new TokenLexemePair("NE", null);
							pairs.add(token);
							beginIndex = i;
							i -= 1;
							state = 1;
						}
						break;

						case 35:
						{
							if(tempChar == 'd'){
								state = 36;
							}
							else{
								state = 100;
							}
						}
						break;

						case 36:
						{
							if(tempChar == 'g'){
								state = 37;
							}
							else{
								state = 100;
							}
						}
						break;

						case 37:
						{
							if(tempChar == 'e'){
								state = 38;
							}
							else{
								state = 100;
							}
						}
						break;

						case 38:
						{
							if(tempChar == '>'){
								state = 39;
							}
							else{
								state = 100;
							}
						}
						break;

						case 39:
						{
							TokenLexemePair token = new TokenLexemePair("EE", null);
							pairs.add(token);
							beginIndex = i;
							i -= 1;
							state = 1;
						}
						break;

						case 100:
						{
							Error error = new Error(lineNumber, tempString);
							
							if (!errors.contains(error))
							{
								errors.add(error);
							}
							
							state = 1;
						}
						break;
					}
				}
				lineNumber++;
			}  
			scanner.close(); 
		}
		catch(IOException e)  
		{  
			e.printStackTrace();  
			return false;
		} 
		return true;
	}
	public void output() throws IOException
	{
		FileWriter fileWriter = new FileWriter("words.txt");
	    PrintWriter printWriter = new PrintWriter(fileWriter);
		for (int i = 0; i < pairs.size(); i++)
		{
			printWriter.println(pairs.get(i));
		}
	    printWriter.close();
		
		System.out.println("Token lexeme pairs for the input code written to file \"words.txt\".");

		fileWriter = new FileWriter("symboltable.txt");
	    printWriter = new PrintWriter(fileWriter);
	    
	    ArrayList<String> names = new ArrayList<>();
	    
		for (int i = 0; i < pairs.size(); i++)
		{
			if(!names.contains(pairs.get(i).getName()) && pairs.get(i).isID())
			{
				names.add(pairs.get(i).getName());
			}
		}
		
		for (int i = 0; i < names.size(); i++)
		{
			printWriter.println(names.get(i));
		}
	    printWriter.close();
		
	    System.out.println("Names of all program identifiers for the input code written to file \"symboltable.txt\".");

	    
		if(errors.size() == 0)
		{
			System.out.println("\nNo lexical errors in code.");
		}
		else
		{
			System.out.println("\nLexical errors for the input code.");
			System.out.println("Line number" + "\t" + "Line");
			for (int i = 0; i < errors.size(); i++)
			{
				System.out.println(errors.get(i));
			}
		}
	}
	
}
