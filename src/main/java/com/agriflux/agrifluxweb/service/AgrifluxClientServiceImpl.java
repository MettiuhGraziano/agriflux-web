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

import com.agriflux.agrifluxshared.dto.AmbienteDTO;
import com.agriflux.agrifluxshared.dto.ColturaDTO;
import com.agriflux.agrifluxshared.dto.ColturaGroupByProdottoDTO;
import com.agriflux.agrifluxshared.dto.ColturaListPrezzoDataRaccoltoDTO;
import com.agriflux.agrifluxshared.dto.MorfologiaDTO;
import com.agriflux.agrifluxshared.dto.ProduzioneDTO;
import com.agriflux.agrifluxshared.dto.TerrenoDTO;
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
	public List<ColturaDTO> findAllColturaSortById() {
		
		String url = batchUrl + "/datiColture";
		
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<Void> httpHentity = new HttpEntity<>(httpHeaders);
        
		ResponseEntity<List<ColturaDTO>> response = restTemplate.exchange(url, HttpMethod.GET, httpHentity,
				new ParameterizedTypeReference<List<ColturaDTO>>() {
				});
        
		return response.getBody();
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
	public List<MorfologiaDTO> findAllMorfologiaSortById() {
		
		String url = batchUrl + "/datiMorfologici";
		
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<Void> httpHentity = new HttpEntity<>(httpHeaders);
        
		ResponseEntity<List<MorfologiaDTO>> response = restTemplate.exchange(url, HttpMethod.GET, httpHentity,
				new ParameterizedTypeReference<List<MorfologiaDTO>>() {
				});
        
		return response.getBody();
	}

	@Override
	public List<TerrenoDTO> findAllTerrenoSortById() {
		
		String url = batchUrl + "/datiTerreni";
		
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<Void> httpHentity = new HttpEntity<>(httpHeaders);
        
		ResponseEntity<List<TerrenoDTO>> response = restTemplate.exchange(url, HttpMethod.GET, httpHentity,
				new ParameterizedTypeReference<List<TerrenoDTO>>() {
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
	public List<ColturaGroupByProdottoDTO> countColtureGroupByProdotto() {
		
		String url = batchUrl + "/listColtureGroupByProdotto";
		
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<Void> httpHentity = new HttpEntity<>(httpHeaders);
        
		ResponseEntity<List<ColturaGroupByProdottoDTO>> response = restTemplate.exchange(url, HttpMethod.GET, httpHentity,
				new ParameterizedTypeReference<List<ColturaGroupByProdottoDTO>>() {
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
