package cmput301.ajford.expense_tracker.activities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cmput301.ajford.expense_tracker.R;
import cmput301.ajford.expense_tracker.R.id;
import cmput301.ajford.expense_tracker.R.layout;
import cmput301.ajford.expense_tracker.TravelClaimsController;
import cmput301.ajford.expense_tracker.activities.NewExpenseItemDialogFragment.NewExpenseItemCreatedListener;
import cmput301.ajford.expense_tracker.fragments.DatePickerFragment;
import cmput301.ajford.expense_tracker.fragments.TravelClaimDetailFragment;
import cmput301.ajford.expense_tracker.models.ExpenseItem;
import cmput301.ajford.expense_tracker.models.TravelClaim;
import cmput301.ajford.expense_tracker.models.TravelClaimsListManager;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DialogFragment;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

/**
 * An activity representing a new Travel Claim being created. This activity
 * is only used on handset devices. On tablet-size devices, item details are
 * presented side-by-side with a list of items in a
 * {@link TravelClaimListActivity}.
 * <p>
 * This activity is mostly just a 'shell' activity containing nothing more than
 * a {@link TravelClaimDetailFragment}.
 */
public class NewTravelClaimActivity extends Activity implements NewExpenseItemCreatedListener {
	private TravelClaim travelClaim;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		travelClaim = new TravelClaim();
		
		setContentView(R.layout.activity_travelclaim_new);
		TravelClaimsListManager.initializeManager(getApplicationContext());

		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == android.R.id.home) {
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			navigateUpTo(new Intent(this, TravelClaimListActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.new_travel_claim, menu);
		return true;
	}
	
	// Source: 	http://developer.android.com/guide/topics/ui/controls/pickers.html (2015-02-01)
	// License: http://creativecommons.org/licenses/by/2.5/
	public void showDatePickerDialog(View v) {
		EditText dateField = (EditText) v;
		
		DialogFragment newFragment = new DatePickerFragment(dateField);
		newFragment.show(getFragmentManager(), "datePicker");
	}
	
	public void addNewExpenseItem(View view) {
		NewExpenseItemDialogFragment dialog = new NewExpenseItemDialogFragment();
		dialog.show(getFragmentManager(), "NewExpenseItemDialogFragment");
	}
	
	public void saveTravelClaim(MenuItem menuItem) {
		Date startDate = getStartDateValue();
		Date endDate = getEndDateValue();
		String description = ((EditText) findViewById(R.id.travel_claim_description)).getEditableText().toString();
		
		if(startDate == null || endDate == null || description == null || description == "") {
			Toast.makeText(this, "Invalid values.", Toast.LENGTH_SHORT).show();
		}
		else {
			travelClaim.setStartDate(startDate);
			travelClaim.setEndDate(endDate);
			travelClaim.setDescription(description);
			
			TravelClaimsController.getTravelClaimsList().add(travelClaim);
			TravelClaimsController.saveStudentList();

			navigateUpTo(new Intent(this, TravelClaimListActivity.class));
		}
	}
	
	private Date getStartDateValue() {
		return getDateValue((EditText) findViewById(R.id.startDate));
	}

	private Date getEndDateValue() {
		return getDateValue((EditText) findViewById(R.id.endDate));
	}
	
	private Date getDateValue(EditText dateField) {
		final Calendar c = Calendar.getInstance();
		Date date;

        SimpleDateFormat format = new SimpleDateFormat(DatePickerFragment.dateFormat);
        
        try {
        	c.setTime(format.parse(dateField.getEditableText().toString()));
        	date = c.getTime();
        } catch (ParseException e) {
        	date = null;
        }
        
        return date;
	}

	@Override
	public void onNewExpenseItemCreated(ExpenseItem expenseItem) {
		travelClaim.addExpenseItem(expenseItem);
	}
}
