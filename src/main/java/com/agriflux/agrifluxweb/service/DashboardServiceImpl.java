package com.agriflux.agrifluxweb.service;

import java.util.List;

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
import com.agriflux.agrifluxshared.dto.MorfologiaDTO;
import com.agriflux.agrifluxshared.dto.ProduzioneDTO;
import com.agriflux.agrifluxshared.dto.TerrenoDTO;
import com.agriflux.agrifluxshared.service.AgrifluxDataService;

@Service
public class DashboardServiceImpl implements AgrifluxDataService {
	
	private final RestTemplate restTemplate;
	private final String batchUrl;
	
	public DashboardServiceImpl(RestTemplate restTemplate, @Value("#{batchServiceBaseUrl}") String batchUrl) {
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MorfologiaDTO> findAllMorfologiaSortById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TerrenoDTO> findAllTerrenoSortById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProduzioneDTO> findAllProduzioneSortById() {
		// TODO Auto-generated method stub
		return null;
	}
}
