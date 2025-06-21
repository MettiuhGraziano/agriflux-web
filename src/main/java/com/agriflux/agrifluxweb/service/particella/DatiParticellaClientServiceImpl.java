package com.agriflux.agrifluxweb.service.particella;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.agriflux.agrifluxshared.dto.particella.DatiParticellaDTO;
import com.agriflux.agrifluxshared.service.particella.DatiParticellaService;

@Service
public class DatiParticellaClientServiceImpl implements DatiParticellaService {
	
	private final RestTemplate restTemplate;
	private final String batchUrl;
	
	public DatiParticellaClientServiceImpl(RestTemplate restTemplate, String batchUrl) {
		this.restTemplate = restTemplate;
		this.batchUrl = batchUrl;
	}
	
	@Override
	public List<DatiParticellaDTO> findAllParticellaSortById() {
		String url = batchUrl + "/findAllParticellaSortById";

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
		HttpEntity<Void> httpHentity = new HttpEntity<>(httpHeaders);

		ResponseEntity<List<DatiParticellaDTO>> response = restTemplate.exchange(url, HttpMethod.GET, httpHentity,
				new ParameterizedTypeReference<List<DatiParticellaDTO>>() {
				});

		return response.getBody();
	}

}
