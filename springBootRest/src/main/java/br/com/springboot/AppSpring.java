package br.com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AppSpring extends SpringBootServletInitializer {

	public static void main(String[]args) {
		
		
		SpringApplication.run(AppSpring.class, args);
		
		
	}
	
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
		
		
		return super.configure(builder);
		
	}
	
	
	
	

}
