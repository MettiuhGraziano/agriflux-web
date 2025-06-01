package com.agriflux.agrifluxweb.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agriflux.agrifluxweb.model.ColturaDTO;
import com.agriflux.agrifluxweb.service.DashboardServiceImpl;

@RestController("api/dashboard")
public class DashboardServiceController {
	
	//TODO CREAZIONE CLIENT PER RICHIAMARE I VARI SERVIZI ESPOSTI DA AGRIFLUX-BATCH
	
	private final DashboardServiceImpl dashboardService;
	
	public DashboardServiceController(DashboardServiceImpl dashboardService) {
		this.dashboardService = dashboardService;
	}
	
	@GetMapping("/colture")
	public List<ColturaDTO> findAllColtureSortById(){
		return dashboardService.findAllColtureSortById();
	}
}
