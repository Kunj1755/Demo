package com.demo.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@ComponentScan(basePackages="com.demo.controllers")
@Configuration
public class AppConfig extends WebMvcConfigurerAdapter{

	@Bean
	public InternalResourceViewResolver getIVR() {
		System.out.println("Setting up view resolver");
		InternalResourceViewResolver irvr = new InternalResourceViewResolver();
		irvr.setPrefix("WEB-INF/jsp/");
		irvr.setSuffix(".jsp");
		return irvr;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		System.out.println("Setting up resource");
		registry.addResourceHandler("/myResources/**").addResourceLocations("/resources");
	}
}
