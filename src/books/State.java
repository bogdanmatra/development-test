package books;

import java.io.Serializable;
import java.util.Calendar;




// the class is used to state if a book is rented or not and
//if the book is rented it will have an interval between present time and 1 moth in the future for the book to be returned

@SuppressWarnings("serial")
public class State implements Serializable{

	private boolean rented;
	private Calendar startDate;
	private Calendar returnDate;

	public State(boolean b) {
		rented = b;
		if(rented==true){
		startDate=Calendar.getInstance(); // current date
		returnDate=Calendar.getInstance(); 
		returnDate.add(Calendar.MONTH,1); // 1 month in the future
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
