package library;

import java.io.BufferedReader;
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

public class LibrarySingleton {

	private static LibrarySingleton theLibrary;
	private Map<Client, List<Book>> libraryMap = new HashMap<Client, List<Book>>();
	private List<Librarian> librarians = new ArrayList<Librarian>();

	private LibrarySingleton() {

		Client booksInLibray = new Client("Library", "Ion Creanga", "0761103535");
		libraryMap.put(booksInLibray, null);

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

	public Map<Client, List<Book>> getLibraryMap() {
		return libraryMap;
	}
	
	
	public Librarian credentialsCheck(String user,String pass) {

		for(Librarian librarian: librarians){
			if(librarian.getUser().equals(user) && librarian.getPassword().equals(pass)) return librarian;
		}
		return null;
	}

	public void serializeMapToFile() {

		ObjectOutputStream output = null;
		try {
			FileOutputStream file = new FileOutputStream("savedMap.ser");
			output = new ObjectOutputStream(file);
			output.writeObject(libraryMap);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (output != null)
					output.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@SuppressWarnings("unchecked")
	public void deserializeMapToFile() {
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream("savedMap.ser"));
			try {
				libraryMap = (Map<Client, List<Book>>) ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (ois != null)
				try {
					ois.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		}

	}

}
