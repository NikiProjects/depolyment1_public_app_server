package spring.rest.webservice.demoRestService.customexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UnableToRetrieveBranchLocationsException extends RuntimeException {

	public UnableToRetrieveBranchLocationsException(String message) {
		super(message);
		
	}

	
}
