package library;

public class Librarian {

	private String name;
	private String user;
	private String password;

	public Librarian(String name, String user, String password) {

		this.name = name;
		this.user = user;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public String getUser() {
		return user;
	}

	protected String getPassword() {
		return password;
	}

}
