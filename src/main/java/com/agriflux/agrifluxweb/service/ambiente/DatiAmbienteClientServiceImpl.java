package com.agriflux.agrifluxweb.service.ambiente;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.agriflux.agrifluxshared.dto.ambiente.AmbienteDTO;
import com.agriflux.agrifluxshared.service.ambiente.DatiAmbienteService;

@Service
public class DatiAmbienteClientServiceImpl implements DatiAmbienteService {
	
	private final RestTemplate restTemplate;
	private final String batchUrl;
	
	DatiAmbienteClientServiceImpl(RestTemplate restTemplate, String batchUrl) {
		this.restTemplate = restTemplate;
		this.batchUrl = batchUrl;
	}
	
	@Override
	public List<AmbienteDTO> findAllAmbienteSortById() {
		String url = batchUrl + "/findAllAmbienteSortById";

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
		HttpEntity<Void> httpHentity = new HttpEntity<>(httpHeaders);

		ResponseEntity<List<AmbienteDTO>> response = restTemplate.exchange(url, HttpMethod.GET, httpHentity,
				new ParameterizedTypeReference<List<AmbienteDTO>>() {
				});

		return response.getBody();
	}
	
}
