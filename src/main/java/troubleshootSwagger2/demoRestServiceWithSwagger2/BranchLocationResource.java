package troubleshootSwagger2.demoRestServiceWithSwagger2;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import spring.rest.webservice.demoRestService.beans.BranchLocation;
import spring.rest.webservice.demoRestService.customexceptions.BranchLocationDoesNotExistException;
import spring.rest.webservice.demoRestService.customexceptions.DetailedAddressNotAvailableException;
import spring.rest.webservice.demoRestService.customexceptions.ResourceNotFoundException;
import spring.rest.webservice.demoRestService.customexceptions.UnableToRetrieveBranchLocationsException;
//import spring.rest.webservice.demoRestService.dao.BranchLocationDAOService;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import io.swagger.annotations.*;




@RestController
//@RequestMapping("/")
public class BranchLocationResource {

@Autowired
private BranchLocationDAOService daoService;	






/*@RequestMapping("/swagger")
public String greeting() {
	return "redirect:/swagger-ui.html";
}

*/


@ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success|OK - The Request was received, was understood, and got procecssed"),
        @ApiResponse(code = 401, message = "not authorized - The Request could not be authenticated; resource requires authentication but client fails provide authentication"), 
        @ApiResponse(code = 403, message = "Server understood the request, but will not fullfil it due to client related issues"),
        @ApiResponse(code = 404, message = "browser was able to communicate with server, but server could not find what was requested") })
@GetMapping("/branchLocations")
List<BranchLocation> showAllBranchLocations(){
	List<BranchLocation> listBranchLocations = daoService.getAllBranchLocations();
	// listBranchLocations.clear();   // mock situation in case db is down. 
	if(listBranchLocations.size() == 0) {
		throw new UnableToRetrieveBranchLocationsException("System not able to access branch location info");
	}
	return listBranchLocations;
}






@GetMapping("/branchLocations/{address}")
ResponseEntity<CollectionModel<EntityModel<BranchLocation>>> getBranchLocationByAddress(@PathVariable String address){
	
	List<BranchLocation> filteredList = daoService.findBranchByAddress(address);
	
	ArrayList<EntityModel<BranchLocation>> resourceList = new ArrayList<EntityModel<BranchLocation>>();
	
	if(filteredList == null || filteredList.size() == 0) {
		throw new BranchLocationDoesNotExistException(address);
	}

WebMvcLinkBuilder controllerLinkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).showAllBranchLocations());	
	
for(BranchLocation branchLocation:filteredList) {
	EntityModel<BranchLocation> resource = new EntityModel(branchLocation);
	resource.add(controllerLinkBuilder.withRel("show all-branchLocations"));
	resourceList.add(resource);
}



String thisApiUri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri().toString();
Link link = new Link(thisApiUri);

CollectionModel<EntityModel<BranchLocation>> resources = new CollectionModel<>(resourceList, link);



	return ResponseEntity.ok(resources);



}






@PostMapping("/branchLocations")
@ApiOperation(value = "Creation of a new Resource(branch location entity) at the url /branchLocation", tags = "create new branch location")
public ResponseEntity<Object> addNewBranchLocation(@Valid @RequestBody BranchLocation branchLocation) {
	BranchLocation newlyaddedBranchLocation = daoService.addNewBranchLocation(branchLocation);

	if(newlyaddedBranchLocation.getBranchAddress().length() == 0) {
		throw new DetailedAddressNotAvailableException("Street address of branch location temporarily unavialable in system");
	}
	
URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{address}").buildAndExpand(newlyaddedBranchLocation.getBranchAddress()).toUri();
// line above references the CREATED location. 

return ResponseEntity.created(uri).build();
}


@DeleteMapping("/branchLocations/{id}")
public void deleteBranchLocationById(
		@ApiParam(
			    name =  "branch id",
			    type = "int",
			    value = "id of a branch location currently in the system",
			    example = "1",
			    required = true) @PathVariable int id) {
	BranchLocation deletedBranchLocation = daoService.deleteBranchLocationById(id); 
	if(deletedBranchLocation == null) {
		throw new ResourceNotFoundException("Was not able to find branch location with id of " + id);
	}
	
}

//  /branchLocations GET	
//Get all  branch locations	
	
//   /branchLocations/{address}	
// findBranchByAddress	
	
}
