package books;

import java.util.Calendar;

public class State {

	private boolean rented;
	private Calendar startDate;
	private Calendar returnDate;

	public State(boolean b) {
		rented = b;
	}

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

	public boolean isRented() {
		return rented;
	}

	public Calendar getStartDate() {
		return startDate;
	}

	public Calendar getReturnDate() {
		return returnDate;
	}

}
