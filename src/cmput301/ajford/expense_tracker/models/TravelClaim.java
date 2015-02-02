/**
 * 
 */
package cmput301.ajford.expense_tracker.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Adam
 *
 */
public class TravelClaim {
	public TravelClaim() {
		id = UUID.randomUUID();
		status = Status.IN_PROGRESS;
		expenseItems = new ArrayList<ExpenseItem>();
	}
	
	public enum Status {
		IN_PROGRESS,
		SUBMITTED,
		RETURNED,
		APPROVED
	}
	
	private UUID id;
	private ArrayList<ExpenseItem> expenseItems;
	private Date startDate;
	private Date endDate;
	private String description;
	private Status status;
	
	public UUID getID() {
		return id;
	}

	public ArrayList<ExpenseItem> getExpenseItems() {
		return expenseItems;
	}
	
	public void addExpenseItem(ExpenseItem item) {
		expenseItems.add(item);
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public boolean isEditable() {
		return status == Status.IN_PROGRESS || status == Status.RETURNED;
	}
	
	@Override
	public String toString() {
		return getDescription() + " - " + getStatus();
	}
	
	@Override
	public boolean equals(Object other) {
		return other != null 
				&& other.getClass().equals(this.getClass()) 
				&& ((TravelClaim)other).getID() == getID();
	}
}


