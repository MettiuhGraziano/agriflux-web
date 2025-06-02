package com.agriflux.agrifluxweb.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agriflux.agrifluxshared.dto.ColturaDTO;
import com.agriflux.agrifluxshared.service.AgrifluxDataService;
import com.agriflux.agrifluxweb.service.DashboardServiceImpl;

@RestController("api/dashboard")
public class DashboardServiceController implements AgrifluxDataService {
	
	//TODO CREAZIONE CLIENT API SERVIZI REST ESPOSTI DA AGRIFLUX-BATCH
	
	private final DashboardServiceImpl dashboardServiceImpl;
	
	public DashboardServiceController(DashboardServiceImpl dashboardService) {
		this.dashboardServiceImpl = dashboardService;
	}
	
	@GetMapping("/colture")
	public List<ColturaDTO> findAllColturaSortById(){
		return dashboardServiceImpl.findAllColturaSortById();
	}
}
