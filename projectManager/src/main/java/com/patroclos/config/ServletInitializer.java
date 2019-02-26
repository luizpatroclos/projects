package com.patroclos.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	//ServletInitializer – which acts as replacement of any spring configuration defined in web.xml
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		
		return new Class[] {HibernateConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		
		return new Class [] {WebMvcConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		
		return new String[] { "/" };
	}
	
	
	
	
	

}
