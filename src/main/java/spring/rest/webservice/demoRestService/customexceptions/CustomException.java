package spring.rest.webservice.demoRestService.customexceptions;

import java.util.Date;

public class CustomException {

private String message; 

private Date timestamp;

private String bindingResultMsg;




public CustomException(String message, Date timestamp) {
	super();
	this.message = message;
	this.timestamp = timestamp;
}


public CustomException(String message, Date timestamp, String bindingResultMsg) {
	super();
	this.message = message;
	this.timestamp = timestamp;
	this.bindingResultMsg = bindingResultMsg;
}


public String getMessage() {
	return message;
}

public Date getTimestamp() {
	return timestamp;
}
	
public String getBindingResultMsg() {
	return bindingResultMsg;
}


}
