package com.agriflux.agrifluxweb.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DashboardConfiguration {
	
	private static final String BASE_URL = "http://localhost:8081";

    @Bean
    RestTemplate restTemplate() {
    	
    	/*TODO Per una gestione custom di timeout, proxy, SSL etc... passare 
    	 * nel costruttore HttpComponentsClientHttpRequestFactory (ricorda di inserire la dipendenza nel pom)
    	*/
        return new RestTemplate();
    }

    @Bean
    String batchServiceBaseUrl() {
        return BASE_URL;
    }
    
    @Bean
    DateFormatter dateFormatter() {
    	return new DateFormatter();
    }
}
