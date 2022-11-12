
public class TokenLexemePair {
	private String token;
	private String lexeme;

	public TokenLexemePair(String token, String lexeme) {
		super();
		this.token = token;
		this.lexeme = lexeme;
	}
	
    public String toString() {
        return "(" + token + ", " + lexeme + ")";
    }
    
    boolean isID()
    {
    	return token.contentEquals("ID");
    }
    
    public String getName()
    {
    	return lexeme;
    }
    
    public String getLexeme()
    {
    	return lexeme;
    }
    
    public String getToken()
    {
    	return token;
    }
}
