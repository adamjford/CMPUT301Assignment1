package cmput301.ajford.expense_tracker.fragments;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.*;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

// Source: 	http://developer.android.com/guide/topics/ui/controls/pickers.html (2015-02-01)
// License: http://creativecommons.org/licenses/by/2.5/
//
// Source: http://stackoverflow.com/a/14933515/14064 (2015-02-01)
public class DatePickerFragment extends DialogFragment
                            implements DatePickerDialog.OnDateSetListener {
	
	EditText dateField;
	public static final String dateFormat = "MM/dd/yy";
	
	public DatePickerFragment(EditText dateField) {
		this.dateField = dateField;
	}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    	String dateText = dateField.getText() != null ? dateField.getText().toString() : "";
        final Calendar c = Calendar.getInstance();

    	if(dateText != null && !dateText.equals("")) {
    		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
    		try {
				c.setTime(format.parse(dateText));
			} catch (ParseException e) {
				Toast.makeText(getActivity(), "Invalid date.", Toast.LENGTH_SHORT).show();
			}
    	}
    	
    	int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH); 

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        final Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        dateField.setText(format.format(c.getTime()));
    }
}
