package books;

import java.util.Calendar;

public class State {

	boolean rented;
	Calendar startDate;
	Calendar returnDate;

	public State(boolean rented, Calendar startDate, Calendar returnDate) {

		this.rented = rented;
		if (rented == true) {
			this.startDate = startDate;
			this.returnDate = returnDate;
		} else {
			this.startDate = null;
			this.returnDate = null;

		}

	}

}
