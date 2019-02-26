package com.patroclos.config;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration: Used to indicate that a class declares one or more @Bean methods. 
//These classes are processed by the Spring container to generate bean definitions and service requests for those beans at runtime.
@Configuration

//@EnableWebMvc is equivalent to <mvc:annotation-driven /> in XML Basically is used for enable Spring MVC. 
//It enables support for @Controller-annotated classes that use @RequestMapping to map incoming requests to a certain method.
@EnableWebMvc

//This annotation is used with @Configuration annotation to allow Spring to know the packages 
//to scan for annotated components. @ComponentScan is also used to specify base packages using basePackageClasses or basePackage attributes to scan.
@ComponentScan(basePackages = "com.patroclos")

public class WebMvcConfig implements WebMvcConfigurer {
	
	//WebMvcConfig – This configuration class can be treated as a replacement of spring-servlet.xml
	//as it contains all the information required for component-scanning and view resolver
	
		
	public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp().prefix("/WEB-INF/views/").suffix(".jsp");
       
    }
	
	
	 @Override
	   public void addResourceHandlers(ResourceHandlerRegistry registry) {

	      // Register resource handler for CSS and JS
	      registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/statics/", "D:/statics/")
	            .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());

	      // Register resource handler for images
	      registry.addResourceHandler("/img/**").addResourceLocations("/WEB-INF/img/")
	            .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
	   }
	
	

}
