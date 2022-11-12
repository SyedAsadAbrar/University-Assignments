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
							if(Character.isLetter(tempChar))
							{
								//to handle identifiers
								state = 2;
								switch(tempChar)
								{
									//to handle "int" and "if" 
									case 'i':
										state = 3;
										break;
									//to handle "char" 
									case 'c':
										state = 7;
										break;
									//to handle "else" 
									case 'e':
										state = 11;
										break;
									//to handle "while" 
									case 'w':
										state = 15;
										break;
									//to handle "return" 
									case 'r':
										state = 20;
										break;
									//to handle "jIn" and "jOut"
									case 'j':
										state = 26;
										break;
								}
							}
							else if(Character.isDigit(tempChar))
							{
								state = 32;
							}
							else if(Character.isWhitespace(tempChar))
							{
								beginIndex++;
							}
							else 
							{
								switch(tempChar)
								{
									//to handle "+" and positive numeric constants
									case '+':
										state = 33;
										break;
									//to handle "-" and negative numeric constants
									case '-':
										state = 34;
										break;
									//to handle "*" 
									case '*':
										state = 35;
										break;
									//to handle "/" and "/*"
									case '/':
										state = 36;
										break;
									//to handle "<" and "<=" and assignment operator
									case '<':
										state = 40;
										break;
									//to handle ">" and ">="
									case '>':
										state = 44;
										break;
									//to handle "==" 
									case '=':
										state = 47;
										break;
									//to handle "!=" 
									case '!':
										state = 49;
										break;
									//to handle literal constants
									case '\'':
										state = 51;
										break;
									//to handle strings
									case '\"':
										state = 54;
										break;
									//to handle left paranthesis
									case '(':
										state = 56;
										break;
									//to handle right paranthesis
									case ')':
										state = 57;
										break;
									//to handle left brace
									case '{':
										state = 58;
										break;
									//to handle right brace
									case '}':
										state = 59;
										break;
									//to handle left square bracket
									case '[':
										state = 60;
										break;
									//to handle right square bracket
									case ']':
										state = 61;
										break;	
									//to handle semi-colon
									case ';':
										state = 62;
										break;	
									//to handle colon
									case ':':
										state = 63;
										break;	
									//to handle comma
									case ',':
										state = 64;
										break;
									case '\0':
										state = 1;
										break;
									default:
										state = 100;
										break;
								}
							}
						}
						break;
						
						case 2:
						{
							if (Character.isLetter(tempChar) || Character.isDigit(tempChar))
							{
								;
							}
							else
							{
								TokenLexemePair token = new TokenLexemePair("ID", tempString.substring(beginIndex, i));
								pairs.add(token);
								beginIndex = i;
								i -= 1;
								state = 1;
							}
						}
						break;
						
						case 3:
						{
							if(tempChar == 'n')
							{
								state = 4;
							}
							else if(tempChar == 'f')
							{
								state = 6;
							}
							else if (Character.isLetter(tempChar) || Character.isDigit(tempChar))
							{
								;
							}
							else
							{
								TokenLexemePair token = new TokenLexemePair("ID", tempString.substring(beginIndex, i));
								pairs.add(token);
								beginIndex = i;
								i -= 1;
								state = 1;
							}
						}
						break;
						
						case 4:
						{
							if(tempChar == 't')
							{
								state = 5;
							}
							else if (Character.isLetter(tempChar) || Character.isDigit(tempChar))
							{
								;
							}
							else
							{
								TokenLexemePair token = new TokenLexemePair("ID", tempString.substring(beginIndex, i));
								pairs.add(token);
								beginIndex = i;
								i -= 1;
								state = 1;
							}
						}
						break;
						
						case 5:
						{
							if (Character.isLetter(tempChar) || Character.isDigit(tempChar))
							{
								;
							}
							else
							{
								TokenLexemePair token = new TokenLexemePair("INT", null);
								pairs.add(token);
								beginIndex = i;
								i -= 1;
								state = 1;
							}
						}
						break;
						
						case 6:
						{
							if (Character.isLetter(tempChar) || Character.isDigit(tempChar))
							{
								;
							}
							else
							{
								TokenLexemePair token = new TokenLexemePair("IF", null);
								pairs.add(token);
								beginIndex = i;
								i -= 1;
								state = 1;
							}
						}
						break;
						
						case 7:
						{
							if(tempChar == 'h')
							{
								state = 8;
							}
							else if (Character.isLetter(tempChar) || Character.isDigit(tempChar))
							{
								;
							}
							else
							{
								TokenLexemePair token = new TokenLexemePair("ID", tempString.substring(beginIndex, i));
								pairs.add(token);
								beginIndex = i;
								i -= 1;
								state = 1;
							}
						}
						break;
						
						case 8:
						{
							if(tempChar == 'a')
							{
								state = 9;
							}
							else if (Character.isLetter(tempChar) || Character.isDigit(tempChar))
							{
								;
							}
							else
							{
								TokenLexemePair token = new TokenLexemePair("ID", tempString.substring(beginIndex, i));
								pairs.add(token);
								beginIndex = i;
								i -= 1;
								state = 1;
							}
						}
						break;
						
						case 9:
						{
							if(tempChar == 'r')
							{
								state = 10;
							}
							else if (Character.isLetter(tempChar) || Character.isDigit(tempChar))
							{
								;
							}
							else
							{
								TokenLexemePair token = new TokenLexemePair("ID", tempString.substring(beginIndex, i));
								pairs.add(token);
								beginIndex = i;
								i -= 1;
								state = 1;
							}
						}
						break;
						
						case 10:
						{
							if (Character.isLetter(tempChar) || Character.isDigit(tempChar))
							{
								;
							}
							else
							{
								TokenLexemePair token = new TokenLexemePair("CHAR", null);
								pairs.add(token);
								beginIndex = i;
								i -= 1;
								state = 1;
							}
						}
						break;
						
						case 11:
						{
							if(tempChar == 'l')
							{
								state = 12;
							}
							else if (Character.isLetter(tempChar) || Character.isDigit(tempChar))
							{
								;
							}
							else
							{
								TokenLexemePair token = new TokenLexemePair("ID", tempString.substring(beginIndex, i));
								pairs.add(token);
								beginIndex = i;
								i -= 1;
								state = 1;
							}
						}
						break;
						
						case 12:
						{
							if(tempChar == 's')
							{
								state = 13;
							}
							else if (Character.isLetter(tempChar) || Character.isDigit(tempChar))
							{
								;
							}
							else
							{
								TokenLexemePair token = new TokenLexemePair("ID", tempString.substring(beginIndex, i));
								pairs.add(token);
								beginIndex = i;
								i -= 1;
								state = 1;
							}
						}
						break;
						
						case 13:
						{
							if(tempChar == 'e')
							{
								state = 14;
							}
							else if (Character.isLetter(tempChar) || Character.isDigit(tempChar))
							{
								;
							}
							else
							{
								TokenLexemePair token = new TokenLexemePair("ID", tempString.substring(beginIndex, i));
								pairs.add(token);
								beginIndex = i;
								i -= 1;
								state = 1;
							}
						}
						break;
						
						case 14:
						{
							if (Character.isLetter(tempChar) || Character.isDigit(tempChar))
							{
								;
							}
							else
							{
								TokenLexemePair token = new TokenLexemePair("ELSE", null);
								pairs.add(token);
								beginIndex = i;
								i -= 1;
								state = 1;
							}
						}
						break;
						
						case 15:
						{
							if(tempChar == 'h')
							{
								state = 16;
							}
							else if (Character.isLetter(tempChar) || Character.isDigit(tempChar))
							{
								;
							}
							else
							{
								TokenLexemePair token = new TokenLexemePair("ID", tempString.substring(beginIndex, i));
								pairs.add(token);
								beginIndex = i;
								i -= 1;
								state = 1;
							}
						}
						break;
						
						case 16:
						{
							if(tempChar == 'i')
							{
								state = 17;
							}
							else if (Character.isLetter(tempChar) || Character.isDigit(tempChar))
							{
								;
							}
							else
							{
								TokenLexemePair token = new TokenLexemePair("ID", tempString.substring(beginIndex, i));
								pairs.add(token);
								beginIndex = i;
								i -= 1;
								state = 1;
							}
						}
						break;
						
						case 17:
						{
							if(tempChar == 'l')
							{
								state = 18;
							}
							else if (Character.isLetter(tempChar) || Character.isDigit(tempChar))
							{
								;
							}
							else
							{
								TokenLexemePair token = new TokenLexemePair("ID", tempString.substring(beginIndex, i));
								pairs.add(token);
								beginIndex = i;
								i -= 1;
								state = 1;
							}
						}
						break;
						
						case 18:
						{
							if(tempChar == 'e')
							{
								state = 19;
							}
							else if (Character.isLetter(tempChar) || Character.isDigit(tempChar))
							{
								;
							}
							else
							{
								TokenLexemePair token = new TokenLexemePair("ID", tempString.substring(beginIndex, i));
								pairs.add(token);
								beginIndex = i;
								i -= 1;
								state = 1;
							}
						}
						break;
						
						case 19:
						{
							if (Character.isLetter(tempChar) || Character.isDigit(tempChar))
							{
								;
							}
							else
							{
								TokenLexemePair token = new TokenLexemePair("WHILE", null);
								pairs.add(token);
								beginIndex = i;
								i -= 1;
								state = 1;
							}
						}
						break;
						
						case 20:
						{
							if(tempChar == 'e')
							{
								state = 21;
							}
							else if (Character.isLetter(tempChar) || Character.isDigit(tempChar))
							{
								;
							}
							else
							{
								TokenLexemePair token = new TokenLexemePair("ID", tempString.substring(beginIndex, i));
								pairs.add(token);
								beginIndex = i;
								i -= 1;
								state = 1;
							}
						}
						break;
						
						case 21:
						{
							if(tempChar == 't')
							{
								state = 22;
							}
							else if (Character.isLetter(tempChar) || Character.isDigit(tempChar))
							{
								;
							}
							else
							{
								TokenLexemePair token = new TokenLexemePair("ID", tempString.substring(beginIndex, i));
								pairs.add(token);
								beginIndex = i;
								i -= 1;
								state = 1;
							}
						}
						break;
						
						case 22:
						{
							if(tempChar == 'u')
							{
								state = 23;
							}
							else if (Character.isLetter(tempChar) || Character.isDigit(tempChar))
							{
								;
							}
							else
							{
								TokenLexemePair token = new TokenLexemePair("ID", tempString.substring(beginIndex, i));
								pairs.add(token);
								beginIndex = i;
								i -= 1;
								state = 1;
							}
						}
						break;
						
						case 23:
						{
							if(tempChar == 'r')
							{
								state = 24;
							}
							else if (Character.isLetter(tempChar) || Character.isDigit(tempChar))
							{
								;
							}
							else
							{
								TokenLexemePair token = new TokenLexemePair("ID", tempString.substring(beginIndex, i));
								pairs.add(token);
								beginIndex = i;
								i -= 1;
								state = 1;
							}
						}
						break;
						
						case 24:
						{
							if(tempChar == 'n')
							{
								state = 25;
							}
							else if (Character.isLetter(tempChar) || Character.isDigit(tempChar))
							{
								;
							}
							else
							{
								TokenLexemePair token = new TokenLexemePair("ID", tempString.substring(beginIndex, i));
								pairs.add(token);
								beginIndex = i;
								i -= 1;
								state = 1;
							}
						}
						break;
						
						case 25:
						{
							if (Character.isLetter(tempChar) || Character.isDigit(tempChar))
							{
								;
							}
							else
							{
								TokenLexemePair token = new TokenLexemePair("RETURN", null);
								pairs.add(token);
								beginIndex = i;
								i -= 1;
								state = 1;
							}
						}
						break;
						
						case 26:
						{
							if(tempChar == 'I')
							{
								state = 27;
							}
							else if(tempChar == 'O')
							{
								state = 29;
							}
							else if (Character.isLetter(tempChar) || Character.isDigit(tempChar))
							{
								;
							}
							else
							{
								TokenLexemePair token = new TokenLexemePair("ID", tempString.substring(beginIndex, i));
								pairs.add(token);
								beginIndex = i;
								i -= 1;
								state = 1;
							}
						}
						break;
						
						case 27:
						{
							if(tempChar == 'n')
							{
								state = 28;
							}
							else if (Character.isLetter(tempChar) || Character.isDigit(tempChar))
							{
								;
							}
							else
							{
								TokenLexemePair token = new TokenLexemePair("ID", tempString.substring(beginIndex, i));
								pairs.add(token);
								beginIndex = i;
								i -= 1;
								state = 1;
							}
						}
						break;
						
						case 28:
						{
							if (Character.isLetter(tempChar) || Character.isDigit(tempChar))
							{
								;
							}
							else
							{
								TokenLexemePair token = new TokenLexemePair("jIn", null);
								pairs.add(token);
								beginIndex = i;
								i -= 1;
								state = 1;
							}
						}
						break;
						
						case 29:
						{
							if(tempChar == 'u')
							{
								state = 30;
							}
							else if (Character.isLetter(tempChar) || Character.isDigit(tempChar))
							{
								;
							}
							else
							{
								TokenLexemePair token = new TokenLexemePair("ID", tempString.substring(beginIndex, i));
								pairs.add(token);
								beginIndex = i;
								i -= 1;
								state = 1;
							}
						}
						break;
						
						case 30:
						{
							if(tempChar == 't')
							{
								state = 31;
							}
							else if (Character.isLetter(tempChar) || Character.isDigit(tempChar))
							{
								;
							}
							else
							{
								TokenLexemePair token = new TokenLexemePair("ID", tempString.substring(beginIndex, i));
								pairs.add(token);
								beginIndex = i;
								i -= 1;
								state = 1;
							}
						}
						break;
						
						case 31:
						{
							if (Character.isLetter(tempChar) || Character.isDigit(tempChar))
							{
								;
							}
							else
							{
								TokenLexemePair token = new TokenLexemePair("jOut", null);
								pairs.add(token);
								beginIndex = i;
								i -= 1;
								state = 1;
							}
						}
						break;
						
						case 32:
						{
							if (Character.isDigit(tempChar))
							{
								;
							}
							else
							{
								TokenLexemePair token = new TokenLexemePair("NC", tempString.substring(beginIndex, i));
								pairs.add(token);
								beginIndex = i;
								i -= 1;
								state = 1;
							}
						}
						break;
						
						case 33:
						{
							if (Character.isDigit(tempChar))
							{
								state = 32;
							}
							else
							{
								TokenLexemePair token = new TokenLexemePair("+", null);
								pairs.add(token);
								beginIndex = i;
								i -= 1;
								state = 1;
							}
						}
						break;
												
						case 34:
						{
							if (Character.isDigit(tempChar))
							{
								state = 32;
							}
							else
							{
								TokenLexemePair token = new TokenLexemePair("-", null);
								pairs.add(token);
								beginIndex = i;
								i -= 1;
								state = 1;
							}
						}
						break;
						
						case 35:
						{
							TokenLexemePair token = new TokenLexemePair("*", null);
							pairs.add(token);
							beginIndex = i;
							i -= 1;
							state = 1;
						}
						break;
						
						case 36:
						{
							if (tempChar == '*')
							{
								state = 37;
							}
							else
							{
								TokenLexemePair token = new TokenLexemePair("/", null);
								pairs.add(token);
								beginIndex = i;
								i -= 1;
								state = 1;
							}
						}
						break;
												
						case 37:
						{
							if (tempChar == '*')
							{
								state = 38;
							}
							else
							{
								;
							}
						}
						break;
						
						case 38:
						{
							if (tempChar == '/')
							{
								state = 39;
							}
							else
							{
								state = 100;
							}
						}
						break;
						
						case 39:
						{
							beginIndex = i;
							i -= 1;
							state = 1;
						}
						break;
						
						case 40:
						{
							if(tempChar == '-')
							{
								state = 41;
							}
							else if (tempChar == '=')
							{
								state = 42;
							}
							else
							{
								state = 43;
							}
						}
						break;
						
						case 41:
						{
							TokenLexemePair token = new TokenLexemePair("<-", null);
							pairs.add(token);
							beginIndex = i;
							i -= 1;
							state = 1;
						}
						break;
						
						case 42:
						{
							TokenLexemePair token = new TokenLexemePair("RO", "LE");
							pairs.add(token);
							beginIndex = i;
							i -= 1;
							state = 1;
						}
						break;
						
						case 43:
						{
							TokenLexemePair token = new TokenLexemePair("RO", "LT");
							pairs.add(token);
							beginIndex = i;
							i -= 1;
							state = 1;
						}
						break;
						
						case 44:
						{
							if(tempChar == '=')
							{
								state = 45;
							}
							else
							{
								state = 46;
							}
						}
						break;
						
						case 45:
						{
							TokenLexemePair token = new TokenLexemePair("RO", "GTE");
							pairs.add(token);
							beginIndex = i;
							i -= 1;
							state = 1;
						}
						break;
						
						case 46:
						{
							TokenLexemePair token = new TokenLexemePair("RO", "GT");
							pairs.add(token);
							beginIndex = i;
							i -= 1;
							state = 1;
						}
						break;
						
						case 47:
						{
							if(tempChar == '=')
							{
								state = 61;
							}
							else
							{
								state = 100;
							}
						}
						break;
						
						case 48:
						{
							TokenLexemePair token = new TokenLexemePair("RO", "EQ");
							pairs.add(token);
							beginIndex = i;
							i -= 1;
							state = 1;
						}
						break;
						
						case 49:
						{
							if(tempChar == '=')
							{
								state = 50;
							}
							else
							{
								state = 100;
							}
						}
						break;
						
						case 50:
						{
							TokenLexemePair token = new TokenLexemePair("RO", "NE");
							pairs.add(token);
							beginIndex = i;
							i -= 1;
							state = 1;
						}
						break;
						
						case 51:
						{
							if(Character.isLetter(tempChar))
							{
								state = 52;
							}
							else
							{
								state = 100;
							}
						}
						break;
						
						case 52:
						{
							if(tempChar == '\'')
							{
								state = 53;
							}
							else
							{
								state = 100;
							}
						}
						break;
						
						case 53:
						{
							TokenLexemePair token = new TokenLexemePair("LC", tempString.substring(beginIndex, i));
							pairs.add(token);
							beginIndex = i;
							i -= 1;
							state = 1;
						}
						break;
						
						case 54:
						{
							if(tempChar == '\"')
							{
								state = 55;
							}
							else
							{
								;
							}
						}
						break;
						
						case 55:
						{
							TokenLexemePair token = new TokenLexemePair("STR", tempString.substring(beginIndex+1, i-1));
							pairs.add(token);
							beginIndex = i;
							i -= 1;
							state = 1;
						}
						break;
						
						case 56:
						{
							TokenLexemePair token = new TokenLexemePair("(", null);
							pairs.add(token);
							beginIndex = i;
							i -= 1;
							state = 1;
						}
						break;
						
						case 57:
						{
							TokenLexemePair token = new TokenLexemePair(")", null);
							pairs.add(token);
							beginIndex = i;
							i -= 1;
							state = 1;
						}
						break;
						
						case 58:
						{
							TokenLexemePair token = new TokenLexemePair("{",null);
							pairs.add(token);
							beginIndex = i;
							i -= 1;
							state = 1;
						}
						break;
						
						case 59:
						{
							TokenLexemePair token = new TokenLexemePair("}", null);
							pairs.add(token);
							beginIndex = i;
							i -= 1;
							state = 1;
						}
						break;
						
						case 60:
						{
							TokenLexemePair token = new TokenLexemePair("[", null);
							pairs.add(token);
							beginIndex = i;
							i -= 1;
							state = 1;
						}
						break;
						
						case 61:
						{
							TokenLexemePair token = new TokenLexemePair("]", null);
							pairs.add(token);
							beginIndex = i;
							i -= 1;
							state = 1;
						}
						break;
						
						case 62:
						{
							TokenLexemePair token = new TokenLexemePair(";", null);
							pairs.add(token);
							beginIndex = i;
							i -= 1;
							state = 1;
						}
						break;
						
						case 63:
						{
							TokenLexemePair token = new TokenLexemePair(":", null);
							pairs.add(token);
							beginIndex = i;
							i -= 1;
							state = 1;
						}
						break;
						
						case 64:
						{
							TokenLexemePair token = new TokenLexemePair(",", null);
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
