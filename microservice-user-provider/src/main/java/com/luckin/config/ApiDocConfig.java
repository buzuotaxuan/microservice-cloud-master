package com.luckin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2 
public class ApiDocConfig {
	
	 @Bean
	    public Docket api() { 
	        return new Docket(DocumentationType.SWAGGER_2).
	           apiInfo(apiInfo()).useDefaultResponseMessages(false)
//	           .forCodeGeneration(true)
	          .select()                                  
	          .apis(RequestHandlerSelectors.basePackage("com.luckin.user.web.controller"))           
	          .paths(PathSelectors.any())                      
	          .build();                                           
	    }
	 
	 private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	                .title("Luckin")
	                .version("v1")
	                .build();
	    }
	 
	/*
	 @Bean
	    public Docket petApi() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .groupName("full-petstore")
	                .apiInfo(apiInfo())
	                .forCodeGeneration(true)
	                .select()
	                .paths(petstorePaths())
	                .build();
	    }
	    private Predicate<String> petstorePaths() {
	        return or(
	                regex("/api/pet.*"),
	                regex("/api/user.*"),
	                regex("/api/store.*")
	        );
	    }

	    @Bean
	    public Docket adminApi(){
	        return new Docket(DocumentationType.SWAGGER_2)
	                .groupName("admins")
	                .apiInfo(apiInfo())
	                .forCodeGeneration(true)
	                .select()
	                .paths(regex("/admins.*"))
	                .build();
	    }


	    @Bean
	    public Docket userApi() {
	        AuthorizationScope[] authScopes = new AuthorizationScope[1];
	        authScopes[0] = new AuthorizationScopeBuilder()
	                .scope("read")
	                .description("read access")
	                .build();
	        SecurityReference securityReference = SecurityReference.builder()
	                .reference("test")
	                .scopes(authScopes)
	                .build();

	        ArrayList<SecurityContext> securityContexts = newArrayList(SecurityContext.builder().securityReferences
	                (newArrayList(securityReference)).build());
	        return new Docket(DocumentationType.SWAGGER_2)
	                .securitySchemes(newArrayList(new BasicAuth("test")))
	                .securityContexts(securityContexts)
	                .groupName("user")
	                .apiInfo(apiInfo())
	                .select()
	                .paths(userOnlyEndpoints())
	                .build();
	    }
	    private Predicate<String> userOnlyEndpoints() {
	        return new Predicate<String>() {
	            @Override
	            public boolean apply(String input) {
	                return input.contains("user");
	            }
	        };
	    }

	    @Bean
	    public Docket configSpringfoxDocket_all() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .produces(Sets.newHashSet("application/json"))
	                .consumes(Sets.newHashSet("application/json"))
	                .protocols(Sets.newHashSet("http", "https"))
	                .forCodeGeneration(true)
	                .select().paths(regex(".*"))
	                .build();
	    }

	    @Bean
	    public Docket configSpringfoxDocket_foo() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .groupName("foo")
	                .produces(Sets.newHashSet("application/json"))
	                .consumes(Sets.newHashSet("application/json"))
	                .protocols(Sets.newHashSet("http", "https"))
	                .forCodeGeneration(true)
	                .select().paths(regex(".*foo.*"))
	                .build();
	    }
*/}
