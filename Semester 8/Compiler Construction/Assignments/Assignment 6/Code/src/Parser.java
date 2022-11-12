import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Parser {
	private ArrayList<String> parsetree;
	private ArrayList<String> varIDs;
	private ArrayList<String> varTypes;
	int tabs;
	Scanner scanner;
	TokenLexemePair pair;
	boolean EOF;
	boolean error;
	TokenLexemePair errorCause;
	private final Set<String> reservedKeywords;

	public Parser() {
		this.parsetree = new ArrayList<>();
		this.varIDs = new ArrayList<>();
		this.varTypes = new ArrayList<>();		
		this.tabs = 0;
		this.scanner = null;
		this.pair = null;
		this.EOF = false;
		this.error = false;
		this.errorCause = null;

		this.reservedKeywords = new HashSet<String>(Arrays.asList(new String[] {"if", "else", "while", "jIn", "jOut", "return", "ret", "int", "char", "IF", "ELSE", "WHILE", "RET", "RETURN", "INT", "CHAR"}));
	}
	
	public boolean openFile(String fileName) {
		try {
			this.scanner = new Scanner(new File(fileName));
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private boolean nextToken() {
		if(scanner.hasNextLine())
		{
			String tempString = scanner.nextLine();
			int index = tempString.indexOf(",") + 1;
			
			while(tempString.charAt(index) == ',')
			{
				index += 1;
			}
			
			if (tempString.charAt(index) != ',')
			{
				index -= 1;
			}
			
			this.pair = new TokenLexemePair(tempString.substring(1,index), tempString.substring(index+2, tempString.length()-1));		
			return true;
		}
		return false;
	}
	
	public boolean parse() {
		nextToken();
				
		P();
		
		if(this.error) {
			return false;
		}
		
		return true;
	}

	private void P() {
		if(!this.error) {
			print("P()");
			this.tabs++;
			if(pair.getLexeme().equals("") && pair.getToken().equals(""))
			{
				String retval = match("");
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				this.tabs--;
			}
			else {
				this.tabs++;
				D();
				P();
			}
		}
	}
	
	private void D() {
		if (!this.error)
		{
			print("D()");
			this.tabs++;
			
			String T = T();
			
			String id = ID();
			
			R(T, id);
			
			this.tabs--;
		}
	}
	
	private String T() {
		if(!this.error)
		{
			print("T()");
			this.tabs++;
			
			String retval = null;
			
			if(this.pair.getToken().equalsIgnoreCase("int")) {
				
				retval = match("INT");
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				
				this.tabs--;
			}
			else if(this.pair.getToken().equalsIgnoreCase("char")) {

				retval = match("CHAR");
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				
				this.tabs--;
			}
			else {
				this.error = true;
				System.out.println("Syntax error.");
			}
			this.tabs--;
			return retval;
		}
		return null;
	}
	
	private String ID() {
		if(!this.error) {
			print("ID()");
			this.tabs++;
			
			String retval = pair.getLexeme();
			
			// tempVar followed by an integer is a reserved word, used while generating 
			// three address code so variable names starting with "tempVar" are not
			// allowed. This is a syntax error so dealt with in Parser.
			// Reference: https://en.wikipedia.org/wiki/Reserved_word
			if (retval.startsWith("tempVar")){
				this.errorCause = new TokenLexemePair(pair.getToken(), pair.getLexeme());
				this.error = true;
				return "Syntax Error: Variable name cannot start with \"tempVar\".";
			}

			// Checking if variable name is a keyword
			if (reservedKeywords.contains(retval)){
				this.errorCause = new TokenLexemePair(pair.getToken(), pair.getLexeme());
				this.error = true;
				return "Syntax Error: Variable name be a reserved keyword";
			}

			String retval2 = match("ID");
			if(!retval2.equals("Syntax Error."))
			{
				print(retval2 + "(" + retval + ")");
			}
			this.tabs--;

			this.tabs--;
			return retval;
		}
		return null;
	}
	
	private void R(String T, String id) {
		if(!this.error) {
			print("R()");
			this.tabs++;
			
			if(this.pair.getToken().equals(",") || this.pair.getToken().equals(";")) {		
				varTypes.add(T);
				varIDs.add(id);

				AV();
				
				String retval = match(";");
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				this.tabs--;

				SL();
			}
			else if(this.pair.getToken().equals("(")) {
				varTypes.add("FUNC");
				varIDs.add(id);

				String retval = match("(");
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				
				this.tabs--;
				
				NPL();

				retval = match(")");
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				
				this.tabs--;
				
				retval = match("{");
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				
				this.tabs--;
				
				SL();
				
				retval = match("}");
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				
				this.tabs--;
			}
			else {
				this.error = true;
				System.out.println("Syntax error.");
			}
			this.tabs--;
		}
	}
	
	private void NPL() {
		if(!this.error) {
			print("NPL()");
			this.tabs++;
			
			if(pair.getLexeme().equals("") && pair.getToken().equals(""))
			{
				String retval = match("");
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				this.tabs--;
			}
			else {
				PL();
			}
			
			this.tabs--;
		}
	}
	
	private void PL() {
		if(!this.error) {
			print("PL()");
			this.tabs++;
			
			String retval = T();
			varTypes.add(retval);
			
			retval = ID();
			varIDs.add(retval);
			
			OPT();
			
			this.tabs--;
		}
	}
	
	private void OPT() {
		if(!this.error) {
			print("OPT()");
			this.tabs++;
			
			if(this.pair.getToken().equals(",")) {
				String retval = match(",");
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				this.tabs--;
				
				PL();
			}
			else if(pair.getLexeme().equals("") && pair.getToken().equals(""))
			{
				String retval = match("");
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				this.tabs--;
			}
			else {
				;
			}
			
			this.tabs--;
		}
	}
	
	private void SL()
	{
		if(!this.error) {
			print("SL()");
			this.tabs++;
			
			if(S())
			{
				SL();
			}
			else
			{
				parsetree.remove(parsetree.size()-1);
			}
			
			this.tabs--;
		}
	}

	private boolean S() {
		if(!this.error) {
			print("S()");
			this.tabs++;
			
			boolean bool = true;
			
			if(this.pair.getToken().equalsIgnoreCase("int") || this.pair.getToken().equalsIgnoreCase("char")) {
				String retval = T();
				varTypes.add(retval);
				
				retval = ID();
				varIDs.add(retval);
				
				AV();
				
				retval = match(";");
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				this.tabs--;
			}
			else if(this.pair.getToken().equalsIgnoreCase("ID")) {
				ID();

				String retval = match("<-");
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				this.tabs--;
				VAL();
				retval = match(";");
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				this.tabs--;
			}
			else if(this.pair.getToken().equals("jOut")) {
				String retval = match("jOut");
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				this.tabs--;
				
				retval = match("(");
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				
				this.tabs--;
				
				if(this.pair.getToken().equals("STR")) {
					STR();
				}
				else{
					VAL();
				}
				
				retval = match(")");
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				
				this.tabs--;
				
				retval = match(";");
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				
				this.tabs--;
			}
			else if(this.pair.getToken().equals("jIn")) {
				String retval = match("jIn");
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				
				this.tabs--;
				
				retval = match("(");
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				
				this.tabs--;
				
				ID();
				
				retval = match(")");
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				
				this.tabs--;
				
				retval = match(";");
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				
				this.tabs--;
			}
			else if(this.pair.getLexeme().equals("") && this.pair.getToken().equals(""))
			{
				bool = false;
			}
			else if(this.pair.getToken().equalsIgnoreCase("WHILE")) {
				String retval = match("WHILE");
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				this.tabs--;
				retval = match("(");
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				this.tabs--;
				REXP();
				retval = match(")");
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				this.tabs--;
				retval = match("{");
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				this.tabs--;
				SL();
				retval = match("}");
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				this.tabs--;
			}
			else if(this.pair.getToken().equalsIgnoreCase("IF")) {
				String retval = match("IF");
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				this.tabs--;
				retval = match("(");
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				this.tabs--;
				REXP();
				retval = match(")");
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				this.tabs--;
				retval = match("{");
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				this.tabs--;
				SL();
				retval = match("}");
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				this.tabs--;
				IE();
			}
			else if(this.pair.getToken().equalsIgnoreCase("RETURN")) {
				String retval = match("RETURN");
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				this.tabs--;
				
				VAL();

				retval = match(";");
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				this.tabs--;
			}
			else {
				bool = false;
			}
			
			this.tabs--;
			return bool;
		}
		return false;
	}
	
	private boolean AV() {
		if(!this.error) {
			print("AV()");
			this.tabs++;
			
			if(this.pair.getToken().equals(",")) {
				String retval = match(",");
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				this.tabs--;
				
				retval = ID();
				varIDs.add(retval);
				varTypes.add(varTypes.get(varTypes.size()-1));
				
				AV();
				this.tabs--;
				return true;
			}
			
			else {
				this.tabs--;
				return false;
			}
		}
		return false;
	}
	

	private void STR() {
		if(!this.error) {
			print("STR()");
			this.tabs++;
			
			String retval = pair.getLexeme();
			
			String retval2 = match("STR");
			if(!retval2.equals("Syntax Error."))
			{
				print(retval2 + "(" + retval + ")");
			}
						
			this.tabs--;	

			this.tabs--;
		}
	}
	
	
	private void VAL() {
		if(!this.error) {
			print("VAL()");
			this.tabs++;
			
			if(this.pair.getToken().equals("LC")) {
				String retval = pair.getLexeme();
				
				String retval2 = match("LC");
				if(!retval2.equals("Syntax Error."))
				{
					print(retval2 + "(" + retval + ")");
				}
				
				this.tabs--;
			}
			else if(this.pair.getToken().equals("ID") || this.pair.getToken().equals("NC") || this.pair.getToken().equals("(")) {
				EXP();
			}
			
			this.tabs--;
		}
	}
	
	private void EXP() {
		if(!this.error) {
			print("EXP()");
			this.tabs++;
			
			VAL2();

			EXP2();
			
			this.tabs--;
		}
	}
	
	private void EXP2() {
		if(!this.error) {
			print("EXP2()");
			this.tabs++;
			
			if(this.pair.getToken().equals("+") || this.pair.getToken().equals("-")) {
				String retval = match(this.pair.getToken());
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				
				this.tabs--;
				
				VAL2();
				
				EXP2();
			}
			else {
				;
			}
			
			this.tabs--;
		}
	}
	
	private void REXP() {
		if(!this.error) {
			print("REXP()");
			this.tabs++;
			
			EXP();
			RO();
			EXP();
			
			this.tabs--;
		}
	}
	
	private void VAL2() {
		if(!this.error) {
			print("VAL2()");
			this.tabs++;
			
			VAL4();
			
			VAL3();
			
			this.tabs--;
		}
	}
	
	private void VAL3() {
		if(!this.error) {
			print("VAL3()");
			this.tabs++;
			
			if(this.pair.getToken().equals("*") || this.pair.getToken().equals("/")) {
				String retval = match(this.pair.getToken());
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				
				this.tabs--;
				VAL4();
				
				VAL3();
			}
			else {
				;
			}
			
			this.tabs--;
		}
	}
	
	private void VAL4() {
		if(!this.error) {
			print("VAL4()");
			this.tabs++;
			
			if(this.pair.getToken().equals("ID")) {
				String retval = pair.getLexeme();
				
				String retval2 = match("ID");
				if(!retval2.equals("Syntax Error."))
				{
					print(retval2 + "(" + retval + ")");
				}
				
				this.tabs--;
			}
			else if(this.pair.getToken().equals("NC")) {
				String retval = pair.getLexeme();
				
				String retval2 = match("NC");
				if(!retval2.equals("Syntax Error."))
				{
					print(retval2 + "(" + retval + ")");
				}
				this.tabs--;
			}
			else if(this.pair.getToken().equals("(")) {
				String retval = match("(");
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				this.tabs--;
				
				EXP();
				
				retval = match(")");
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				this.tabs--;
			}
			this.tabs--;
		}
	}
	
	private void RO() {
		if(!this.error) {
			print("RO()");
			this.tabs++;
			
			String retval = pair.getLexeme();
			
			String retval2 = match("RO");
			if(!retval2.equals("Syntax Error."))
			{
				print(retval2 + "(" + retval + ")");
			}
			this.tabs--;
			
			this.tabs--;
		}
	}
	
	private boolean optC() {
		if(!this.error) {
			print("optC()");
			this.tabs++;
			
			if(this.pair.getToken().equalsIgnoreCase("IF")) {
				String retval = match("IF");
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				this.tabs--;
				
				retval = match("(");
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				this.tabs--;
				REXP();
				retval = match(")");
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				this.tabs--;
				
				this.tabs--;
				return true;
			}
			
			else {
				this.tabs--;
				return false;
			}
		}
		return false;
	}
	
	private boolean IE() {
		if(!this.error) {
			print("IE()");
			this.tabs++;
			
			if(this.pair.getToken().equalsIgnoreCase("ELSE")) {
				String retval = match("ELSE");
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				this.tabs--;
				
				optC();
				
				retval = match("{");
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				this.tabs--;
				SL();
				retval = match("}");
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				this.tabs--;
				
				IE();
				
				this.tabs--;
				return true;
			}
			
			else {
				this.tabs--;
				return false;
			}
		}
		return false;
	}
	
	private String match(String token) {
		print("match()");
		this.tabs++;
				
		if (pair.getToken().equals(token)) {
			String retval = pair.getToken();
			if (!nextToken())
			{
				this.EOF = true;
				pair = new TokenLexemePair("", "");
			}
			return retval;
		}
		
		this.errorCause = new TokenLexemePair(pair.getToken(), pair.getLexeme());
		this.error = true;
		return "Syntax Error.";
	}
	
	private void print(String text) {
		String temp = "";
		for (int i = 0; i < tabs-1; i++) {
			temp += ":\t";
		}
		temp += "|__" + text;
		parsetree.add(temp);
	}
	
	public void output() throws IOException
	{
		if(!this.error) {
			FileWriter fileWriter = new FileWriter("parsetree.txt");
		    PrintWriter printWriter = new PrintWriter(fileWriter);
			for (int i = 0; i < parsetree.size(); i++)
			{
				printWriter.println(parsetree.get(i));
			}
		    printWriter.close();
			
			System.out.println("Parse tree for the input code written to file \"parsetree.txt\".");

			fileWriter = new FileWriter("parser-symboltable.txt");
		    printWriter = new PrintWriter(fileWriter);
		    
		    printWriter.println("Identifier: Data Type");
		    
		   	for (int i = 0; i < varTypes.size(); i++)
			{
				printWriter.println(varIDs.get(i) + ": " + varTypes.get(i));
			}
		    printWriter.close();
			
		    System.out.println("Names and data types of all program identifiers for the input code written to file \"parser-symboltable.txt\".");
		}
		else {
			System.out.println("Syntax error caused by the token-lexeme pair, " + this.errorCause.toString() + ".");
		}
	 }	
}
