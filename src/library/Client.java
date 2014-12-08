package library;

import java.io.Serializable;


// clients that are keys in the hashmap
@SuppressWarnings("serial")
public class Client implements Serializable {

	private String firstName;
	private String lastName;
	private String phoneNumber;

	public Client(String firstName, String lastName, String phoneNumber) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

}
