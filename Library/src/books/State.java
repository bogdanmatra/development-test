package books;

import java.io.Serializable;
import java.util.Calendar;

@SuppressWarnings("serial")
public class State implements Serializable{

	private boolean rented;
	private Calendar startDate;
	private Calendar returnDate;

	public State(boolean b) {
		rented = b;
		if(rented==true){
		startDate=Calendar.getInstance();
		returnDate=Calendar.getInstance();
		returnDate.add(Calendar.MONTH,1);
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
