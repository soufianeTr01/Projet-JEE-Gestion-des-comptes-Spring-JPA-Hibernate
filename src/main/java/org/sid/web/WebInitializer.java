package org.sid.web;

import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.sid.VotreBanqueApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

public class WebInitializer extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(VotreBanqueApplication.class);
		}
	
}
