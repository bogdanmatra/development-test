package library;

import java.util.ArrayList;
import java.util.List;
import exceptions.NoBookTypeException;
import books.Book;
import books.BookFactory;
import books.State;

public class Main {

	public static void main(String[] args) {
		LibrarySingleton l = LibrarySingleton.getInstance();
		l.printLibrarioans();
		List<Book> list = new ArrayList<Book>();
		
		
		l.serializeMapToFile();
		
		try {
			list.add(BookFactory.bookCreator("Drama", "one", "two", new State(false)));
		} catch (NoBookTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		l.getLibraryMap().put(new Client("ion", "ionescu", "georgescu"), list);
		
		
		l.deserializeMapToFile();

		
		
		
	}
	
}
