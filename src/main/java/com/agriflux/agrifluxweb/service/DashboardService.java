package com.agriflux.agrifluxweb.service;

import java.util.List;
import java.util.Map;

import com.agriflux.agrifluxshared.dto.AmbienteDTO;
import com.agriflux.agrifluxshared.dto.ColturaDTO;
import com.agriflux.agrifluxshared.dto.MorfologiaDTO;
import com.agriflux.agrifluxshared.dto.ProduzioneDTO;
import com.agriflux.agrifluxshared.dto.TerrenoDTO;

/**
 * Interfaccia che mostra il contratto che deve rispettare il FE per la visualizzazione dei dati
 * nelle datatables e nei chart
 */
public interface DashboardService {
	
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
	 * Metodo che torna la lista di dati Morfologici ordinati in modo ASCENDENTE tramite l'ID
	 * 
	 * @return List di MorfologiaDTO
	 */
	List<MorfologiaDTO> findAllMorfologiaSortById();
	
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
	
	/**
	 * Metodo che torna una mappa contenente in chiave il nome del prodotto coltivato e 
	 * per valore il numero di volte in cui è stato coltivato
	 * 
	 * @return Mappa key: Prodotto; value: 
	 */
	Map<String, Long> countColtureGroupByProdotto();
}
