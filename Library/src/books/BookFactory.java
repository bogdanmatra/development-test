package books;

import exceptions.NoBookTypeException;


//creates a specific book
public class BookFactory {
	
	public static Book createBook(String type , String title , String author, State s) throws NoBookTypeException{
		switch(type){
		case "Poetry":
			return new PoetryBook(title, author, s);
		case "Drama":
			return new DramaBook(title, author, s);
		case "Science":
			return new ScienceBook(title, author, s);
		case "SF":
			return new SFBook(title, author, s);
		default:
			throw new NoBookTypeException();	
		}
		
	}

}
