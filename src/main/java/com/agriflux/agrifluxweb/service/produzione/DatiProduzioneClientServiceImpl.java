package com.agriflux.agrifluxweb.service.produzione;

import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.agriflux.agrifluxshared.dto.produzione.ProduzioneColturaDTO;
import com.agriflux.agrifluxshared.dto.produzione.ProduzioneColturaTempiDTO;
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

	@Override
	public Map<String, Map<String, ProduzioneColturaDTO>> findProduzioneQuantitaJoinColtura() {
		String url = batchUrl + "/findProduzioneQuantitaJoinColtura";

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
		HttpEntity<Void> httpHentity = new HttpEntity<>(httpHeaders);

		ResponseEntity<Map<String, Map<String, ProduzioneColturaDTO>>> response = restTemplate.exchange(url,
				HttpMethod.GET, httpHentity,
				new ParameterizedTypeReference<Map<String, Map<String, ProduzioneColturaDTO>>>() {
				});

		return response.getBody();
	}

	@Override
	public Map<String, List<ProduzioneColturaTempiDTO>> findProduzioneJoinColturaTempi() {
		String url = batchUrl + "/findProduzioneJoinColturaTempi";

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
		HttpEntity<Void> httpHentity = new HttpEntity<>(httpHeaders);

		ResponseEntity<Map<String, List<ProduzioneColturaTempiDTO>>> response = restTemplate.exchange(url,
				HttpMethod.GET, httpHentity,
				new ParameterizedTypeReference<Map<String, List<ProduzioneColturaTempiDTO>>>() {
				});

		return response.getBody();
	}

}
