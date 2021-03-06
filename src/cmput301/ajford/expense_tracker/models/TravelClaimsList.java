/**
 * 
 */
package cmput301.ajford.expense_tracker.models;

import java.util.ArrayList;
import java.util.UUID;

import cmput301.ajford.expense_tracker.framework.*;

/**
 * @author Adam
 *
 */
public class TravelClaimsList extends FModel<FView> {
	private ArrayList<TravelClaim> travelClaims;
	
	public TravelClaimsList() {
		this(new ArrayList<TravelClaim>());
	}
	
	public TravelClaimsList(ArrayList<TravelClaim> travelClaims) {
		this.travelClaims = travelClaims;
	}
	
	public ArrayList<TravelClaim> getAll() {
		return travelClaims;
	}
	
	public TravelClaim getByID(UUID id) {
		for(TravelClaim claim : travelClaims) {
			if(claim.getID().equals(id)) return claim;
		}
		return null;
	}
	
	public void add(TravelClaim travelClaim) {
		travelClaims.add(travelClaim);
		notifyViews();
	}
	
	public void remove(TravelClaim travelClaim) {
		travelClaims.remove(travelClaim);
		notifyViews();
	}
	
	public void childUpdatePerformed() {
		notifyViews();
	}
}
