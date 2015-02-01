/**
 * 
 */
package cmput301.ajford.expense_tracker.models;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import cmput301.ajford.expense_tracker.framework.*;

/**
 * @author Adam
 *
 * Source: https://www.youtube.com/watch?v=gmNfc6u1qk0 (2015-01-31)
 */
public class ExpenseTrackerRepository extends FModel<FView> {
	private static final String preferencesFileName = "travelClaims";
	private static final String preferenceKey = "travelClaims";
	
	Context context;
	Gson gson;
	
	public ExpenseTrackerRepository(Context context) {
		this.context = context;
		this.gson = new Gson();
	}
	
	public ArrayList<TravelClaim> loadTravelClaims() {
		SharedPreferences settings = context.getSharedPreferences(preferencesFileName, Context.MODE_PRIVATE);
		String travelClaimsListData = settings.getString(preferenceKey, "");
		if(travelClaimsListData.equals("")) {
			return new ArrayList<TravelClaim>();
		} else {
			// Source: https://sites.google.com/site/gson/gson-user-guide (2015-01-31)
			Type collectionType = new TypeToken<ArrayList<TravelClaim>>(){}.getType();
			return (new Gson()).fromJson(travelClaimsListData, collectionType);
		}
	}
	
	public void saveTravelClaims(ArrayList<TravelClaim> travelClaims) {
		SharedPreferences settings = context.getSharedPreferences(preferencesFileName, Context.MODE_PRIVATE);
		Editor editor = settings.edit();
		String travelClaimsString = gson.toJson(travelClaims);
		editor.putString(preferenceKey, travelClaimsString);
		editor.commit();
	}
}
