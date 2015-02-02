package cmput301.ajford.expense_tracker.activities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.UUID;

import com.google.gson.Gson;

import cmput301.ajford.expense_tracker.R;
import cmput301.ajford.expense_tracker.TravelClaimsController;
import cmput301.ajford.expense_tracker.R.id;
import cmput301.ajford.expense_tracker.R.layout;
import cmput301.ajford.expense_tracker.adapters.ExpenseItemListAdapter;
import cmput301.ajford.expense_tracker.fragments.DatePickerFragment;
import cmput301.ajford.expense_tracker.fragments.NewExpenseItemDialogFragment;
import cmput301.ajford.expense_tracker.fragments.NewExpenseItemDialogFragment.ExpenseItemSavedListener;
import cmput301.ajford.expense_tracker.framework.FView;
import cmput301.ajford.expense_tracker.models.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;

import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

/**
 * An activity representing a single Travel Claim detail screen. This activity
 * is only used on handset devices. On tablet-size devices, item details are
 * presented side-by-side with a list of items in a
 * {@link TravelClaimListActivity}.
 * <p>
 * This activity is mostly just a 'shell' activity containing nothing more than
 * a {@link TravelClaimDetailFragment}.
 */
public class TravelClaimDetailActivity extends TravelClaimActivityBase
		implements ExpenseItemSavedListener {

	public static final String TravelClaimArgumentID = "TravelClaim";

	private TravelClaim travelClaim = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TravelClaimsListManager.initializeManager(getApplicationContext());

		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	protected void onStart() {
		super.onStart();
		Intent intent = getIntent();
		UUID id = (UUID) intent.getExtras().getSerializable(
				TravelClaimArgumentID);
		travelClaim = TravelClaimsController.getTravelClaimByID(id);

		if (travelClaim.isEditable()) {
			initializeEditMode();
		} else {
			initializeViewMode();
		}
		

		final ListView expenseItemList = (ListView) findViewById(R.id.expense_items_list);
		setExpenseItemAdapter(expenseItemList);

		if (travelClaim.isEditable()) {
			expenseItemList
				.setOnItemLongClickListener(new OnItemLongClickListener() {
					@Override
					public boolean onItemLongClick(AdapterView<?> parent,
							View view, int position, long id) {

						final int index = position;
						// Source: http://stackoverflow.com/a/2115770/14064 (2014-02-02)
						(new AlertDialog.Builder(TravelClaimDetailActivity.this))
								.setTitle("Delete entry")
								.setMessage("Are you sure you want to delete this entry?")
								.setPositiveButton(android.R.string.yes,
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog, int which) {
												ArrayList<ExpenseItem> expenseItems = TravelClaimDetailActivity.this.travelClaim.getExpenseItems();
												expenseItems.remove(expenseItems.get(index));
												TravelClaimDetailActivity.this.setExpenseItemAdapter(expenseItemList);
											}
										})
								.setNegativeButton(android.R.string.no,
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog, int which) {
												// do nothing
											}
										})
								.setIcon(android.R.drawable.ic_dialog_alert)
								.show();

						return true;
					}
				});
		}
	}

	@Override
	protected void onPause() {
		super.onPause();

		if (travelClaim.isEditable()) {
			travelClaim.setDescription(getDescription());
            travelClaim.setStartDate(getStartDateValue());
            travelClaim.setEndDate(getEndDateValue());
		}

		String status = getStatus();
		travelClaim.setStatus(status);
		TravelClaimsController.updatePerformed();
		TravelClaimsController.persistStudentList();
	}

	// Source:
	// http://developer.android.com/guide/topics/ui/controls/pickers.html
	// (2015-02-01)
	// License: http://creativecommons.org/licenses/by/2.5/
	public void showDatePickerDialog(View v) {
		EditText dateField = (EditText) v;

		DialogFragment newFragment = new DatePickerFragment(dateField);
		newFragment.show(getFragmentManager(), "datePicker");
	}

	public void initializeEditMode() {
		setContentView(R.layout.activity_travelclaim_edit);

		setStatusAdapter();
		setStatusEdit();
		setDescriptionEdit();
		setStartDateEdit();
		setEndDateEdit();
	}
	
	public void initializeViewMode() {
		setContentView(R.layout.activity_travelclaim_view);

		setStatusAdapter();
		setStatusEdit();
		setDescriptionView();
		setDateRangeView();
	}

	private void setStatusEdit() {
		Spinner s = (Spinner) findViewById(R.id.status_edit);
		int position = TravelClaim.getValidStatuses().indexOf(travelClaim.getStatus());
		s.setSelection(position);
	}
	
	private void setText(String text, int id) {
		TextView textView = (TextView) findViewById(id);
		textView.setText(text);
	}

	private void setDescriptionView() {
		setText(travelClaim.getDescription(), R.id.description_view);
	}
	
	private void setDateRangeView() {
		SimpleDateFormat format = new SimpleDateFormat(
				DatePickerFragment.dateFormat);
		
		String value = format.format(travelClaim.getStartDate()) 
						+ " - " 
						+ format.format(travelClaim.getEndDate());

		setText(value, R.id.date_range_view);
	}

	private void setDescriptionEdit() {
		EditText descriptionField = (EditText) findViewById(R.id.travel_claim_description);
		descriptionField.setText(travelClaim.getDescription());
	}

	private void setStartDateEdit() {
		setDateValue(travelClaim.getStartDate(),
				(EditText) findViewById(R.id.startDate));
	}

	private void setEndDateEdit() {
		setDateValue(travelClaim.getEndDate(),
				(EditText) findViewById(R.id.endDate));
	}

	private void setDateValue(Date date, EditText dateField) {
		SimpleDateFormat format = new SimpleDateFormat(
				DatePickerFragment.dateFormat);

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

	public void addNewExpenseItem(View view) {
		NewExpenseItemDialogFragment dialog = new NewExpenseItemDialogFragment();
		dialog.show(getFragmentManager(), "NewExpenseItemDialogFragment");
	}

	@Override
	public void onExpenseItemSaved(ExpenseItem expenseItem) {
		travelClaim.addExpenseItem(expenseItem);
		setExpenseItemAdapter((ListView) findViewById(R.id.expense_items_list));
	}

	private void setExpenseItemAdapter(ListView expenseItemList) {
		ArrayList<ExpenseItem> expenseItems = travelClaim.getExpenseItems();
		
		// Source: http://stackoverflow.com/a/18441978/14064 (2015-02-02)
		Collections.sort(expenseItems, new Comparator<ExpenseItem>() {
			@Override
			public int compare(ExpenseItem item1, ExpenseItem item2)
			{
				return item1.getDate().compareTo(item2.getDate());
			}
		});
		
		ExpenseItemListAdapter adapter = new ExpenseItemListAdapter(this, expenseItems);
		expenseItemList.setAdapter(adapter);
	}
	
	private void setStatusAdapter() {
		SpinnerAdapter adapter = new ArrayAdapter<String>(
				this, 
				android.R.layout.simple_list_item_activated_1,
				android.R.id.text1, 
				TravelClaim.getValidStatuses());
		Spinner s = (Spinner) findViewById(R.id.status_edit);
		s.setAdapter(adapter);
	}
}
