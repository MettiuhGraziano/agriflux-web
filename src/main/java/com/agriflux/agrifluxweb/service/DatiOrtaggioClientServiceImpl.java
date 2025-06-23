package com.agriflux.agrifluxweb.service;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.agriflux.agrifluxshared.dto.ortaggio.OrtaggioDTO;
import com.agriflux.agrifluxshared.dto.ortaggio.OrtaggioRangeStagioneSumDTO;
import com.agriflux.agrifluxshared.service.ortaggio.DatiOrtaggioService;

@Service
public class DatiOrtaggioClientServiceImpl implements DatiOrtaggioService {
	
	private final RestTemplate restTemplate;
	private final String batchUrl;
	
	DatiOrtaggioClientServiceImpl(RestTemplate restTemplate, String batchUrl) {
		this.restTemplate = restTemplate;
		this.batchUrl = batchUrl;
	}
	
	@Override
	public List<OrtaggioDTO> findAllOrtaggioSortById() {
		String url = batchUrl + "/findAllOrtaggioSortById";

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
		HttpEntity<Void> httpHentity = new HttpEntity<>(httpHeaders);

		ResponseEntity<List<OrtaggioDTO>> response = restTemplate.exchange(url, HttpMethod.GET, httpHentity,
				new ParameterizedTypeReference<List<OrtaggioDTO>>() {
				});

		return response.getBody();
	}

	@Override
	public List<OrtaggioRangeStagioneSumDTO> findAllOrtaggioRangeStagione() {
		String url = batchUrl + "/findAllOrtaggioRangeStagione";

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
		HttpEntity<Void> httpHentity = new HttpEntity<>(httpHeaders);

		ResponseEntity<List<OrtaggioRangeStagioneSumDTO>> response = restTemplate.exchange(url, HttpMethod.GET, httpHentity,
				new ParameterizedTypeReference<List<OrtaggioRangeStagioneSumDTO>>() {
				});

		return response.getBody();
	}

}
