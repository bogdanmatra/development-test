package library;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import books.Book;

public class LibrarySingleton {
	
	
	LibrarySingleton theLibrary;
	Map<Client,List<Book>> libraryMap=new HashMap<Client,List<Book>>();
	List<Librarian> librarians=new ArrayList<Librarian>();
	
	private LibrarySingleton() {
		
	}
	
	
	public LibrarySingleton getInstance() {
		if(theLibrary==null){
			return new LibrarySingleton();
		}
		else return theLibrary;
	}

}
