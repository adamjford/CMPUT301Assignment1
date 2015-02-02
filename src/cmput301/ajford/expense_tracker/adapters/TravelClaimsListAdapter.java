/**
 * 
 */
package cmput301.ajford.expense_tracker.adapters;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cmput301.ajford.expense_tracker.R;
import cmput301.ajford.expense_tracker.fragments.DatePickerFragment;
import cmput301.ajford.expense_tracker.models.ExpenseItem;
import cmput301.ajford.expense_tracker.models.TravelClaim;
import android.content.Context;
import android.view.*;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author Adam
 *
 * Source: http://stackoverflow.com/a/8166802/14064 (2015-02-02)
 */
public class TravelClaimsListAdapter extends ArrayAdapter<TravelClaim> {
	private static final int itemLayout = R.layout.travel_claim_list_item;
	
	public TravelClaimsListAdapter(Context context, ArrayList<TravelClaim> travelClaims) {
		super(context, itemLayout, travelClaims);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;

		if (v == null) {
			v = LayoutInflater.from(getContext()).inflate(itemLayout, parent, false);
		}
		
		TravelClaim item = getItem(position);
		if (item != null) {
			TextView description 		= (TextView) v.findViewById(R.id.description_view);
			TextView status 			= (TextView) v.findViewById(R.id.status_view);
			TextView dateRange 			= (TextView) v.findViewById(R.id.date_range_view);
			
			description.setText(item.getDescription());
			status.setText(item.getStatus());

			SimpleDateFormat format = new SimpleDateFormat(DatePickerFragment.dateFormat);
		
			String dateRangeValue = format.format(item.getStartDate()) 
						+ " - " 
						+ format.format(item.getEndDate());
			dateRange.setText(dateRangeValue);
		}
		
		ArrayAdapter<String> currenciesAdapter = new ArrayAdapter<String>(
					getContext(),
					android.R.layout.simple_list_item_1,
					item.getCurrencies());
		
		TextView listView = (TextView) v.findViewById(R.id.currencies_view);
		
		String currencies = "";
		for(String currency : item.getCurrencies()) {
			currencies = currencies + "\n" + currency;
		}
		
		listView.setText(currencies);
		
		return v;
	}
}
