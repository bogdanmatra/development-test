package library;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import books.Book;

public class LibrarySingleton {

	

	private static LibrarySingleton theLibrary;
	private Map<Client, List<Book>> libraryMap = new HashMap<Client, List<Book>>();
	private List<Librarian> librarians = new ArrayList<Librarian>();

	private LibrarySingleton() {
		
		Client booksInLibray=new Client("Library", "Ion Creanga", "0761103535");
		Client books2=new Client("Library", "Ion Creanga", "0761103535");
		Client books3=new Client("Library", "Ion Creanga", "0761103535");
		
		libraryMap.put(booksInLibray, null);
		libraryMap.put(books2, null);
		libraryMap.put(books3, null);
		
		BufferedReader input = null;
		try {
			input = new BufferedReader(new FileReader("bin/library/librarians.txt"));
			String line;
			String[] data;

			while ((line = input.readLine()) != null) {
				data = line.split(" ");
				librarians.add(new Librarian(data[0]+" "+data[1], data[2], data[3]));
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (input != null)
					input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	public void printLibrarioans() {
		for (Librarian l: librarians ){
			System.out.println(l.getName());
		}

	}

	public static LibrarySingleton getInstance() {
		if (theLibrary == null) {
			theLibrary=new LibrarySingleton();
			return theLibrary;
		} else
			return theLibrary;
	}
	
	public Map<Client, List<Book>> getLibraryMap() {
		return libraryMap;
	}

}
