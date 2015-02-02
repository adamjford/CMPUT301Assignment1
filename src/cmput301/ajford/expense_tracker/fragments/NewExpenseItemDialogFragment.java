package cmput301.ajford.expense_tracker.fragments;

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

    @Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
    	ExpenseItem expenseItem = new ExpenseItem();
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		View view = View.inflate(getActivity(), R.layout.fragment_new_expense_item, null);
		builder
			.setView(view)
			.setPositiveButton(R.string.title_save, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			})
			.setNegativeButton(R.string.title_cancel, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});
		
		Spinner categorySpinner = (Spinner) view.findViewById(R.id.categorySpinner);
		SpinnerAdapter categoryAdapter = new ArrayAdapter<String>(
				getActivity(), 
				android.R.layout.simple_list_item_activated_1,
				android.R.id.text1, 
				ExpenseItem.getValidCategories());
		
		categorySpinner.setAdapter(categoryAdapter);

		Spinner currencySpinner = (Spinner) view.findViewById(R.id.currency_spinner);
		SpinnerAdapter currencyAdapter = new ArrayAdapter<String>(
				getActivity(), 
				android.R.layout.simple_list_item_activated_1,
				android.R.id.text1, 
				ExpenseItem.getValidCurrencies());
		
		currencySpinner.setAdapter(currencyAdapter);
		
		EditText currencyField = (EditText) view.findViewById(R.id.amount_spent);
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
	public void saveExpenseItem(MenuItem menuItem) {
		
	}
}
