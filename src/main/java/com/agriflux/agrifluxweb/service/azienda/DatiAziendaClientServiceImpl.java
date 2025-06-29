package com.agriflux.agrifluxweb.service.azienda;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.agriflux.agrifluxshared.dto.azienda.AziendaDTO;
import com.agriflux.agrifluxshared.service.azienda.DatiAziendaService;

@Service
public class DatiAziendaClientServiceImpl implements DatiAziendaService {
	
	private final RestTemplate restTemplate;
	private final String batchUrl;
	
	DatiAziendaClientServiceImpl(RestTemplate restTemplate, String batchUrl) {
		this.restTemplate = restTemplate;
		this.batchUrl = batchUrl;
	}
	
	@Override
	public AziendaDTO findAzienda() {
		String url = batchUrl + "/findAzienda";

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
		HttpEntity<Void> httpHentity = new HttpEntity<>(httpHeaders);

		ResponseEntity<AziendaDTO> response = restTemplate.exchange(url, HttpMethod.GET, httpHentity,
				new ParameterizedTypeReference<AziendaDTO>() {
				});

		return response.getBody();
	}

}
