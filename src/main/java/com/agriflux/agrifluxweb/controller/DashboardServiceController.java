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

/**
 * Client API servizi Rest esposti da Agriflux-Batch
 */
@RestController("api/dashboard")
public class DashboardServiceController implements AgrifluxDataService {
	
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
		return dashboardServiceImpl.findAllAmbienteSortById();
	}

	@Override
	@GetMapping("/datiMorfologici")
	public List<MorfologiaDTO> findAllMorfologiaSortById() {
		return dashboardServiceImpl.findAllMorfologiaSortById();
	}

	@Override
	@GetMapping("/datiTerreni")
	public List<TerrenoDTO> findAllTerrenoSortById() {
		return dashboardServiceImpl.findAllTerrenoSortById();
	}

	@Override
	@GetMapping("/datiProduzione")
	public List<ProduzioneDTO> findAllProduzioneSortById() {
		return dashboardServiceImpl.findAllProduzioneSortById();
	}
	
}
