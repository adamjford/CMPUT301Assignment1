package cmput301.ajford.expense_tracker.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import cmput301.ajford.expense_tracker.R;
import cmput301.ajford.expense_tracker.TravelClaimsController;
import cmput301.ajford.expense_tracker.framework.FView;
import cmput301.ajford.expense_tracker.models.TravelClaim;
import cmput301.ajford.expense_tracker.models.TravelClaimsList;
import cmput301.ajford.expense_tracker.models.TravelClaimsListManager;

import com.google.gson.Gson;

/**
 * An activity representing a list of Travel Claims. This activity has different
 * presentations for handset and tablet-size devices. On handsets, the activity
 * presents a list of items, which when touched, lead to a
 * {@link TravelClaimDetailActivity} representing item details. On tablets, the
 * activity presents the list of items and item details side-by-side using two
 * vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link TravelClaimListFragment} and the item details (if present) is a
 * {@link TravelClaimDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link TravelClaimListFragment.Callbacks} interface to listen for item
 * selections.
 */
public class TravelClaimListActivity 
				extends Activity 
				implements FView<TravelClaimsList> {

	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private boolean mTwoPane;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_travelclaim_list);
		
		ListView listView = (ListView) findViewById(R.id.travel_claims_list);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> myAdapter, View myView, int position, long id) {
				ArrayList<TravelClaim> travelClaims = TravelClaimsController.getTravelClaimsList().getAll();
				TravelClaim claim = travelClaims.get(position);
                Gson gson = new Gson();
                Intent detailIntent = new Intent(TravelClaimListActivity.this, TravelClaimDetailActivity.class);

                detailIntent.putExtra(TravelClaimDetailActivity.TravelClaimArgumentID, gson.toJson(claim, TravelClaim.class));
                startActivity(detailIntent);
            }
		});
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		TravelClaimsListManager.initializeManager(getApplicationContext());
		
		ListView listView = (ListView) findViewById(R.id.travel_claims_list);
		ArrayAdapter<TravelClaim> adapter = new ArrayAdapter<TravelClaim>(this,
				android.R.layout.simple_list_item_activated_1,
				android.R.id.text1, TravelClaimsController.getTravelClaimsList().getAll());
		listView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	// source: https://www.youtube.com/watch?v=fxjIA4HIruU (2015-02-01)
	public void newTravelClaim(MenuItem menuItem) {
		Intent intent = new Intent(TravelClaimListActivity.this, NewTravelClaimActivity.class);
		startActivity(intent);
	}

	@Override
	public void update(TravelClaimsList Model) {
		// TODO Auto-generated method stub
		
	}
}
