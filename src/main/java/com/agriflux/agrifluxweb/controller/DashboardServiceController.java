package com.agriflux.agrifluxweb.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agriflux.agrifluxshared.dto.ambiente.AmbienteDTO;
import com.agriflux.agrifluxshared.dto.coltura.ColturaDTO;
import com.agriflux.agrifluxshared.dto.coltura.ColturaListPrezzoDataRaccoltoDTO;
import com.agriflux.agrifluxshared.dto.particella.DatiParticellaDTO;
import com.agriflux.agrifluxshared.dto.produzione.ProduzioneColturaDTO;
import com.agriflux.agrifluxshared.dto.produzione.ProduzioneColturaTempiDTO;
import com.agriflux.agrifluxshared.dto.produzione.ProduzioneDTO;
import com.agriflux.agrifluxshared.dto.produzione.ProduzioneMorfologiaColturaDTO;
import com.agriflux.agrifluxshared.dto.terreno.ParticellaColturaTerrenoDTO;
import com.agriflux.agrifluxshared.dto.terreno.TerrenoDTO;
import com.agriflux.agrifluxweb.service.dashboard.DashboardService;
import com.agriflux.agrifluxweb.service.dashboard.DashboardServiceImpl;

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
	
	//PARTICELLA
	
	@Override
	@GetMapping("/particelleApi")
	@Operation(summary = "Recupera tutte le Particelle", description = "Restituisce una lista di Particelle")
	public List<DatiParticellaDTO> findAllParticellaSortById() {
		return dashboardServiceImpl.findAllParticellaSortById();
	}
	
	@Override
	@GetMapping("/findParticellaJoinColturaTerrenoApi")
	@Operation(summary = "Recupera alcuni dati relativi a Coltura e Rilevazione Terreno raggruppandoli per Particella", description = "Restituisce una mappa che ha come chiave l'id della Particella"
			+ " e come valore una lista di DTO che contengono l'id della Coltura, il prodotto coltivato e la lista di date rilevazioni associate a quella Coltura su quella Particella")
	public Map<Long, List<ParticellaColturaTerrenoDTO>> findParticellaJoinColturaTerreno() {
		return dashboardServiceImpl.findParticellaJoinColturaTerreno();
	}
	
	//COLTURA
	
	@Override
	@GetMapping("/coltureApi")
	@Operation(summary = "Recupera tutte le Colture coltivate", description = "Restituisce una lista di colture")
	public List<ColturaDTO> findAllColturaSortById(){
		return dashboardServiceImpl.findAllColturaSortById();
	}
	
	@Override
	@GetMapping("/countOrtaggioColturaApi")
	@Operation(summary = "Conta le Colture raggruppate per nome Ortaggio", description = "Restituisce una mappa in cui la chiave è il tipo di Ortaggio e il valore è il numero di occorrenze trovate")
	public Map<String, Long> countOrtaggioColtura() {
		return dashboardServiceImpl.countOrtaggioColtura();
	}

	@Override
	@GetMapping("/countFamigliaOrtaggioColturaApi")
	@Operation(summary = "Conta le Colture raggruppate per Famiglia di Ortaggio", description = "Restituisce una mappa in cui la chiave è la Famiglia associata a quell'Ortaggio e il valore è il numero di occorrenze trovate")
	public Map<String, Long> countFamigliaOrtaggioColtura() {
		return dashboardServiceImpl.countFamigliaOrtaggioColtura();
	}
	
	@Override
	@GetMapping("/getPrezziAndDateRaccoltoColturaApi")
	@Operation(summary = "Mostra la lista di prezzi e date raccolto relative alle singole Colture", description = "Restituisce una mappa in cui la chiave è la Coltura e il valore è un oggetto contenente la lista di Date Raccolto e Prezzo al Kg relativi a quella Coltura")
	public Map<String, ColturaListPrezzoDataRaccoltoDTO> findPrezziAndDateRaccoltoColtura() {
		return dashboardServiceImpl.findPrezziAndDateRaccoltoColtura();
	}
	
	//RILEVAZIONE TERRENO
	
	@Override
	@GetMapping("/rilevazioniTerrenoApi")
	@Operation(summary = "Recupera tutte le Rilevazioni effettuate sulle Particelle", description = "Restituisce una lista di Rilevazioni del Terreno")
	public List<TerrenoDTO> findAllRilevazioneTerrenoSortById() {
		return dashboardServiceImpl.findAllRilevazioneTerrenoSortById();
	}
	
	//PRODUZIONE

	@Override
	@GetMapping("/produzioneApi")
	public List<ProduzioneDTO> findAllProduzioneSortById() {
		return dashboardServiceImpl.findAllProduzioneSortById();
	}
	
	@Override
	@GetMapping("/getProduzioneQuantitaJoinColtura")
	public Map<String, Map<String, ProduzioneColturaDTO>> findProduzioneQuantitaJoinColtura() {
		return dashboardServiceImpl.findProduzioneQuantitaJoinColtura();
	}
	
	@Override
	@GetMapping("/getProduzioneTempiJoinColtura")
	public Map<String, List<ProduzioneColturaTempiDTO>> findProduzioneTempiJoinColtura() {
		return dashboardServiceImpl.findProduzioneTempiJoinColtura();
	}

	@Override
	@GetMapping("/getProduzioneJoinColturaMorfologia")
	public Map<Long, ProduzioneMorfologiaColturaDTO> findProduzioneJoinColturaMorfologia() {
		return dashboardServiceImpl.findProduzioneJoinColturaMorfologia();
	}
	
	//AMBIENTE
	
	@Override
	@GetMapping("/datiAmbientali")
	public List<AmbienteDTO> findAllAmbienteSortById() {
		return dashboardServiceImpl.findAllAmbienteSortById();
	}

}
