package books;

public class SFBook implements Book {

	private DefaultBook dBook;
	private static final String genre = "Science-Fiction";

	public SFBook(String author, String title, State s) {

		dBook = new DefaultBook(title, author, s);

	}

	@Override
	public String getGenre() {
		return genre;
	}

	@Override
	public String getTitle() {
		return dBook.getTitle();
	}

	@Override
	public String getAuthor() {
		return dBook.getAuthor();
	}

	@Override
	public State getState() {
		return dBook.getState();
	}

}
