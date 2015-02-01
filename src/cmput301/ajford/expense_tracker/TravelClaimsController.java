/**
 * 
 */
package cmput301.ajford.expense_tracker;

import cmput301.ajford.expense_tracker.models.TravelClaimsList;
import cmput301.ajford.expense_tracker.models.TravelClaimsListManager;

/**
 * @author Adam
 *
 * Source: https://www.youtube.com/watch?v=uat-8Z6U_Co (2015-02-01)
 */
public class TravelClaimsController {
	transient private static TravelClaimsList travelClaimsList;
	
	// Warning: can throw a RuntimeException is TravelClaimsListManager was not earlier initialized
	public static TravelClaimsList getTravelClaimsList() {
		if (travelClaimsList == null) {
			travelClaimsList = TravelClaimsListManager.getManager().loadTravelClaims();
		}
		return travelClaimsList;
	}
	
	public static void saveStudentList() {
        TravelClaimsListManager.getManager().saveTravelClaims(travelClaimsList);
	}
}
