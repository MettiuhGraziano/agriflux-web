package com.agriflux.agrifluxweb.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.agriflux.agrifluxshared.dto.ColturaDTO;
import com.agriflux.agrifluxweb.service.DashboardServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;
/**
 * Controller per la gestione, comunicazione e recupero dati tra pagine html
 */
@Controller
public class DashboardController {
	
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
	    return "fragments/dashboard :: dashboardPage";
	}
	
	@GetMapping("/coltura")
	public String getColtureDataModel(Model model) {
		List<ColturaDTO> listaColture = dashboardServiceImpl.findAllColturaSortById();
		model.addAttribute("colture", listaColture);

		try {
			model.addAttribute("colturaChartData",
					new ObjectMapper().writeValueAsString(dashboardServiceImpl.countColtureGroupByProdotto()));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return "fragments/coltura :: colturaPage";
	}

	@GetMapping("/ambiente")
	public String getAmbientiDataModel(Model model) {
		model.addAttribute("ambienti", dashboardServiceImpl.findAllAmbienteSortById());
	    return "fragments/ambiente :: ambientePage";
	}

//	@GetMapping("/morfologia")
//	public String getMorfologieDataModel(Model model) {
//		model.addAttribute("morfologie", dashboardServiceImpl.findAllMorfologiaSortById());
//	    return "fragments/morfologia :: morfologiaPage";
//	}

	@GetMapping("/terreno")
	public String getTerreniDataModel(Model model) {
		model.addAttribute("terreni", dashboardServiceImpl.findAllTerrenoSortById());
		model.addAttribute("morfologie", dashboardServiceImpl.findAllMorfologiaSortById());
	    return "fragments/terreno :: terrenoPage";
	}

	@GetMapping("/produzione")
	public String getProduzioniDataModel(Model model) {
		model.addAttribute("produzioni", dashboardServiceImpl.findAllProduzioneSortById());
	    return "fragments/produzione :: produzionePage";
	}
}
