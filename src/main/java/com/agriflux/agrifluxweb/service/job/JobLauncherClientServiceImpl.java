package com.agriflux.agrifluxweb.service.job;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JobLauncherClientServiceImpl {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JobLauncherClientServiceImpl.class);
	
	private final RestTemplate restTemplate;
	private final String batchUrl;
	
	public JobLauncherClientServiceImpl(RestTemplate restTemplate, String batchUrl) {
		this.restTemplate = restTemplate;
		this.batchUrl = batchUrl;
	}
	
	public ResponseEntity<Void> startSimulationJob() {
		
		String url = batchUrl + "/simulationJob.html";

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
		HttpEntity<Void> httpHentity = new HttpEntity<>(httpHeaders);
		
		ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.POST, httpHentity, Void.class);
		
		LOGGER.info("Response servizio Start Simulation Job: {}", response);
		
		return response;
	}
}
