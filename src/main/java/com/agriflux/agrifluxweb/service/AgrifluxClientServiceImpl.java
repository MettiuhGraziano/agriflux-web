package com.agriflux.agrifluxweb.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.agriflux.agrifluxshared.dto.ambiente.AmbienteDTO;
import com.agriflux.agrifluxshared.dto.produzione.ProduzioneColturaDTO;
import com.agriflux.agrifluxshared.dto.produzione.ProduzioneColturaTempiDTO;
import com.agriflux.agrifluxshared.dto.produzione.ProduzioneDTO;
import com.agriflux.agrifluxshared.dto.produzione.ProduzioneMorfologiaColturaDTO;
import com.agriflux.agrifluxshared.service.AgrifluxDataService;

@Service
public class AgrifluxClientServiceImpl implements AgrifluxDataService {
	
	private final RestTemplate restTemplate;
	private final String batchUrl;
	
	public AgrifluxClientServiceImpl(RestTemplate restTemplate, @Value("#{batchServiceBaseUrl}") String batchUrl) {
		this.restTemplate = restTemplate;
		this.batchUrl = batchUrl;
	}

	@Override
	public List<AmbienteDTO> findAllAmbienteSortById() {
		
		String url = batchUrl + "/datiAmbientali";
		
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<Void> httpHentity = new HttpEntity<>(httpHeaders);
        
		ResponseEntity<List<AmbienteDTO>> response = restTemplate.exchange(url, HttpMethod.GET, httpHentity,
				new ParameterizedTypeReference<List<AmbienteDTO>>() {
				});
        
		return response.getBody();
	}

	@Override
	public List<ProduzioneDTO> findAllProduzioneSortById() {
		
		String url = batchUrl + "/datiProduzione";
		
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<Void> httpHentity = new HttpEntity<>(httpHeaders);
        
		ResponseEntity<List<ProduzioneDTO>> response = restTemplate.exchange(url, HttpMethod.GET, httpHentity,
				new ParameterizedTypeReference<List<ProduzioneDTO>>() {
				});
        
		return response.getBody();
	}

	@Override
	public Map<String, Map<String, ProduzioneColturaDTO>> findColtureJoinProduzione() {
		
		String url = batchUrl + "/findColtureJoinProduzione";
		
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<Void> httpHentity = new HttpEntity<>(httpHeaders);
        
		ResponseEntity<Map<String, Map<String, ProduzioneColturaDTO>>> response = restTemplate.exchange(url, HttpMethod.GET, httpHentity,
				new ParameterizedTypeReference<Map<String, Map<String, ProduzioneColturaDTO>>>() {
				});
        
		return response.getBody();
	}

	@Override
	public Map<String, List<ProduzioneColturaTempiDTO>> findProduzioneTempiJoinColtura() {
		
		String url = batchUrl + "/findProduzioneTempiJoinColtura";
		
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<Void> httpHentity = new HttpEntity<>(httpHeaders);
        
		ResponseEntity<Map<String, List<ProduzioneColturaTempiDTO>>> response = restTemplate.exchange(url, HttpMethod.GET, httpHentity,
				new ParameterizedTypeReference<Map<String, List<ProduzioneColturaTempiDTO>>>() {
				});
        
		return response.getBody();
	}
	
	@Override
	public Map<Long, ProduzioneMorfologiaColturaDTO> findProduzioneJoinColturaMorfologia() {
		
		String url = batchUrl + "/findProduzioneJoinColturaMorfologia";

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
		HttpEntity<Void> httpHentity = new HttpEntity<>(httpHeaders);

		ResponseEntity<Map<Long, ProduzioneMorfologiaColturaDTO>> response = restTemplate.exchange(url,
				HttpMethod.GET, httpHentity,
				new ParameterizedTypeReference<Map<Long, ProduzioneMorfologiaColturaDTO>>() {
				});

		return response.getBody();
	}

}
