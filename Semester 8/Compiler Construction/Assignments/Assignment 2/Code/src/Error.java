
public class Error {
	private int line;
	private String text;

	public Error(int line, String text) {
		super();
		this.line = line;
		this.text = text;
	}
	
	@Override
    public String toString() {
        return Integer.toString(line) + "\t" + text;
    }
    
    @Override
    public boolean equals(Object obj) {
        return (this.line == ((Error) obj).line)
                && this.text.equals(((Error) obj).text);
    }
}
