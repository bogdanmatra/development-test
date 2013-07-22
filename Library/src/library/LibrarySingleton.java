package library;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import books.Book;



// class that has an object that hold the library map, librarians, and the library client (the library is also a client in the map)
public class LibrarySingleton {

	private static LibrarySingleton theLibrary;
	private Map<Client, List<Book>> libraryMap = new HashMap<Client, List<Book>>();
	private List<Librarian> librarians = new ArrayList<Librarian>();
	private Client libraryClient;

	public Client getLibraryClient() {
		return libraryClient;
	}

	private LibrarySingleton() {
	    	
	    	// adding the library as a client
		Client booksInLibray = new Client("Library", "Ion Creanga", "0761103535");
		libraryClient=booksInLibray;
		libraryMap.put(booksInLibray, null);

		// loading the librarians from a file
		BufferedReader input = null;
		try {
			input = new BufferedReader(new FileReader("bin/library/librarians.txt"));
			String line;
			String[] data;

			while ((line = input.readLine()) != null) {
				data = line.split(" ");
				librarians.add(new Librarian(data[0] + " " + data[1], data[2],data[3]));
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
	
	
	// test method to see the librarians
	public void printLibrarioans() {
		for (Librarian l : librarians) {
			System.out.println(l.getName());
		}

	}
	
	public static LibrarySingleton getInstance() {
		if (theLibrary == null) {
			theLibrary = new LibrarySingleton();
			return theLibrary;
		} else
			return theLibrary;
	}
	
	
	// returns the library map
	public Map<Client, List<Book>> getLibraryMap() {
		return libraryMap;
	}
	
	//method that checks if the librarian is a valid user
	public Librarian credentialsCheck(String user,String pass) {

		for(Librarian librarian: librarians){
			if(librarian.getUser().equals(user) && librarian.getPassword().equals(pass)) return librarian;
		}
		return null;
	}
	
	
	// method serializes the map object to a specified path
	public void serializeMapToFile(File directory) {

		ObjectOutputStream output = null;
		try {
			FileOutputStream file = new FileOutputStream((directory.getPath() + "\\savedMap.ser"));
			output = new ObjectOutputStream(file);
			output.writeObject(libraryMap);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (output != null)
					output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	
	// method loads a map object from a file where it was serialized
	@SuppressWarnings("unchecked")
	public void deserializeMapToFile(File file) {
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			try {
				libraryMap = (Map<Client, List<Book>>) ois.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ois != null)
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

		}

	}

}
