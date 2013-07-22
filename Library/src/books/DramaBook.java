package books;

@SuppressWarnings("serial")
public class DramaBook implements Book {

	private DefaultBook dBook;
	private static final String genre = "Drama";

	public DramaBook(String author, String title, State s) {

		dBook = new DefaultBook(title, author, s);

	}
	
	@Override
	public String getGenre() {
		return genre; 
	}

	@Override
	public String getTitle() {
		return dBook.getTitle(); //delegates to defaultbook
	}

	@Override
	public String getAuthor() {
		return dBook.getAuthor(); //delegates to defaultbook
	}

	@Override
	public State getState() {
		return dBook.getState(); //delegates to defaultbook
	}

}
