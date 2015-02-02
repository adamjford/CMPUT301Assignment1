package cmput301.ajford.expense_tracker.fragments;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.os.Bundle;
import android.app.DialogFragment;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cmput301.ajford.expense_tracker.R;
import cmput301.ajford.expense_tracker.R.id;
import cmput301.ajford.expense_tracker.R.layout;
import cmput301.ajford.expense_tracker.activities.NewTravelClaimActivity;
import cmput301.ajford.expense_tracker.activities.TravelClaimListActivity;
import cmput301.ajford.expense_tracker.dummy.DummyContent;
import cmput301.ajford.expense_tracker.models.TravelClaim;

/**
 * A fragment representing a new Travel Claim being created. This fragment is
 * either contained in a {@link TravelClaimListActivity} in two-pane mode (on
 * tablets) or a {@link NewTravelClaimActivity} on handsets.
 */
public class NewTravelClaimFragment extends Fragment {
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	/**
	 * The TravelClaim this fragment is presenting.
	 */
	private TravelClaim travelClaim;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public NewTravelClaimFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		travelClaim = new TravelClaim();
		
/*		EditText startDateField = (EditText) getActivity().findViewById(R.id.startDate);
		EditText endDateField = (EditText) getActivity().findViewById(R.id.endDate);
		
		startDateField.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(!hasFocus) {
					EditText field = (EditText) v;
					
                    final Calendar c = Calendar.getInstance();
					SimpleDateFormat format = new SimpleDateFormat(DatePickerFragment.dateFormat);
                    try {
                    	c.setTime(format.parse(field.getEditableText().toString()));
                    } catch (ParseException e) {
                                Toast.makeText(getActivity(), "Invalid date.", Toast.LENGTH_SHORT).show();
                    }
				}
			}
		});*/
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_travelclaim_new,
				container, false);

		return rootView;
	}
}
