package com.agriflux.agrifluxweb.service;

import java.util.Map;

/**
 * Interfaccia che mostra il contratto che deve rispettare il FE per la visualizzazione dei dati
 * nei grafici
 */
public interface DataChartService {
	
	/**
	 * Metodo che torna una mappa contenente in chiave il nome del prodotto coltivato e 
	 * per valore il numero di volte in cui è stato coltivato
	 * 
	 * @return Mappa key: Prodotto; value: 
	 */
	Map<String, Long> countColtureGroupByProdotto();
}
