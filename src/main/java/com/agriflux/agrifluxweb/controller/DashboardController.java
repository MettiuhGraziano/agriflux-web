package com.agriflux.agrifluxweb.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agriflux.agrifluxshared.dto.coltura.ColturaDTO;
import com.agriflux.agrifluxshared.dto.coltura.ColturaListPrezzoDataRaccoltoDTO;
import com.agriflux.agrifluxshared.dto.produzione.ProduzioneColturaDTO;
import com.agriflux.agrifluxshared.dto.produzione.ProduzioneColturaTempiDTO;
import com.agriflux.agrifluxshared.dto.produzione.ProduzioneMorfologiaColturaDTO;
import com.agriflux.agrifluxshared.dto.terreno.ParticellaColturaTerrenoDTO;
import com.agriflux.agrifluxweb.service.dashboard.DashboardServiceImpl;
import com.agriflux.agrifluxweb.service.dashboard.DataChartService;

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
	
	//COLTURA
	
	@GetMapping("/coltura")
	public String getColtureDataModel(Model model) {
		List<ColturaDTO> listaColture = dashboardServiceImpl.findAllColturaSortById();
		model.addAttribute("colture", listaColture);

		return "fragments/coltura :: colturaPage";
	}
	
	@Override
	@GetMapping("/countOrtaggioColtura")
	@ResponseBody
	public Map<String, Long> countOrtaggioColtura() {
		return dashboardServiceImpl.countOrtaggioColtura();
	}

	@Override
	@GetMapping("/countFamigliaOrtaggioColtura")
	@ResponseBody
	public Map<String, Long> countFamigliaOrtaggioColtura() {
		return dashboardServiceImpl.countFamigliaOrtaggioColtura();
	}
	
	@Override
	@GetMapping("/findPrezziAndDateRaccoltoColtura")
	@ResponseBody
	public Map<String, ColturaListPrezzoDataRaccoltoDTO> findPrezziAndDateRaccoltoColtura() {
		return dashboardServiceImpl.findPrezziAndDateRaccoltoColtura();
	}
	
	//PARTICELLA

	@GetMapping("/terreno")
	public String getParticelleDataModel(Model model) {
		model.addAttribute("particelle", dashboardServiceImpl.findAllParticellaSortById());
		model.addAttribute("rilevazioniTerreno", dashboardServiceImpl.findAllRilevazioneTerrenoSortById());
	    return "fragments/terreno :: terrenoPage";
	}
	
	@Override
	@GetMapping("/findParticellaJoinColturaTerreno")
	@ResponseBody
	public Map<Long, List<ParticellaColturaTerrenoDTO>> findParticellaJoinColturaTerreno() {
		return dashboardServiceImpl.findParticellaJoinColturaTerreno();
	}
	
	//PRODUZIONE

	@GetMapping("/produzione")
	public String getProduzioniDataModel(Model model) {
		model.addAttribute("produzioni", dashboardServiceImpl.findAllProduzioneSortById());
	    return "fragments/produzione :: produzionePage";
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

	@Override
	@GetMapping("/findProduzioneJoinColturaMorfologia")
	@ResponseBody
	public Map<Long, ProduzioneMorfologiaColturaDTO> findProduzioneJoinColturaMorfologia() {
		return dashboardServiceImpl.findProduzioneJoinColturaMorfologia();
	}
	
	//AMBIENTE
	
	@GetMapping("/ambiente")
	public String getAmbientiDataModel(Model model) {
		model.addAttribute("ambienti", dashboardServiceImpl.findAllAmbienteSortById());
	    return "fragments/ambiente :: ambientePage";
	}

//	@GetMapping("/findListaProdottiColtivati")
//	@ResponseBody
//	public List<String> findListProdottiColture() {
//		
//		List<String> prodottiColtivati = new ArrayList<String>();
//		for (TipoProdottoEnum prodotto : TipoProdottoEnum.values()) {
//			prodottiColtivati.add(prodotto.name());
//		}
//		
//		return prodottiColtivati;
//	}

}
