package com.agriflux.agrifluxweb.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agriflux.agrifluxshared.dto.AmbienteDTO;
import com.agriflux.agrifluxshared.dto.ColturaDTO;
import com.agriflux.agrifluxshared.dto.MorfologiaDTO;
import com.agriflux.agrifluxshared.dto.ProduzioneDTO;
import com.agriflux.agrifluxshared.dto.TerrenoDTO;
import com.agriflux.agrifluxshared.service.AgrifluxDataService;
import com.agriflux.agrifluxweb.service.DashboardServiceImpl;

@RestController("api/dashboard")
public class DashboardServiceController implements AgrifluxDataService {
	
	//TODO CREAZIONE CLIENT API SERVIZI REST ESPOSTI DA AGRIFLUX-BATCH
	
	private final DashboardServiceImpl dashboardServiceImpl;
	
	public DashboardServiceController(DashboardServiceImpl dashboardService) {
		this.dashboardServiceImpl = dashboardService;
	}
	
	@Override
	@GetMapping("/datiColture")
	public List<ColturaDTO> findAllColturaSortById(){
		return dashboardServiceImpl.findAllColturaSortById();
	}

	@Override
	@GetMapping("/datiAmbientali")
	public List<AmbienteDTO> findAllAmbienteSortById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@GetMapping("/datiMorfologici")
	public List<MorfologiaDTO> findAllMorfologiaSortById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@GetMapping("/datiTerreni")
	public List<TerrenoDTO> findAllTerrenoSortById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@GetMapping("/datiProduzione")
	public List<ProduzioneDTO> findAllProduzioneSortById() {
		// TODO Auto-generated method stub
		return null;
	}
}
