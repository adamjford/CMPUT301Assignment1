/**
 * 
 */
package cmput301.ajford_notes.models;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Adam
 *
 */
public class TravelClaim {
	public TravelClaim() {
		expenseItems = new ArrayList<ExpenseItem>();
	}
	
	private ArrayList<ExpenseItem> expenseItems;
	private Date startDate;
	private Date endDate;
	private String description;

	public ArrayList<ExpenseItem> getExpenseItems() {
		return expenseItems;
	}
	
	public void AddExpenseItem(ExpenseItem item) {
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
}


