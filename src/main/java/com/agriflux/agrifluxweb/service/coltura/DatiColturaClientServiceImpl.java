package com.agriflux.agrifluxweb.service.coltura;

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

import com.agriflux.agrifluxshared.dto.coltura.ColturaDTO;
import com.agriflux.agrifluxshared.dto.coltura.ColturaListPrezzoDataRaccoltoDTO;
import com.agriflux.agrifluxshared.service.coltura.DatiColturaService;

@Service
public class DatiColturaClientServiceImpl implements DatiColturaService{
	
	private final RestTemplate restTemplate;
	private final String batchUrl;
	
	DatiColturaClientServiceImpl(RestTemplate restTemplate, String batchUrl) {
		this.restTemplate = restTemplate;
		this.batchUrl = batchUrl;
	}
	
	@Override
	public List<ColturaDTO> findAllColturaSortById() {
		String url = batchUrl + "/findAllColturaSortById";

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
		HttpEntity<Void> httpHentity = new HttpEntity<>(httpHeaders);

		ResponseEntity<List<ColturaDTO>> response = restTemplate.exchange(url, HttpMethod.GET, httpHentity,
				new ParameterizedTypeReference<List<ColturaDTO>>() {
				});

		return response.getBody();
	}

	@Override
	public Map<String, Long> countOrtaggioColtura() {
		String url = batchUrl + "/countOrtaggioColtura";

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
		HttpEntity<Void> httpHentity = new HttpEntity<>(httpHeaders);

		ResponseEntity<Map<String, Long>> response = restTemplate.exchange(url, HttpMethod.GET, httpHentity,
				new ParameterizedTypeReference<Map<String, Long>>() {
				});

		return response.getBody();
	}

	@Override
	public Map<String, Long> countFamigliaOrtaggioColtura() {
		String url = batchUrl + "/countFamigliaOrtaggioColtura";

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
		HttpEntity<Void> httpHentity = new HttpEntity<>(httpHeaders);

		ResponseEntity<Map<String, Long>> response = restTemplate.exchange(url, HttpMethod.GET, httpHentity,
				new ParameterizedTypeReference<Map<String, Long>>() {
				});

		return response.getBody();
	}

	@Override
	public Map<String, ColturaListPrezzoDataRaccoltoDTO> findPrezziAndDateColtura() {
		String url = batchUrl + "/findPrezziAndDateRaccoltoColtura";

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
		HttpEntity<Void> httpHentity = new HttpEntity<>(httpHeaders);

		ResponseEntity<Map<String, ColturaListPrezzoDataRaccoltoDTO>> response = restTemplate.exchange(url, HttpMethod.GET, httpHentity,
				new ParameterizedTypeReference<Map<String, ColturaListPrezzoDataRaccoltoDTO>>() {
				});

		return response.getBody();
	}

}
