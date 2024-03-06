package com.NBK.xmlextractor.SpringBootApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main entry point for the Spring Boot application.
 * Performs component scanning across multiple packages to ensure all
 * relevant beans are discovered.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.NBK.xmlextractor.Controller", "com.NBK.xmlextractor.Service","com.NBK.xmlextractor.XMLConfiguration","com.NBK.xmlextractor.XMLParser",})
public class XmlextractorApplication {

	public static void main(String[] args) {
		SpringApplication.run(XmlextractorApplication.class, args);
	}


}