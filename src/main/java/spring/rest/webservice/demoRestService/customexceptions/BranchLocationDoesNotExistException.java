package spring.rest.webservice.demoRestService.customexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BranchLocationDoesNotExistException extends RuntimeException {

	public BranchLocationDoesNotExistException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
	
}
