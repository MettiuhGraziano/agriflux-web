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

import com.agriflux.agrifluxshared.dto.terreno.TerrenoDTO;
import com.agriflux.agrifluxshared.service.terreno.DatiRilevazioneTerrenoService;

@Service
public class DatiRilevazioneTerrenoClientServiceImpl implements DatiRilevazioneTerrenoService {

	private final RestTemplate restTemplate;
	private final String batchUrl;
	
	DatiRilevazioneTerrenoClientServiceImpl(RestTemplate restTemplate, String batchUrl) {
		this.restTemplate = restTemplate;
		this.batchUrl = batchUrl;
	}
	
	@Override
	public List<TerrenoDTO> findAllRilevazioneTerrenoSortById() {
		String url = batchUrl + "/findAllRilevazioneTerrenoSortById";

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
		HttpEntity<Void> httpHentity = new HttpEntity<>(httpHeaders);

		ResponseEntity<List<TerrenoDTO>> response = restTemplate.exchange(url, HttpMethod.GET, httpHentity,
				new ParameterizedTypeReference<List<TerrenoDTO>>() {
				});

		return response.getBody();
	}

}
