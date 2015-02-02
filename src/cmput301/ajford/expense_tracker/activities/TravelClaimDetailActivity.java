package cmput301.ajford.expense_tracker.activities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.google.gson.Gson;

import cmput301.ajford.expense_tracker.R;
import cmput301.ajford.expense_tracker.R.id;
import cmput301.ajford.expense_tracker.R.layout;
import cmput301.ajford.expense_tracker.fragments.DatePickerFragment;
import cmput301.ajford.expense_tracker.models.TravelClaim;
import cmput301.ajford.expense_tracker.models.TravelClaimsListManager;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

import android.view.MenuItem;
import android.widget.EditText;

/**
 * An activity representing a single Travel Claim detail screen. This activity
 * is only used on handset devices. On tablet-size devices, item details are
 * presented side-by-side with a list of items in a
 * {@link TravelClaimListActivity}.
 * <p>
 * This activity is mostly just a 'shell' activity containing nothing more than
 * a {@link TravelClaimDetailFragment}.
 */
public class TravelClaimDetailActivity extends Activity {

	public static final String TravelClaimArgumentID = "TravelClaim";
	
	private TravelClaim travelClaim = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TravelClaimsListManager.initializeManager(getApplicationContext());

		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		Intent intent = getIntent();
		String travelClaimJson = intent.getExtras().getString(TravelClaimArgumentID);
		travelClaim = (new Gson()).fromJson(travelClaimJson, TravelClaim.class);
		
		if (travelClaim.isEditable()) {
			initializeEditMode();
		}
	}

	public void initializeEditMode() {
		setContentView(R.layout.activity_travelclaim_edit);
		
		setStartDateValue();
		setEndDateValue();
		setDescription();
	}
	
	private void setDescription() {
		EditText descriptionField = (EditText) findViewById(R.id.travel_claim_description);
		descriptionField.setText(travelClaim.getDescription());
	}

	private void setStartDateValue() {
		setDateValue(travelClaim.getStartDate(), (EditText) findViewById(R.id.startDate));
	}

	private void setEndDateValue() {
		setDateValue(travelClaim.getEndDate(), (EditText) findViewById(R.id.endDate));
	}
	
	private void setDateValue(Date date, EditText dateField) {
        SimpleDateFormat format = new SimpleDateFormat(DatePickerFragment.dateFormat);
        
        dateField.setText(format.format(date));
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
}
