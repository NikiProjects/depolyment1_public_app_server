package troubleshootSwagger2.demoRestServiceWithSwagger2;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import spring.rest.webservice.demoRestService.beans.UserBean;

@RestController
public class HelloWorldController {

@Autowired
private MessageSource messageSource;	
	
// @RequestMapping(method=RequestMethod.GET, path="/sayHello")	
@GetMapping(path="/sayHello")	
public String sayHello()
{
	return "Hello from REST Service using differnt annotation";
}
	

@GetMapping(path="/sayHello-internationalization")	
//public String sayHelloInternationalized(@RequestHeader(name="Accept-Language",required=false) Locale locale)
public String sayHelloInternationalized()
{
	//return "Hello from REST Service using differnt annotation";
	return messageSource.getMessage("hello.message", null, LocaleContextHolder.getLocale());
}
	

//@RequestMapping(method=RequestMethod.GET, path="/sayHello")	
@GetMapping(path="/outputUserDetails")	
public UserBean getUserInfo()
{
	return new UserBean("Bill Gates","Bill_Gates@gmail.com");
}
	

//@RequestMapping(method=RequestMethod.GET, path="/sayHello")	
@GetMapping(path="/outputUserDetails/pathVar/{id}")	
public UserBean getUserInfoWithId(@PathVariable int id)
{
	return new UserBean(String.format("ID: %s Bill Gates", id),"Bill_Gates@gmail.com");
}

}
