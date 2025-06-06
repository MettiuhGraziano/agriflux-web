package com.agriflux.agrifluxweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.agriflux.agrifluxweb.service.DashboardService;
import com.agriflux.agrifluxweb.service.DashboardServiceImpl;

import jakarta.servlet.http.HttpSession;

/**
 * Controller per la gestione, comunicazione e recupero dati tra pagine html
 */
@Controller
public class DashboardController implements DashboardService {
	
	private final DashboardServiceImpl dashboardServiceImpl;
	
	public DashboardController(DashboardServiceImpl dashboardService) {
		this.dashboardServiceImpl = dashboardService;
	}
	
	@GetMapping("/login")
	public String login() {
	    return "login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate();
	    return "redirect:/login";
	}
	
	@PostMapping("/homepage")
	public String homepage(@RequestParam() String username, HttpSession session) {
		session.setAttribute("username", username);
	    return "homepage";
	}
	
	@GetMapping("/dashboard")
	public String dashboard() {
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
