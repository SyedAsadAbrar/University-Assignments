import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Translator {
	private ArrayList<String> output;
	private ArrayList<String> varIDs;
	private ArrayList<String> varTypes;
	Scanner scanner;
	boolean EOF;
	boolean error;
	TokenLexemePair errorCause;
	TokenLexemePair pair;
	
	private int tempCount;
	
	private String typeVal;

	public Translator() {
		this.output = new ArrayList<>();
		this.varIDs = new ArrayList<>();
		this.varTypes = new ArrayList<>();		

		this.scanner = null;
		this.pair = null;
		this.EOF = false;
		this.error = false;
		this.errorCause = null;
		
		this.tempCount = 1;
		this.typeVal = null;
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
    
    public boolean translate() {
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
            
            if(pair.getLexeme().equals("null") && pair.getToken().equals("GS"))
			{                
                if(this.pair.getToken().equals("GS")) {
				
                    String retval = match("GS");
                    if(!retval.equals("Syntax Error."))
                    {
                        print(retval);
                    }
                    
                    
                }

                NL();

                EL();
                
                if(this.pair.getToken().equals("GE")) {
				
                    String retval = match("GE");
                    if(!retval.equals("Syntax Error."))
                    {
                        print(retval);
                    }
                    
                    
                }

                
			}
			else {
                String retval = match("");
				if(!retval.equals("Syntax Error."))
				{
					print(retval);
				}
				
			}
        }
    }

    private void NL(){
        if(!this.error){
            print("NL()");
            
            if(this.pair.getToken().equals("NS")) {
				
                String retval = match("NS");
                if(!retval.equals("Syntax Error."))
                {
                    print(retval);
                }
                
                
            }

            N();

            if(this.pair.getToken().equals("NE")) {
				
                String retval = match("NE");
                if(!retval.equals("Syntax Error."))
                {
                    print(retval);
                }
                
                
            }

            
        }
    }

    private void N(){
        if(!this.error){
            print("N()");
            

            INT();

            OEN();

            
        }
    }

    private void INT(){
        if(!this.error){
            print("INT()");
            

            if(this.pair.getToken().equals("INT")) {
				String retval = pair.getLexeme();
				
				String retval2 = match("INT");
				if(!retval2.equals("Syntax Error."))
				{
					print(retval2 + "(" + retval + ")");
				}
				
            }
            else {
				this.error = true;
				System.out.println("Syntax error.");
			}

            
        }
    }
	
    private void OEN(){
        if(!this.error){
            print("OEN()");
            
    
            if(this.pair.getToken().equals(",")) {
				
                String retval = match(",");
                if(!retval.equals("Syntax Error."))
                {
                    print(retval);
                }
                
                

                INT();

                OEN();
            }
            
            else{
                ;
            }

            
        }
    }

    private void EL(){
        if(!this.error){
            print("EL()");
            
            if(this.pair.getToken().equals("ES")) {
				
                String retval = match("ES");
                if(!retval.equals("Syntax Error."))
                {
                    print(retval);
                }
                
                
            }

            E();

            if(this.pair.getToken().equals("EE")) {
				
                String retval = match("EE");
                if(!retval.equals("Syntax Error."))
                {
                    print(retval);
                }
                
                
            }

            
        }

    }

    private void E(){
        if(!this.error){
            print("E()");
            

            ED();

            OEE();

            
        }
    }

    private void ED(){
        if(!this.error){
            print("ED()");
            

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

                
            }
            else {
				this.error = true;
				System.out.println("Syntax error.");
			}

            
        }
    }
	
    private void OEE(){
        if(!this.error){
            print("OEE()");
            
    
            if(this.pair.getToken().equals(",")) {
				
                String retval = match(",");
                if(!retval.equals("Syntax Error."))
                {
                    print(retval);
                }
                
                

                ED();

                OEE();
            }
            
            else{
                ;
            }

            
        }
    }
	
	private String match(String token) {
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
		output.add(text);
	}
	
	public void output() throws IOException
	{
		FileWriter fileWriter = new FileWriter("tac.txt");
	    PrintWriter printWriter = new PrintWriter(fileWriter);
		for (int i = 0; i < this.output.size(); i++)
		{
			printWriter.println(Integer.toString(i+1) + "\t" + output.get(i));
		}
		printWriter.close();
		
		System.out.println("\nThree address code for the input code file written to file \"tac.txt\".");
		
		fileWriter = new FileWriter("translator-symboltable.txt");
	    printWriter = new PrintWriter(fileWriter);
	    printWriter.println("Symbol Table");
	    printWriter.println("------------------------------------------------");
	    printWriter.println("Name" + "\t\t" + "Datatype" + "\t" + "Relative Address");
	    printWriter.println("------------------------------------------------");
		
	    int temp = 0;
	    for(int i = 0; i < this.varIDs.size(); i++) {
			
			if(i > 0 && varTypes.get(i).equals("INT")) {
				temp += 4;
			}
			else if(i > 0 && varTypes.get(i).equals("CHAR")) {
				temp ++;
			}
			printWriter.println(varIDs.get(i) + "\t\t" + varTypes.get(i) + "\t\t" + Integer.toString(temp));
		}
	    printWriter.close();
	    
	    System.out.println("Symbol-table for the input code file written to file \"translator-symboltable.txt\".");
	}
}

