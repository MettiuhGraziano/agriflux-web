package com.agriflux.agrifluxweb.service.dashboard;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.agriflux.agrifluxshared.dto.ambiente.AmbienteDTO;
import com.agriflux.agrifluxshared.dto.ambiente.VariazioneValoriParametriAmbienteDTO;
import com.agriflux.agrifluxshared.dto.azienda.AziendaDTO;
import com.agriflux.agrifluxshared.dto.coltura.ColturaDTO;
import com.agriflux.agrifluxshared.dto.coltura.ColturaListPrezzoDataRaccoltoDTO;
import com.agriflux.agrifluxshared.dto.ortaggio.OrtaggioDTO;
import com.agriflux.agrifluxshared.dto.particella.DatiParticellaDTO;
import com.agriflux.agrifluxshared.dto.produzione.ProduzioneColturaDTO;
import com.agriflux.agrifluxshared.dto.produzione.ProduzioneColturaTempiDTO;
import com.agriflux.agrifluxshared.dto.produzione.ProduzioneDTO;
import com.agriflux.agrifluxshared.dto.produzione.ProduzioneParticellaColturaOrtaggioDTO;
import com.agriflux.agrifluxshared.dto.terreno.ParticellaColturaTerrenoDTO;
import com.agriflux.agrifluxshared.dto.terreno.TerrenoDTO;
import com.agriflux.agrifluxweb.service.ambiente.DatiAmbienteClientServiceImpl;
import com.agriflux.agrifluxweb.service.azienda.DatiAziendaClientServiceImpl;
import com.agriflux.agrifluxweb.service.coltura.DatiColturaClientServiceImpl;
import com.agriflux.agrifluxweb.service.ortaggio.DatiOrtaggioClientServiceImpl;
import com.agriflux.agrifluxweb.service.particella.DatiParticellaClientServiceImpl;
import com.agriflux.agrifluxweb.service.produzione.DatiProduzioneClientServiceImpl;
import com.agriflux.agrifluxweb.service.terreno.DatiRilevazioneTerrenoClientServiceImpl;

@Service
public class DashboardServiceImpl implements DashboardService {
	
	private final DatiColturaClientServiceImpl datiColturaService;
	private final DatiParticellaClientServiceImpl datiParticellaService;
	private final DatiRilevazioneTerrenoClientServiceImpl datiRilevazioneTerrenoService;
	private final DatiProduzioneClientServiceImpl datiProduzioneService;
	private final DatiOrtaggioClientServiceImpl datiOrtaggioService;
	private final DatiAmbienteClientServiceImpl datiAmbienteService;
	private final DatiAziendaClientServiceImpl datiAziendaService;

	public DashboardServiceImpl(DatiColturaClientServiceImpl datiColturaService,
			DatiParticellaClientServiceImpl datiParticellaService,
			DatiRilevazioneTerrenoClientServiceImpl datiRilevazioneTerrenoService,
			DatiProduzioneClientServiceImpl datiProduzioneService, DatiOrtaggioClientServiceImpl datiOrtaggioService,
			DatiAmbienteClientServiceImpl datiAmbienteClientService, DatiAziendaClientServiceImpl datiAziendaService) {
		this.datiColturaService = datiColturaService;
		this.datiParticellaService = datiParticellaService;
		this.datiRilevazioneTerrenoService = datiRilevazioneTerrenoService;
		this.datiProduzioneService = datiProduzioneService;
		this.datiOrtaggioService = datiOrtaggioService;
		this.datiAmbienteService = datiAmbienteClientService;
		this.datiAziendaService = datiAziendaService;
	}
	
	//PARTICELLA
	
	@Override
	public List<DatiParticellaDTO> findAllParticellaSortById() {
		return datiParticellaService.findAllParticellaSortById();
	}
	
	@Override
	public Map<Long, List<ParticellaColturaTerrenoDTO>> findParticellaJoinColturaTerreno() {
		return datiParticellaService.findParticellaJoinColturaTerreno();
	}

	//COLTURA
	
	@Override
	public List<ColturaDTO> findAllColturaSortById() {
		return datiColturaService.findAllColturaSortById();
	}
	
	@Override
	public Map<String, Long> countOrtaggioColtura() {
		return datiColturaService.countOrtaggioColtura();
	}

	@Override
	public Map<String, Long> countFamigliaOrtaggioColtura() {
		return datiColturaService.countFamigliaOrtaggioColtura();
	}
	
	@Override
	public Map<String, ColturaListPrezzoDataRaccoltoDTO> findPrezziAndDateRaccoltoColtura() {
		return datiColturaService.findPrezziAndDateColtura();
	}
	
	//RILEVAZIONE TERRENO
	
	@Override
	public List<TerrenoDTO> findAllRilevazioneTerrenoSortById() {
		return datiRilevazioneTerrenoService.findAllRilevazioneTerrenoSortById();
	}
	
	//PRODUZIONE
	
	@Override
	public List<ProduzioneDTO> findAllProduzioneSortById() {
		return datiProduzioneService.findAllProduzioneSortById();
	}
	
	@Override
	public Map<String, Map<String, ProduzioneColturaDTO>> findProduzioneQuantitaJoinColtura() {
		return datiProduzioneService.findProduzioneQuantitaJoinColtura();
	}
	
	@Override
	public Map<String, List<ProduzioneColturaTempiDTO>> findProduzioneJoinColturaTempi() {
		return datiProduzioneService.findProduzioneJoinColturaTempi();
	}
	
	@Override
	public Map<Long, ProduzioneParticellaColturaOrtaggioDTO> findProduzioneParticellaColturaOrtaggio() {
		return datiProduzioneService.findProduzioneParticellaColturaOrtaggio();
	}

	//AMBIENTE
	
	@Override
	public List<AmbienteDTO> findAllAmbienteSortById() {
		return datiAmbienteService.findAllAmbienteSortById();
	}
	
	@Override
	public List<String> getListaParametriAmbiente() {
		return datiAmbienteService.getListaParametriAmbiente();
	}
	
	@Override
	public Map<String, List<VariazioneValoriParametriAmbienteDTO>> getVariazioneValoriParametriAmbiente() {
		return datiAmbienteService.getVariazioneValoriParametriAmbiente();
	}
	
	//ORTAGGIO

	public List<OrtaggioDTO> findAllOrtaggioSortById() {
		return datiOrtaggioService.findAllOrtaggioSortById();
	}

	@Override
	public AziendaDTO findAzienda() {
		return datiAziendaService.findAzienda();
	}

}
