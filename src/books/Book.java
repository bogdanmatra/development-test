package books;

import java.io.Serializable;

public interface Book extends Serializable {

	public String getTitle();

	public String getAuthor();

	public State getState();
	
	public String getGenre();

}
