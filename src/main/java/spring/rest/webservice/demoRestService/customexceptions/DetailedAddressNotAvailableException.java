package spring.rest.webservice.demoRestService.customexceptions;

public class DetailedAddressNotAvailableException extends RuntimeException {

	
	public DetailedAddressNotAvailableException(String message) {
		super(message);
		
	}

}
