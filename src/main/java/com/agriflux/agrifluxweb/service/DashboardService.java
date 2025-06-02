package com.agriflux.agrifluxweb.service;

import java.util.List;

import com.agriflux.agrifluxweb.model.ColturaDTO;

public interface DashboardService {
	
	/**
	 * Metodo che torna la lista di Colture ordinate in modo ASCENDENTE tramite l'ID
	 * 
	 * @return List di ColtureDTO
	 */
	List<ColturaDTO> findAllColtureSortById();
}
