package com.ozcanmuhammet.polling.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
	
	@Bean
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedHeader("*");
		config.setAllowedOrigins(getCorsDomains());
		config.setAllowedMethods(getCorsMethods());
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
	
	private List<String> getCorsDomains() {
		List<String> corsDomains = new ArrayList<String>();
		corsDomains.add("http://localhost:3000");
		corsDomains.add("http://localhost:3001");
		return corsDomains;
	}
	
	private List<String> getCorsMethods() {
		return Arrays.asList(
				"HEAD", 
				"GET", 
				"POST", 
				"PUT", 
				"DELETE", 
				"PATCH", 
				"OPTIONS"
			);
	}

}
