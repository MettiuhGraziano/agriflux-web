package com.agriflux.agrifluxweb.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.agriflux.agrifluxshared.dto.AmbienteDTO;
import com.agriflux.agrifluxshared.dto.ColturaDTO;
import com.agriflux.agrifluxshared.dto.ColturaGroupByProdottoDTO;
import com.agriflux.agrifluxshared.dto.ColturaListPrezzoDataRaccoltoDTO;
import com.agriflux.agrifluxshared.dto.MorfologiaDTO;
import com.agriflux.agrifluxshared.dto.ProduzioneColturaDTO;
import com.agriflux.agrifluxshared.dto.ProduzioneColturaTempiDTO;
import com.agriflux.agrifluxshared.dto.ProduzioneDTO;
import com.agriflux.agrifluxshared.dto.ProduzioneMorfologiaColturaDTO;
import com.agriflux.agrifluxshared.dto.TerrenoDTO;
import com.agriflux.agrifluxshared.dto.TerrenoMorfologiaColturaDTO;

@Service
public class DashboardServiceImpl implements DashboardService {
	
	private final AgrifluxClientServiceImpl agrifluxServiceImpl;
	
	public DashboardServiceImpl(AgrifluxClientServiceImpl agrifluxServiceImpl) {
		this.agrifluxServiceImpl = agrifluxServiceImpl;
	}

	@Override
	public List<ColturaDTO> findAllColturaSortById() {
		return agrifluxServiceImpl.findAllColturaSortById();
	}

	@Override
	public List<AmbienteDTO> findAllAmbienteSortById() {
		return agrifluxServiceImpl.findAllAmbienteSortById();
	}

	@Override
	public List<MorfologiaDTO> findAllMorfologiaSortById() {
		return agrifluxServiceImpl.findAllMorfologiaSortById();
	}

	@Override
	public List<TerrenoDTO> findAllTerrenoSortById() {
		return agrifluxServiceImpl.findAllTerrenoSortById();
	}

	@Override
	public List<ProduzioneDTO> findAllProduzioneSortById() {
		return agrifluxServiceImpl.findAllProduzioneSortById();
	}

	@Override
	public Map<String, Long> countColtureGroupByProdotto() {

		Map<String, Long> chartData = new HashMap<>();
		List<ColturaGroupByProdottoDTO> countColtureGroupByProdotto = agrifluxServiceImpl.countColtureGroupByProdotto();

		for (ColturaGroupByProdottoDTO colturaGroupByProdottoDTO : countColtureGroupByProdotto) {
			chartData.put(colturaGroupByProdottoDTO.getProdottoColtivato(), colturaGroupByProdottoDTO.getCount());
		}
		
		return chartData;
	}

	@Override
	public Map<String, ColturaListPrezzoDataRaccoltoDTO> findPrezziAndDateRaccoltoColtura() {
		return agrifluxServiceImpl.findPrezziAndDateColtura();
	}

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
