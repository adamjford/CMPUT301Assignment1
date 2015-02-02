package cmput301.ajford.expense_tracker.models;

import java.util.ArrayList;
import java.util.Date;
import org.joda.money.Money;

public class ExpenseItem {
	private transient static final ArrayList<String> validCategories = new ArrayList<String>() {{
		add("Air Fare");
		add("Ground Transport");
		add("Vehicle Rental");
		add("Fuel");
		add("Parking");
		add("Registration");
		add("Accomodiation");
		add("Meal");
	}};
	
	private transient static final ArrayList<String> validCurrencies = new ArrayList<String>() {{
		add("CAD");
		add("USD");
		add("EUR");
		add("GBP");
	}};
	
	public static ArrayList<String> getValidCategories() {
		return validCategories;
	}

	public static ArrayList<String> getValidCurrencies() {
		return validCurrencies;
	}
	
	private Date date;
	private String category;
	private String description;
	private Money amountSpent;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Money getAmountSpent() {
		return amountSpent;
	}

	public void setAmountSpent(Money amountSpent) {
		this.amountSpent = amountSpent;
	}
}
