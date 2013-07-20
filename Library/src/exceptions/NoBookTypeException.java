package exceptions;

@SuppressWarnings("serial")
public class NoBookTypeException extends Exception {

	public NoBookTypeException() {
		super("The book type does not exist!");
	}

}
