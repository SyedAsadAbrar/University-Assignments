import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

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
	}
	
	public boolean openFile(String fileName) {
		try {
			this.scanner = new Scanner(new File(fileName));
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
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
		String temp;
		
		nextToken();
				
		G();
		
		if(this.error) {
			return false;
		}
		
		return true;
	}

    private void G(){
        if(!this.error){
            print("G()");
            this.tabs++;
            if(pair.getLexeme().equals("null") && pair.getToken().equals("GS"))
			{
                this.tabs++;
                
                if(this.pair.getToken().equals("GS")) {
				
                    String retval = match("GS");
                    if(!retval.equals("Syntax Error."))
                    {
                        print(retval);
                    }
                    
                    this.tabs--;
                }

                NL();

                EL();
                
                if(this.pair.getToken().equals("GE")) {
				
                    String retval = match("GE");
                    if(!retval.equals("Syntax Error."))
                    {
                        print(retval);
                    }
                    
                    this.tabs--;
                }

                this.tabs--;
			}
			else {
                String retval = match("");
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				this.tabs--;
			}
        }
    }

    private void NL(){
        if(!this.error){
            print("NL()");
            this.tabs++;
            if(this.pair.getToken().equals("NS")) {
				
                String retval = match("NS");
                if(!retval.equals("Syntax Error."))
                {
                    print(retval);
                }
                
                this.tabs--;
            }

            N();

            if(this.pair.getToken().equals("NE")) {
				
                String retval = match("NE");
                if(!retval.equals("Syntax Error."))
                {
                    print(retval);
                }
                
                this.tabs--;
            }

            this.tabs--;
        }
    }

    private void N(){
        if(!this.error){
            print("N()");
            this.tabs++;

            INT();

            OEN();

            this.tabs--;
        }
    }

    private void INT(){
        if(!this.error){
            print("INT()");
            this.tabs++;

            if(this.pair.getToken().equals("INT")) {
				String retval = pair.getLexeme();
				
				String retval2 = match("INT");
				if(!retval2.equals("Syntax Error."))
				{
					print(retval2 + "(" + retval + ")");
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
	
    private void OEN(){
        if(!this.error){
            print("OEN()");
            this.tabs++;
    
            if(this.pair.getToken().equals(",")) {
				
                String retval = match(",");
                if(!retval.equals("Syntax Error."))
                {
                    print(retval);
                }
                
                this.tabs--;

                INT();

                OEN();
            }
            
            else{
                ;
            }

            this.tabs--;
        }
    }

    private void EL(){
        if(!this.error){
            print("EL()");
            this.tabs++;
            if(this.pair.getToken().equals("ES")) {
				
                String retval = match("ES");
                if(!retval.equals("Syntax Error."))
                {
                    print(retval);
                }
                
                this.tabs--;
            }

            E();

            if(this.pair.getToken().equals("EE")) {
				
                String retval = match("EE");
                if(!retval.equals("Syntax Error."))
                {
                    print(retval);
                }
                
                this.tabs--;
            }

            this.tabs--;
        }

    }

    private void E(){
        if(!this.error){
            print("E()");
            this.tabs++;

            ED();

            OEE();

            this.tabs--;
        }
    }

    private void ED(){
        if(!this.error){
            print("ED()");
            this.tabs++;

            if(this.pair.getToken().equals("(")) {
				
                String retval = match("(");
                if(!retval.equals("Syntax Error."))
                {
                    print(retval);
                }
                
                INT();

                retval = match(",");
                if(!retval.equals("Syntax Error."))
                {
                    print(retval);
                }

                INT();

                retval = match(")");
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
	
    private void OEE(){
        if(!this.error){
            print("OEE()");
            this.tabs++;
    
            if(this.pair.getToken().equals(",")) {
				
                String retval = match(",");
                if(!retval.equals("Syntax Error."))
                {
                    print(retval);
                }
                
                this.tabs--;

                ED();

                OEE();
            }
            
            else{
                ;
            }

            this.tabs--;
        }
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
