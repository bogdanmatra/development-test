package books;

public class PoetryBook implements Book{
	
	private DefaultBook dBook;
	private static final String genre = "Poetry";

	public PoetryBook(String author, String title, State s) {

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
