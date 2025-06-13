package com.agriflux.agrifluxweb.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agriflux.agrifluxshared.dto.ColturaDTO;
import com.agriflux.agrifluxshared.dto.ColturaListPrezzoDataRaccoltoDTO;
import com.agriflux.agrifluxshared.dto.ProduzioneColturaDTO;
import com.agriflux.agrifluxshared.dto.ProduzioneColturaTempiDTO;
import com.agriflux.agrifluxweb.service.DashboardServiceImpl;
import com.agriflux.agrifluxweb.service.DataChartService;

import jakarta.servlet.http.HttpSession;

/**
 * Controller per la gestione, comunicazione e recupero dati tra pagine html
 */
@Controller
public class DashboardController implements DataChartService{
	
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

	@Override
	@GetMapping("/countColtureGroupByProdotto")
	@ResponseBody
	public Map<String, Long> countColtureGroupByProdotto() {
		return dashboardServiceImpl.countColtureGroupByProdotto();
	}

	@Override
	@GetMapping("/findPrezziAndDateRaccoltoColtura")
	@ResponseBody
	public Map<String, ColturaListPrezzoDataRaccoltoDTO> findPrezziAndDateRaccoltoColtura() {
		return dashboardServiceImpl.findPrezziAndDateRaccoltoColtura();
	}

	@Override
	@GetMapping("/findColtureJoinProduzione")
	@ResponseBody
	public Map<String, Map<String, ProduzioneColturaDTO>> findColtureJoinProduzione() {
		return dashboardServiceImpl.findColtureJoinProduzione();
	}

	@Override
	@GetMapping("/findProduzioneTempiJoinColtura")
	@ResponseBody
	public Map<String, List<ProduzioneColturaTempiDTO>> findProduzioneTempiJoinColtura() {
		return dashboardServiceImpl.findProduzioneTempiJoinColtura();
	}
	
}
