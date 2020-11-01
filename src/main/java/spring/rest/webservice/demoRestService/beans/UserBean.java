package spring.rest.webservice.demoRestService.beans;

import io.swagger.annotations.ApiModel;

@ApiModel(description="Includes data such as the user's name and the user's email")
public class UserBean {

private String name; 

private String email;
	
public UserBean(String name, String email){
	this.name = name;
	this.email = email;
}



// below are setters and getters for fields. 
public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}



@Override
public String toString() {
	return "UserBean [name=" + name + ", email=" + email + "]";
}


	
}
