package library;

import java.util.ArrayList;
import java.util.List;

import exceptions.NoBookTypeException;
import gui.SignIn;
import books.Book;
import books.BookFactory;
import books.State;


// main class adds some data to the library then runs the application
public class Main {

	public static void main(String[] args) {
		LibrarySingleton l = LibrarySingleton.getInstance();
		
		//adding some data
		List<Book> list = new ArrayList<Book>();
		try {
			list.add(BookFactory.createBook("Drama", "Beyond", "John Doe", new State(true)));
		} catch (NoBookTypeException e) {
			e.printStackTrace();
		}
		
		
		List<Book> listInLibrary = new ArrayList<Book>();
		try {
			listInLibrary.add(BookFactory.createBook("SF", "Inception", "John Aldrige", new State(false)));
		} catch (NoBookTypeException e) {
			e.printStackTrace();
		}
		
		l.getLibraryMap().put(l.getLibraryClient(), listInLibrary);
		l.getLibraryMap().put(new Client("Bogdan", "Ionescu", "0751103535"), list);
		
		// starting application
		new SignIn(l);
		
		
		
		
	}
	
}
