package cmput301.ajford.expense_tracker.activities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cmput301.ajford.expense_tracker.R;
import cmput301.ajford.expense_tracker.fragments.DatePickerFragment;

import android.app.Activity;
import android.widget.EditText;

public abstract class TravelClaimActivityBase extends Activity {
	protected String getDescription() {
		return ((EditText) findViewById(R.id.travel_claim_description)).getEditableText().toString();
	}
	
	protected Date getStartDateValue() {
		return getDateValue((EditText) findViewById(R.id.startDate));
	}

	protected Date getEndDateValue() {
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
}
