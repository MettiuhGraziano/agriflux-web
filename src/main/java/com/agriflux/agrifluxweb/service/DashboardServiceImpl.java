package com.agriflux.agrifluxweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.agriflux.agrifluxweb.model.ColturaDTO;

@Service
public class DashboardServiceImpl implements DashboardService {
	
	private final RestTemplate restTemplate;
	private final String batchUrl;
	
	public DashboardServiceImpl(RestTemplate restTemplate, @Value("#{batchServiceBaseUrl}") String batchUrl) {
		this.restTemplate = restTemplate;
		this.batchUrl = batchUrl;
	}

	@Override
	public List<ColturaDTO> findAllColtureSortById() {
		
		String url = batchUrl + "/colture";
		
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<Void> httpHentity = new HttpEntity<>(httpHeaders);
        
		ResponseEntity<List<ColturaDTO>> response = restTemplate.exchange(url, HttpMethod.GET, httpHentity,
				new ParameterizedTypeReference<List<ColturaDTO>>() {
				});
        
		return response.getBody();
	}
}
