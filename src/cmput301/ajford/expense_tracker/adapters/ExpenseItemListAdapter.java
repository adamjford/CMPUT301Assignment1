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
import android.content.Context;
import android.view.*;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * @author Adam
 *
 * Source: http://stackoverflow.com/a/8166802/14064 (2015-02-02)
 */
public class ExpenseItemListAdapter extends ArrayAdapter<ExpenseItem> {
	private static final int itemLayout = R.layout.expense_list_item;
	
	public ExpenseItemListAdapter(Context context, ArrayList<ExpenseItem> expenseItem) {
		super(context, itemLayout, expenseItem);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;

		if (v == null) {
			v = LayoutInflater.from(getContext()).inflate(itemLayout, parent, false);
		}
		
		ExpenseItem item = getItem(position);
		if (item != null) {
			TextView description 		= (TextView) v.findViewById(R.id.description_expense_list_item);
			TextView category 			= (TextView) v.findViewById(R.id.category_expense_list_item);
			TextView date 				= (TextView) v.findViewById(R.id.date_expense_list_item);
			TextView amountSpent 		= (TextView) v.findViewById(R.id.amount_spent_list_expense_item);
			
			description.setText(item.getDescription());
			category.setText(item.getCategory());
			setDateValue(item.getDate(), date);
			amountSpent.setText(item.getAmountSpent().toString());
		}
		
		return v;
	}

	private void setDateValue(Date date, TextView dateField) {
        SimpleDateFormat format = new SimpleDateFormat(DatePickerFragment.dateFormat);
        
        dateField.setText(format.format(date));
	}
}
