package cmput301.ajford.expense_tracker.activities;

import cmput301.ajford.expense_tracker.R;
import cmput301.ajford.expense_tracker.R.id;
import cmput301.ajford.expense_tracker.R.layout;
import cmput301.ajford.expense_tracker.R.menu;
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

// Source: http://developer.android.com/guide/topics/ui/dialogs.html (2015-02-01)
public class NewExpenseItemDialogFragment extends DialogFragment {

	/**
	 * The ExpenseItem this fragment is presenting.
	 */
	private ExpenseItem expenseItem;

    @Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
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
		
		return builder.create();
	}
	
	public void saveExpenseItem(MenuItem menuItem) {
		
	}
}
