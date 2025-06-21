package com.agriflux.agrifluxweb.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.agriflux.agrifluxshared.dto.ambiente.AmbienteDTO;
import com.agriflux.agrifluxshared.dto.coltura.ColturaDTO;
import com.agriflux.agrifluxshared.dto.coltura.ColturaListPrezzoDataRaccoltoDTO;
import com.agriflux.agrifluxshared.dto.produzione.ProduzioneColturaDTO;
import com.agriflux.agrifluxshared.dto.produzione.ProduzioneColturaTempiDTO;
import com.agriflux.agrifluxshared.dto.produzione.ProduzioneDTO;
import com.agriflux.agrifluxshared.dto.produzione.ProduzioneMorfologiaColturaDTO;
import com.agriflux.agrifluxshared.dto.terreno.TerrenoDTO;
import com.agriflux.agrifluxshared.dto.terreno.TerrenoMorfologiaColturaDTO;
import com.agriflux.agrifluxweb.service.coltura.DatiColturaClientServiceImpl;

@Service
public class DashboardServiceImpl implements DashboardService {
	
	private final AgrifluxClientServiceImpl agrifluxServiceImpl;
	private final DatiColturaClientServiceImpl datiColturaService;
	
	public DashboardServiceImpl(AgrifluxClientServiceImpl agrifluxServiceImpl, DatiColturaClientServiceImpl datiColturaService) {
		this.agrifluxServiceImpl = agrifluxServiceImpl;
		this.datiColturaService = datiColturaService;
	}

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

	@Override
	public List<AmbienteDTO> findAllAmbienteSortById() {
		return agrifluxServiceImpl.findAllAmbienteSortById();
	}

	@Override
	public List<TerrenoDTO> findAllTerrenoSortById() {
		return agrifluxServiceImpl.findAllTerrenoSortById();
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
