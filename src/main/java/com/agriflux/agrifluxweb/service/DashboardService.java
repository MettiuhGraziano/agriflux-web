package com.agriflux.agrifluxweb.service;

import java.util.List;

import com.agriflux.agrifluxshared.dto.ambiente.AmbienteDTO;
import com.agriflux.agrifluxshared.dto.coltura.ColturaDTO;
import com.agriflux.agrifluxshared.dto.produzione.ProduzioneDTO;
import com.agriflux.agrifluxshared.dto.terreno.TerrenoDTO;

/**
 * Interfaccia che mostra il contratto che deve rispettare il FE per la visualizzazione dei dati
 * nelle datatables e nei chart
 */
public interface DashboardService extends DataChartService{
	
	/**
	 * Metodo che torna la lista di Colture ordinate in modo ASCENDENTE tramite l'ID
	 * 
	 * @return List di ColtureDTO
	 */
	List<ColturaDTO> findAllColturaSortById();
	
	
	
	
	
	
	/**
	 * Metodo che torna la lista di dati Ambientali ordinati in modo ASCENDENTE tramite l'ID
	 * 
	 * @return List di AmbienteDTO
	 */
	List<AmbienteDTO> findAllAmbienteSortById();
	
	/**
	 * Metodo che torna la lista di Terreni ordinati in modo ASCENDENTE tramite l'ID
	 * 
	 * @return List di TerrenoDTO
	 */
	List<TerrenoDTO> findAllTerrenoSortById();
	
	/**
	 * Metodo che torna la lista di dati di Produzione ordinati in modo ASCENDENTE tramite l'ID
	 * 
	 * @return List di ProduzioneDTO
	 */
	List<ProduzioneDTO> findAllProduzioneSortById();
	
}
