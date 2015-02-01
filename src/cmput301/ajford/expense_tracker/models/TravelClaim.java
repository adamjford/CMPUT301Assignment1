/**
 * 
 */
package cmput301.ajford.expense_tracker.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * @author Adam
 *
 */
public class TravelClaim {
	public TravelClaim() {
		expenseItems = new ArrayList<ExpenseItem>();
		status = Status.IN_PROGRESS;
	}
	
	public enum Status {
		IN_PROGRESS,
		SUBMITTED,
		RETURNED,
		APPROVED
	}
	
	private ArrayList<ExpenseItem> expenseItems;
	private Date startDate;
	private Date endDate;
	private String description;
	private Status status;

	public Collection<ExpenseItem> getExpenseItems() {
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
	
	public boolean IsEditable() {
		return status == Status.IN_PROGRESS || status == Status.RETURNED;
	}
}


