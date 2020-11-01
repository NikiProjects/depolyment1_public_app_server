package troubleshootSwagger2.demoRestServiceWithSwagger2;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;

import com.google.common.collect.Lists;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class AppSpringConfig {

	
	public static final Contact CUSTOM_CONTACT = new Contact("Nikita Kulkarni", "", "NikitaK01@gmail.com");

// 1st parameter is title; 2nd parameter is description	
	  public static final ApiInfo CUSTOM_API_INFO = new ApiInfo("Title: Northern Bank Locations", "Description: Northern Bank Branch Locations", "1.0", "urn:tos",
	          CUSTOM_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<VendorExtension>());

private static final Set<String> CUSTOM_PRODUCES = new HashSet<String>(Arrays.asList("application/xml","application/json"));
private static final Set<String> CUSTOM_CONSUMES = new HashSet<String>(Arrays.asList("application/xml","application/json"));
	//private static final ApiInfo CUSTOM_API_INFO = null;

	@Bean
	public LinkDiscoverers discoverers(){
		final List<CollectionJsonLinkDiscoverer> plugins = Lists.newArrayList();
		plugins.add(new CollectionJsonLinkDiscoverer());
	  return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
	}
	
	@Bean
	public Docket api(){
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(CUSTOM_API_INFO).produces(CUSTOM_PRODUCES).consumes(CUSTOM_CONSUMES);
// above .apiInfo function call allows us to modify 'info' json field found at url localhost:/8081/v2/api-docs.	
// .apiInfo function returns an object of type springfox.documentation.spring.web.plugins.Docket. 
// Next, we can call springfox.documentation.spring.web.plugins.Docket.produces() function to configure the format in which the Response produces output. 		
// As an argument, the produces() function takes a Set of type String as an argument. 
		
	}
	
	
	
	
	
	
}
