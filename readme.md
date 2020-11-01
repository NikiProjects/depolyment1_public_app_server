commit # 1:
After making the changes in this commit, we were able to access the following Swagger endpoints in Google Chrome. 
1) http://localhost:8081/swagger-ui.html#/
2) http://localhost:8081/v2/api-docs

To access these endpoints, we make the following changes in our project. 
In the pom.xml, we added two new dependencies: 

dependency:
groupId is io.springfox
artifactId is springfox-swagger2
version is 2.9.2    
    
dependency:
groupId is io.springfox
artifactId is springfox-swagger-ui
version is 2.9.2

Please see the pom.xml for a combination of dependencies which allow us to access above mentioned endpoints
It is reccommended that before accessing these endpoints, we should first clear the browsing History. 


Next, we created a new Configuration class in the same package as the Main class. At the class level, we 
added the annotations 
- org.springframework.context.annotation.Configuration (used to turn our Java class into a Configuration class)
- springfox.documentation.swagger2.annotations.EnableSwagger2 
In this class, we manually create a bean called org.springframework.hateoas.client.LinkDiscoverers. 
Next, in this same class, we generate a springfox.documentation.spring.web.plugins.Docket bean. This Docket Bean is used to 
configure Swagger for the application. 

Because, there is a slight incompatibility when using the latest versions of HATEOS and Swagger simultaneously, we need to make the following changes in our project: 
The incompatibility between HATEOAS and Swagger is due to the fact that swagger(springfox) uses the dependency Spring Plugin Core 1.2.0 RELEASE while HATEOAS uses a newer dependency. To fix this conflict, we configure HATEOAS to use Spring Plugin Core 1.2.0.RELEASE as well. We manually generate the LinkDiscoverers beans by using dependency Spring Plugin Core 1.2.0.RELEASE. 
In our pom.xml, we add the Spring Plugin Core using the following groupId and artifactId.
groupId: org.springframework.plugin
artifactId: spring-plugin-core
version: 1.2.0.RELEASE

We should create the org.springframework.hateoas.client.LinkDiscoverers bean in the same class which enables Swagger. The @EnableSwagger2 annotation enables Swagger and is used to load all the beans required for Swagger configuration. 

When using the newer version of HATEOAS, the following changes are applicable:
In case you are using HATEOAS v1.0 and above (Spring boot >= 2.2.0), do note that the classnames have changed. Notably the below classes have been renamed:

ResourceSupport changed to RepresentationModel
Resource changed to EntityModel
Resources changed to CollectionModel
PagedResources changed to PagedModel
ResourceAssembler changed to RepresentationModelAssembler

commit # 2: 

The code changes in this commit enhance the Swagger documentation. 
With these changes the Swagger documentation now describes the validation performed on our bean properties. 
We added the validation description using the library io.swagger.annotations.ApiModelProperty. 
In the JSON found at url localhost:/8081/v2/api-docs, we find this validation description under the
'definitions' field. 

In the Swagger documentation, we also added a bean description for the beans that we implemented in our REST application. We did this using library io.swagger.annotations.ApiModel. In the Swagger documentation found at url localhost:/8081/v2/api-docs, we see the bean description appear under the 'definitions' json field. 

The Model Section at url http://localhost:8081/swagger-ui.html#/ lists all the beans and objects used to build our web service implementation. The bean related descriptions which we add using io.swagger.annotations.ApiModel or io.swagger.annotations.ApiModelProperty can be found in this Model Section.  

In our Configuration Java class, we made further configurations to the springfox.documentation.spring.web.plugins.Docket Java
object, so that we can customize the information displayed in the 'info' json field found at url localhost:/8081/v2/api-docs.
The 'info' json field displays high level information about our API. This same nspringfox.documentation.spring.web.plugins.Docket Java object was used to display in the Swagger documentation the format that a particular API/Service will consume/produce. This info can be seen at url localhost:/8081/v2/api-docs in the 'consumes' and 'produces' JSON field. 

We also demoed the usage of additional Swagger annotations from package io.swagger.annotations.*. 
The annotations such as:
@ApiResponses - use this annotation when using multiple @ApiResponse annotations.  
@ApiResponse - Using this annotation, we can specify a descriptive message that will be displayed for given response status code. The 'message' attribute of this annotation is rendered in the 'paths' json field, 'responses' json field of the Swagger
document found at localhost:/8081/v2/api-docs.  
@ApiParam - The attributes of the @ApiParam annotation are rendered at url http://localhost:8081/swagger-ui.html#/. At this
url, we can expand a specific operation to see that the 'value' attribute and the 'example' attribute of the annotation are rendered in the text box input field and the operation's description 
@ApiOperation - the attributes of the ApiOperation annotation are rendered in the 'paths' json field at url localhost:/8081/v2/api-docs. 

were used to update the Swagger documentation.  


The validation of our bean properties should also be included in our Swagger documentation. 

provide a description about the Java objects used to build our service/api. For example, our custom beans used in the implementation of our service/api. 

Location of project on C drive: C:\Users\Nikita\WebServicesWithSpringBootCourse\demoRestServiceWithSwagger2



