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
	private transient static final String IN_PROGRESS = "In Progress";
	private transient static final String SUBMITTED = "Submitted";
	private transient static final String RETURNED = "Returned";
	private transient static final String APPROVED = "Approved";
	
	private transient static final ArrayList<String> allStatuses = new ArrayList<String>() {{
		add(IN_PROGRESS);
		add(SUBMITTED);
		add(RETURNED);
		add(APPROVED);
	}};
	
	public TravelClaim() {
		id = UUID.randomUUID();
		status = IN_PROGRESS;
		expenseItems = new ArrayList<ExpenseItem>();
	}
	
	private UUID id;
	private ArrayList<ExpenseItem> expenseItems;
	private Date startDate;
	private Date endDate;
	private String description;
	private String status;
	
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public boolean isEditable() {
		return status == IN_PROGRESS || status == RETURNED;
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


