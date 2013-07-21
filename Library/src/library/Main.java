package library;

import java.util.ArrayList;
import java.util.List;

import exceptions.NoBookTypeException;
import gui.SignIn;
import books.Book;
import books.BookFactory;
import books.State;

public class Main {

	public static void main(String[] args) {
		LibrarySingleton l = LibrarySingleton.getInstance();
		List<Book> list = new ArrayList<Book>();
		
		try {
			list.add(BookFactory.bookCreator("Drama", "Beyond", "John Doe", new State(false)));
		} catch (NoBookTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		l.getLibraryMap().put(new Client("Bogdan", "Ionescu", "0751103535"), list);
		

		new SignIn(l);
		
		
		
		
	}
	
}
