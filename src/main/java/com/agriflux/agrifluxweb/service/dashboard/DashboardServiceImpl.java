package com.agriflux.agrifluxweb.service.dashboard;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.agriflux.agrifluxshared.dto.ambiente.AmbienteDTO;
import com.agriflux.agrifluxshared.dto.coltura.ColturaDTO;
import com.agriflux.agrifluxshared.dto.coltura.ColturaListPrezzoDataRaccoltoDTO;
import com.agriflux.agrifluxshared.dto.particella.DatiParticellaDTO;
import com.agriflux.agrifluxshared.dto.produzione.ProduzioneColturaDTO;
import com.agriflux.agrifluxshared.dto.produzione.ProduzioneColturaTempiDTO;
import com.agriflux.agrifluxshared.dto.produzione.ProduzioneDTO;
import com.agriflux.agrifluxshared.dto.produzione.ProduzioneMorfologiaColturaDTO;
import com.agriflux.agrifluxshared.dto.terreno.TerrenoDTO;
import com.agriflux.agrifluxshared.dto.terreno.TerrenoMorfologiaColturaDTO;
import com.agriflux.agrifluxweb.service.AgrifluxClientServiceImpl;
import com.agriflux.agrifluxweb.service.DatiRilevazioneTerrenoClientServiceImpl;
import com.agriflux.agrifluxweb.service.coltura.DatiColturaClientServiceImpl;
import com.agriflux.agrifluxweb.service.particella.DatiParticellaClientServiceImpl;

@Service
public class DashboardServiceImpl implements DashboardService {
	
	private final AgrifluxClientServiceImpl agrifluxServiceImpl;
	private final DatiColturaClientServiceImpl datiColturaService;
	private final DatiParticellaClientServiceImpl datiParticellaService;
	private final DatiRilevazioneTerrenoClientServiceImpl datiRilevazioneTerrenoService;
	
	public DashboardServiceImpl(AgrifluxClientServiceImpl agrifluxServiceImpl,
			DatiColturaClientServiceImpl datiColturaService, DatiParticellaClientServiceImpl datiParticellaService,
			DatiRilevazioneTerrenoClientServiceImpl datiRilevazioneTerrenoService) {
		this.agrifluxServiceImpl = agrifluxServiceImpl;
		this.datiColturaService = datiColturaService;
		this.datiParticellaService = datiParticellaService;
		this.datiRilevazioneTerrenoService = datiRilevazioneTerrenoService;
	}
	
	//PARTICELLA
	
	@Override
	public List<DatiParticellaDTO> findAllParticellaSortById() {
		return datiParticellaService.findAllParticellaSortById();
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
	
	

	@Override
	public List<AmbienteDTO> findAllAmbienteSortById() {
		return agrifluxServiceImpl.findAllAmbienteSortById();
	}

	@Override
	public List<ProduzioneDTO> findAllProduzioneSortById() {
		return agrifluxServiceImpl.findAllProduzioneSortById();
	}

	
//	@Override
//	public Map<String, Long> countColtureGroupByProdotto() {
//
//		Map<String, Long> chartData = new HashMap<>();
//		List<ColturaGroupByProdottoDTO> countColtureGroupByProdotto = agrifluxServiceImpl.countColtureGroupByProdotto();
//
//		for (ColturaGroupByProdottoDTO colturaGroupByProdottoDTO : countColtureGroupByProdotto) {
//			chartData.put(colturaGroupByProdottoDTO.getProdottoColtivato(), colturaGroupByProdottoDTO.getCount());
//		}
//		
//		return chartData;
//	}

	@Override
	public Map<String, Map<String, ProduzioneColturaDTO>> findColtureJoinProduzione() {
		return agrifluxServiceImpl.findColtureJoinProduzione();
	}

	@Override
	public Map<String, List<ProduzioneColturaTempiDTO>> findProduzioneTempiJoinColtura() {
		return agrifluxServiceImpl.findProduzioneTempiJoinColtura();
	}

	@Override
	public Map<Long, ProduzioneMorfologiaColturaDTO> findProduzioneJoinColturaMorfologia() {
		return agrifluxServiceImpl.findProduzioneJoinColturaMorfologia();
	}

	@Override
	public Map<Long, List<TerrenoMorfologiaColturaDTO>> findTerrenoJoinColturaMorfologia() {
		return agrifluxServiceImpl.findTerrenoJoinColturaMorfologia();
	}

}
