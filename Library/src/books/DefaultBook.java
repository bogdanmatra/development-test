package books;


//  the default book that is used as a field for specific books

@SuppressWarnings("serial")
public class DefaultBook implements Book {

	private String title;
	private String author;
	private State currentState;

	public DefaultBook(String title, String author, State currentState) {

		this.title = title;
		this.author = author;
		this.currentState = currentState;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public String getAuthor() {
		return author;
	}

	@Override
	public State getState() {
		return currentState;
	}

	@Override
	public String getGenre() {
		return null;
	}



}
