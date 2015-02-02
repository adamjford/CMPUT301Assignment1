package cmput301.ajford.expense_tracker.fragments;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.joda.money.Money;

import cmput301.ajford.expense_tracker.R;
import cmput301.ajford.expense_tracker.R.id;
import cmput301.ajford.expense_tracker.R.layout;
import cmput301.ajford.expense_tracker.R.menu;
import cmput301.ajford.expense_tracker.input_filters.MoneyValueFilter;
import cmput301.ajford.expense_tracker.models.ExpenseItem;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

// Source: http://developer.android.com/guide/topics/ui/dialogs.html (2015-02-01)
public class NewExpenseItemDialogFragment extends DialogFragment {

	public interface NewExpenseItemCreatedListener {
		public void onNewExpenseItemCreated(ExpenseItem expenseItem);
	}
	
    NewExpenseItemCreatedListener mListener;
	
	/**
	 * The ExpenseItem this fragment is presenting.
	 */
	private ExpenseItem expenseItem;
	private View dialogView;

    @Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
    	expenseItem = new ExpenseItem();
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		dialogView = View.inflate(getActivity(), R.layout.fragment_new_expense_item, null);
		builder
			.setView(dialogView)
			.setPositiveButton(R.string.title_save, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					saveExpenseItem();
				}
			})
			.setNegativeButton(R.string.title_cancel, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});
		
		Spinner categorySpinner = (Spinner) dialogView.findViewById(R.id.categorySpinner);
		SpinnerAdapter categoryAdapter = new ArrayAdapter<String>(
				getActivity(), 
				android.R.layout.simple_list_item_activated_1,
				android.R.id.text1, 
				ExpenseItem.getValidCategories());
		
		categorySpinner.setAdapter(categoryAdapter);

		Spinner currencySpinner = (Spinner) dialogView.findViewById(R.id.currency_spinner);
		SpinnerAdapter currencyAdapter = new ArrayAdapter<String>(
				getActivity(), 
				android.R.layout.simple_list_item_activated_1,
				android.R.id.text1, 
				ExpenseItem.getValidCurrencies());
		
		currencySpinner.setAdapter(currencyAdapter);
		
		EditText currencyField = (EditText) dialogView.findViewById(R.id.amount_spent);
		currencyField.setKeyListener(new MoneyValueFilter());
		
		return builder.create();
	}

	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (NewExpenseItemCreatedListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NewExpenseItemCreatedListener");
        }
    }

	public void saveExpenseItem() {
		expenseItem.setDate(getDate());
		expenseItem.setCategory(getCategory());
		expenseItem.setDescription(getDescription());
		expenseItem.setAmountSpent(getAmountSpent());
		
		mListener.onNewExpenseItemCreated(expenseItem);
	}
	
	private Date getDate() {
		EditText dateField = (EditText) dialogView.findViewById(R.id.date);
		
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
	
	private String getCategory() {
		return ((Spinner) dialogView.findViewById(R.id.categorySpinner)).getSelectedItem().toString();
	}
	
	private String getDescription() {
		return ((EditText) dialogView.findViewById(R.id.travel_claim_description)).getEditableText().toString();
	}
	
	private Money getAmountSpent() {
		String amountSpentString = ((EditText) dialogView.findViewById(R.id.amount_spent)).getEditableText().toString();
		String currencyType = ((Spinner) dialogView.findViewById(R.id.currency_spinner)).getSelectedItem().toString();
		
		return Money.parse(currencyType + " " + amountSpentString);
	}
}
