package com.agriflux.agrifluxweb.service.fatturato;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.agriflux.agrifluxshared.dto.fatturato.FatturatoDTO;
import com.agriflux.agrifluxshared.service.fatturato.DatiFatturatoService;

@Service
public class DatiFatturatoClientServiceImpl implements DatiFatturatoService {
	
	private final RestTemplate restTemplate;
	private final String batchUrl;
	
	public DatiFatturatoClientServiceImpl(RestTemplate restTemplate, String batchUrl) {
		this.restTemplate = restTemplate;
		this.batchUrl = batchUrl;
	}
	
	@Override
	public List<FatturatoDTO> findAllFatturatoSortById() {
		String url = batchUrl + "/findAllFatturatoSortById";

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
		HttpEntity<Void> httpHentity = new HttpEntity<>(httpHeaders);

		ResponseEntity<List<FatturatoDTO>> response = restTemplate.exchange(url, HttpMethod.GET, httpHentity,
				new ParameterizedTypeReference<List<FatturatoDTO>>() {
				});

		return response.getBody();
	}

}
