package com.qbo.swagger;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.securityContexts(Arrays.asList(securityContext()))
				.securitySchemes(Arrays.asList(apiKey()))
				.select()
				.apis(RequestHandlerSelectors.any())
				.build();
				
	}
	//Que datos son por donde vamos a invocar a nuestros recursos.
	private ApiKey apiKey() {
		return new ApiKey("JWT", "Authorization", "header");
	}
	//Obtiene un listado que parte de la autenticación que se tiene.
	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}
	//Define el requisito a para la seguridad de la API
	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = 
				new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] autoAuthorizationScopes = 
				new AuthorizationScope[1];
		autoAuthorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("JWT", autoAuthorizationScopes));
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("API Rest QBO Instiute", 
				"API Restpara el curso de Spring Boot", 
				"1.0", 
				"Términos y condiciones del autor", 
				new Contact("Luis Salvatierra", "www.qboinstitute.com", "lsalvatierra@qboinstitute.com"), 
				"Licencia Libre", 
				"API Licencia QBO",
				Collections.emptyList());
	}

}
