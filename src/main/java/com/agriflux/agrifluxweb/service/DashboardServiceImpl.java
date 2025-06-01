package com.agriflux.agrifluxweb.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
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
        ResponseEntity<ColturaDTO[]> response = restTemplate.getForEntity(url, ColturaDTO[].class);
		
        //TODO MODIFICA UTILIZZANDO EXCHANGE
        
		return Arrays.asList(response.getBody());
	}
}
