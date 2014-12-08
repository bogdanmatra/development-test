package books;

import exceptions.NoBookTypeException;


//creates a specific book
public class BookFactory {
	
	public static Book createBook(String type , String title , String author, State s) throws NoBookTypeException{
		if(type.equalsIgnoreCase("Poetry")) {
			return new PoetryBook(title, author, s);
		}else if (type.equalsIgnoreCase("Drama")) {
			return new DramaBook(title, author, s);
		} else if (type.equalsIgnoreCase( "Science")) {
			return new ScienceBook(title, author, s);
		} else if(type.equalsIgnoreCase("SF")) {
			return new SFBook(title, author, s);
		}else{
			throw new NoBookTypeException();	
		}
		
	}

}
