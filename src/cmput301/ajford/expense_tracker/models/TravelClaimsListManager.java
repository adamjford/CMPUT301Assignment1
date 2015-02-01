/**
 * 
 */
package cmput301.ajford.expense_tracker.models;

import com.google.gson.Gson;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * @author Adam
 *
 * Source: 	https://www.youtube.com/watch?v=gmNfc6u1qk0 (2015-01-31)
 * 			https://www.youtube.com/watch?v=uat-8Z6U_Co (2015-02-01)
 */
public class TravelClaimsListManager {
	private static final String preferencesFileName = "travelClaims";
	private static final String preferenceKey = "travelClaims";
	private static TravelClaimsListManager singleton = null;
	
	Context context;
	Gson gson;
	
	public static void initializeManager(Context context) {
		if (singleton == null) {
			if (context == null) {
				throw new RuntimeException("Missing Context for TravelClaimsListManager.");
			}

			singleton = new TravelClaimsListManager(context);
		}
	}
	
	public static TravelClaimsListManager getManager() {
		if (singleton == null) {
			throw new RuntimeException("TravelClaimsListManager was not initialized.");
		}
		return singleton;
	}
	
	private TravelClaimsListManager(Context context) {
		this.context = context;
		this.gson = new Gson();
	}
	
	public TravelClaimsList loadTravelClaims() {
		SharedPreferences settings = context.getSharedPreferences(preferencesFileName, Context.MODE_PRIVATE);
		String travelClaimsListData = settings.getString(preferenceKey, "");
		if (travelClaimsListData.equals("")) {
			return new TravelClaimsList();
		} else {
			return (new Gson()).fromJson(travelClaimsListData, TravelClaimsList.class);
		}
	}
	
	public void saveTravelClaims(TravelClaimsList travelClaims) {
		SharedPreferences settings = context.getSharedPreferences(preferencesFileName, Context.MODE_PRIVATE);
		Editor editor = settings.edit();
		String travelClaimsString = gson.toJson(travelClaims);
		editor.putString(preferenceKey, travelClaimsString);
		editor.commit();
	}
}
