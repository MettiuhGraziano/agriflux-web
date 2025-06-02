package com.agriflux.agrifluxweb.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.agriflux.agrifluxweb.model.ColturaDTO;
import com.agriflux.agrifluxweb.service.DashboardService;
import com.agriflux.agrifluxweb.service.DashboardServiceImpl;

@Controller
public class DashboardController implements DashboardService {
	
	//TODO Controller utilizzato da THYMELEAF per la gestione e comunicazione tra le pagine html
	
	private final DashboardServiceImpl dashboardServiceImpl;
	
	public DashboardController(DashboardServiceImpl dashboardService) {
		this.dashboardServiceImpl = dashboardService;
	}
	
	@GetMapping("/")
	public String home() {
	    return "home";
	}
	
	@ModelAttribute(name = "colture")
	public List<ColturaDTO> findAllColtureSortById(){
		return dashboardServiceImpl.findAllColtureSortById();
	}
}
