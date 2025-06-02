package com.agriflux.agrifluxweb.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.agriflux.agrifluxshared.dto.AmbienteDTO;
import com.agriflux.agrifluxshared.dto.ColturaDTO;
import com.agriflux.agrifluxshared.dto.MorfologiaDTO;
import com.agriflux.agrifluxshared.dto.ProduzioneDTO;
import com.agriflux.agrifluxshared.dto.TerrenoDTO;
import com.agriflux.agrifluxshared.service.AgrifluxDataService;
import com.agriflux.agrifluxweb.service.DashboardServiceImpl;

@Controller
public class DashboardController implements AgrifluxDataService {
	
	//TODO Controller utilizzato da THYMELEAF per la gestione e comunicazione tra le pagine html
	
	private final DashboardServiceImpl dashboardServiceImpl;
	
	public DashboardController(DashboardServiceImpl dashboardService) {
		this.dashboardServiceImpl = dashboardService;
	}
	
	@GetMapping("/")
	public String dashboard(Model model) {
		model.addAttribute("username", "Admin");
	    return "dashboard";
	}
	
	@Override
	@ModelAttribute(name = "colture")
	public List<ColturaDTO> findAllColturaSortById(){
		return dashboardServiceImpl.findAllColturaSortById();
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
