package com.agriflux.agrifluxweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.agriflux.agrifluxweb.service.DashboardService;
import com.agriflux.agrifluxweb.service.DashboardServiceImpl;

/**
 * Controller per la gestione, comunicazione e recupero dati tra pagine html
 */
@Controller
public class DashboardController implements DashboardService {
	
	//TODO Controller utilizzato da THYMELEAF per la gestione e comunicazione tra le pagine html
	
	private final DashboardServiceImpl dashboardServiceImpl;
	
	public DashboardController(DashboardServiceImpl dashboardService) {
		this.dashboardServiceImpl = dashboardService;
	}
	
	@GetMapping("/Agriflux")
	public String homepage(Model model) {
		model.addAttribute("username", "Admin");
	    return "homepage";
	}
	
	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		model.addAttribute("username", "Admin");
	    return "dashboard";
	}
	
	@Override
	@GetMapping("/coltura")
	public String getAllColtureSortById(Model model){
		model.addAttribute("colture", dashboardServiceImpl.findAllColturaSortById());
	    return "fragments/coltura :: colturaPage";
	}

	@Override
	@GetMapping("/ambiente")
	public String getAllAmbienteSortById(Model model) {
		model.addAttribute("ambienti", dashboardServiceImpl.findAllAmbienteSortById());
	    return "fragments/ambiente :: ambientePage";
	}

	@Override
	@GetMapping("/morfologia")
	public String getAllMorfologieSortById(Model model) {
		model.addAttribute("morfologie", dashboardServiceImpl.findAllMorfologiaSortById());
	    return "fragments/morfologia :: morfologiaPage";
	}

	@Override
	@GetMapping("/terreno")
	public String getAllTerreniSortById(Model model) {
		model.addAttribute("terreni", dashboardServiceImpl.findAllTerrenoSortById());
		model.addAttribute("morfologie", dashboardServiceImpl.findAllMorfologiaSortById());
	    return "fragments/terreno :: terrenoPage";
	}

	@Override
	@GetMapping("/produzione")
	public String getAllProduzioniSortById(Model model) {
		model.addAttribute("produzioni", dashboardServiceImpl.findAllProduzioneSortById());
	    return "fragments/produzione :: produzionePage";
	}
}
