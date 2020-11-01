package troubleshootSwagger2.demoRestServiceWithSwagger2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import spring.rest.webservice.demoRestService.beans.BranchLocation;

@Component
public class BranchLocationDAOService {

private static List<BranchLocation> branchLocations = new ArrayList<>();

private static int mostRecentlyAddedPrimaryKey = 3333;

static {
	
	branchLocations.add(new BranchLocation(1111,"Northern Savings Bank - California", "20 Sunnyvale Avenue, San Deigo, California"));
	branchLocations.add(new BranchLocation(2222,"Northern Savings Bank - Florida", "10 Main Street, Orlando, Florida"));
	branchLocations.add(new BranchLocation(3333,"Northern Savings Bank - Texas", "30 Montvale Avenue, Dallas, Texas"));
}


public List<BranchLocation> getAllBranchLocations(){
	return branchLocations;
}

public BranchLocation addNewBranchLocation(BranchLocation branchLocation) {
	if(branchLocation.getBranchId() == null)
	{
		//mostRecentlyAddedPrimaryKey = mostRecentlyAddedPrimaryKey++;
		branchLocation.setBranchId(Integer.valueOf(++mostRecentlyAddedPrimaryKey));
	}
	branchLocations.add(branchLocation);
	
	return branchLocation;
}


public List<BranchLocation> findBranchByAddress(String branchAddress) {
	List<BranchLocation> filteredBranchLocations = new ArrayList<>();
	
	for(BranchLocation branchLocation:branchLocations) {
		
		if(branchLocation.getBranchAddress().contains(branchAddress)) {
			filteredBranchLocations.add(branchLocation);
		}
	}  // end of for loop

return filteredBranchLocations;
	
}


public BranchLocation deleteBranchLocationById(int id) {
	Iterator<BranchLocation> iteratorBranchLocationsArrayList = branchLocations.iterator();
	while(iteratorBranchLocationsArrayList.hasNext()) {
		BranchLocation branchLocation = iteratorBranchLocationsArrayList.next();
		if(branchLocation.getBranchId() == id) {
			iteratorBranchLocationsArrayList.remove();
			return branchLocation;
		}
	}// end of while loop

return null;

}    // end of function

}
