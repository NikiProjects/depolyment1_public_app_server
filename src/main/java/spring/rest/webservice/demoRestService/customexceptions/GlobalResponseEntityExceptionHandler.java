package spring.rest.webservice.demoRestService.customexceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class GlobalResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

/*@ExceptionHandler(Exception.class)
public final ResponseEntity<Object> handleAllApplicationExceptions(Exception ex, WebRequest request) throws Exception {
	CustomException customException = new CustomException(request.getDescription(false),new Date());
	return new ResponseEntity(customException,HttpStatus.INTERNAL_SERVER_ERROR);
	
}
*/

@ExceptionHandler(BranchLocationDoesNotExistException.class)
public final ResponseEntity<Object> handleBranchLocationDoesNotExistExceptions(BranchLocationDoesNotExistException ex, WebRequest request) throws Exception {
	CustomException customException = new CustomException(request.getDescription(false),new Date());
	return new ResponseEntity(customException,HttpStatus.NOT_FOUND);
	
}


@ExceptionHandler(DetailedAddressNotAvailableException.class)
public final ResponseEntity<Object> handleAllApplicationExceptions(DetailedAddressNotAvailableException ex, WebRequest request) throws Exception {
	CustomException customException = new CustomException(request.getDescription(false),new Date());
	return new ResponseEntity(customException,HttpStatus.INTERNAL_SERVER_ERROR);
	
}

@Override 
protected ResponseEntity<Object> handleMethodArgumentNotValid(
		MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

	CustomException customException = new CustomException(request.getDescription(false),new Date(),ex.getBindingResult().toString());
	return new ResponseEntity(customException,HttpStatus.BAD_REQUEST);
	

	
}

}
