package books;

import exceptions.NoBookTypeException;

public class BookFactory {
	
	static Book bookCreator(String type , String title , String author, State s) throws NoBookTypeException{
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
