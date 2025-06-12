package com.agriflux.agrifluxweb.service;

import java.util.Map;

import com.agriflux.agrifluxshared.dto.ColturaListPrezzoDataRaccoltoDTO;
import com.agriflux.agrifluxshared.dto.ProduzioneColturaDTO;

/**
 * Interfaccia che mostra il contratto che deve rispettare il FE per la visualizzazione dei dati
 * nei grafici
 */
public interface DataChartService {
	
	/**
	 * Metodo che restituisce una mappa contenente in chiave il nome del prodotto coltivato e 
	 * per valore il numero di volte in cui è stato coltivato
	 * 
	 * @return Mappa key: Prodotto; value: 
	 */
	Map<String, Long> countColtureGroupByProdotto();
	
	/**
	 * Metodo che restituisce una mappa con in chiave la tipologia di prodotto e come valore un oggetto
	 * che ha le liste di prezzi e date raccolto relative a quel determinato prodotto
	 * 
	 * @return Map di ColturaListPrezzoDataRaccoltoDTO
	 */
	Map<String, ColturaListPrezzoDataRaccoltoDTO> findPrezziAndDateRaccoltoColtura();
	
	/**
	 * Metodo che restituisce una mappa con chiave la tipologia di prodotto e come valore un'altra mappa con chiave
	 * l'anno di riferimento e come valore un oggetto contenente i dati sulla quantita' del raccolto ed il fatturato
	 * relativo a quell'anno
	 * 
	 * @return Map -> key : Prodotto | value : Map -> key : Anno Riferimento | value : {@code ProduzioneColturaDTO}
	 */
	Map<String, Map<String, ProduzioneColturaDTO>> findColtureJoinProduzione();
}
