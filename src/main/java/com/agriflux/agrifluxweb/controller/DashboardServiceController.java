package com.agriflux.agrifluxweb.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agriflux.agrifluxshared.dto.ambiente.AmbienteDTO;
import com.agriflux.agrifluxshared.dto.ambiente.VariazioneValoriParametriAmbienteDTO;
import com.agriflux.agrifluxshared.dto.azienda.AziendaDTO;
import com.agriflux.agrifluxshared.dto.coltura.ColturaDTO;
import com.agriflux.agrifluxshared.dto.coltura.ColturaListPrezzoDataRaccoltoDTO;
import com.agriflux.agrifluxshared.dto.fatturato.FatturatoDTO;
import com.agriflux.agrifluxshared.dto.particella.DatiParticellaDTO;
import com.agriflux.agrifluxshared.dto.produzione.ProduzioneColturaDTO;
import com.agriflux.agrifluxshared.dto.produzione.ProduzioneColturaTempiDTO;
import com.agriflux.agrifluxshared.dto.produzione.ProduzioneDTO;
import com.agriflux.agrifluxshared.dto.produzione.ProduzioneParticellaColturaOrtaggioDTO;
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
	@Operation(summary = "Recupera tutti i dati relativi alle Produzioni", description = "Restituisce una lista di Produzioni")
	public List<ProduzioneDTO> findAllProduzioneSortById() {
		return dashboardServiceImpl.findAllProduzioneSortById();
	}
	
	@Override
	@GetMapping("/getProduzioneQuantitaJoinColtura")
	@Operation(summary = "Mostra una mappa delle quantita' di raccolto prodotte e del fatturato raggruppate per nome ortaggio e anno di riferimento", description = "Restituisce una mappa in cui la chiave è il nome dell'Ortaggio e il valore è un mappa "
			+ " con in chiave l'anno di riferimento e come valore un oggetto contenente le quantità di raccolto prodotte ed il fatturato")
	public Map<String, Map<String, ProduzioneColturaDTO>> findProduzioneQuantitaJoinColtura() {
		return dashboardServiceImpl.findProduzioneQuantitaJoinColtura();
	}
	
	@Override
	@GetMapping("/getProduzioneJoinColturaTempi")
	@Operation(summary = "Mostra una mappa della lista dei tempi medi da semina a raccolto delle singole Colture per anno di riferimento", description = "Restituisce una mappa in cui la chiave è l'anno di riferimento e il valore è una lista "
			+ " di oggetti contenenti il prodotto coltivato e la lista delle medie dei tempi da semina a raccolto")
	public Map<String, List<ProduzioneColturaTempiDTO>> findProduzioneJoinColturaTempi() {
		return dashboardServiceImpl.findProduzioneJoinColturaTempi();
	}
	
	@Override
	@GetMapping("/getProduzioneParticellaColturaOrtaggio")
	@Operation(summary = "Mostra una mappa dei dati relativi alla Particella e al prodotto coltivato raggruppati per Produzione", description = "Restituisce una mappa in cui la chiave è la Produzione e il valore è un oggetto "
			+ " contenente i dati relativi alla Particella e al prodotto coltivato riferito a quella Produzione")
	public Map<Long, ProduzioneParticellaColturaOrtaggioDTO> findProduzioneParticellaColturaOrtaggio() {
		return dashboardServiceImpl.findProduzioneParticellaColturaOrtaggio();
	}

	//AMBIENTE
	
	@Override
	@GetMapping("/ambienteApi")
	@Operation(summary = "Recupera tutti i dati Ambientali", description = "Restituisce una lista di dati Ambientali")
	public List<AmbienteDTO> findAllAmbienteSortById() {
		return dashboardServiceImpl.findAllAmbienteSortById();
	}

	@Override
	@GetMapping("/findListaParametriAmbientali")
	@Operation(summary = "Recupera la lista dei parametri Ambientali", description = "Restituisce la lista dei nomi dei parametri Ambientali calcolati")
	public List<String> getListaParametriAmbiente() {
		return dashboardServiceImpl.getListaParametriAmbiente();
	}

	@Override
	@GetMapping("/findVariazioneValoriParametriAmbiente")
	@Operation(summary = "Mostra una mappa con la variazione dei parametri ambientali nel corso del tempo", description = "Restituisce una mappa con in chiave il nome del parametro ambientale e come valore"
			+ " una lista di oggetti contenenti il valore del parametro, la data di rilevazione ambientale e la variazione percentuale rispetto all'anno precedente")
	public Map<String, List<VariazioneValoriParametriAmbienteDTO>> getVariazioneValoriParametriAmbiente() {
		return dashboardServiceImpl.getVariazioneValoriParametriAmbiente();
	}

	@Override
	@GetMapping("/aziendaApi")
	@Operation(summary = "Recupera i dati Aziendali", description = "Restituisce un DTO contenente i dati Aziendali")
	public AziendaDTO findAzienda() {
		return dashboardServiceImpl.findAzienda();
	}
	
	//FATTURATO
	
	@Override
	@GetMapping("/fatturatoApi")
	@Operation(summary = "Recupera i dati relativi al Fatturato", description = "Restituisce una lista di DTO contenenti i dati relativi al Fatturato")
	public List<FatturatoDTO> findAllFatturatoSortById() {
		return dashboardServiceImpl.findAllFatturatoSortById();
	}

}
