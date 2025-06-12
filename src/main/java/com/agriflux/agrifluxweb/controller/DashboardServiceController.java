package com.agriflux.agrifluxweb.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agriflux.agrifluxshared.dto.AmbienteDTO;
import com.agriflux.agrifluxshared.dto.ColturaDTO;
import com.agriflux.agrifluxshared.dto.ColturaListPrezzoDataRaccoltoDTO;
import com.agriflux.agrifluxshared.dto.MorfologiaDTO;
import com.agriflux.agrifluxshared.dto.ProduzioneColturaDTO;
import com.agriflux.agrifluxshared.dto.ProduzioneDTO;
import com.agriflux.agrifluxshared.dto.TerrenoDTO;
import com.agriflux.agrifluxweb.service.DashboardService;
import com.agriflux.agrifluxweb.service.DashboardServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Client API servizi Rest esposti da Agriflux-Batch
 */
@RestController("api/batch")
@Tag(name = "Dashboard Service Controller", description = "Client API servizi Rest esposti da Agriflux-Batch per alimentare Tabelle e Grafici")
public class DashboardServiceController implements DashboardService {
	
	private final DashboardServiceImpl dashboardServiceImpl;
	
	public DashboardServiceController(DashboardServiceImpl dashboardServiceImpl) {
		this.dashboardServiceImpl = dashboardServiceImpl;
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

	@Override
	@GetMapping("/getColtureGroupByProdotto")
	@Operation(summary = "Conta le colture raggruppate per tipologia di prodotto", description = "Restituisce una mappa in cui la chiave è il nome della coltura e il valore è il numero di occorrenze trovate")
	public Map<String, Long> countColtureGroupByProdotto() {
		return dashboardServiceImpl.countColtureGroupByProdotto();
	}

	@Override
	@GetMapping("/getPrezziAndDateRaccoltoColtura")
	public Map<String, ColturaListPrezzoDataRaccoltoDTO> findPrezziAndDateRaccoltoColtura() {
		return dashboardServiceImpl.findPrezziAndDateRaccoltoColtura();
	}

	@Override
	@GetMapping("/getColtureJoinProduzione")
	public Map<String, Map<String, ProduzioneColturaDTO>> findColtureJoinProduzione() {
		return dashboardServiceImpl.findColtureJoinProduzione();
	}
	
}
