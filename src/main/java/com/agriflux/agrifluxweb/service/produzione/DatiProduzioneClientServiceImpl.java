package com.agriflux.agrifluxweb.service.produzione;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.agriflux.agrifluxshared.dto.produzione.ProduzioneDTO;
import com.agriflux.agrifluxshared.service.produzione.DatiProduzioneService;

@Service
public class DatiProduzioneClientServiceImpl implements DatiProduzioneService {
	
	private final RestTemplate restTemplate;
	private final String batchUrl;
	
	DatiProduzioneClientServiceImpl(RestTemplate restTemplate, String batchUrl) {
		this.restTemplate = restTemplate;
		this.batchUrl = batchUrl;
	}
	
	@Override
	public List<ProduzioneDTO> findAllProduzioneSortById() {
		String url = batchUrl + "/findAllProduzioneSortById";

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
		HttpEntity<Void> httpHentity = new HttpEntity<>(httpHeaders);

		ResponseEntity<List<ProduzioneDTO>> response = restTemplate.exchange(url, HttpMethod.GET, httpHentity,
				new ParameterizedTypeReference<List<ProduzioneDTO>>() {
				});

		return response.getBody();
	}

}
