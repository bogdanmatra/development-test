package exceptions;

@SuppressWarnings("serial")
public class NoBookTypeException extends Exception {
    
    	
    	// the exception thrown if an invalid type of book will be created
	public NoBookTypeException() {
		super("The book type does not exist!");
	}

}
