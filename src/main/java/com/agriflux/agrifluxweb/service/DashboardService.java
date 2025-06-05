package com.agriflux.agrifluxweb.service;

import org.springframework.ui.Model;

public interface DashboardService {
	
	String getAllColtureSortById(Model model);
	
	String getAllAmbienteSortById(Model model);
	
	String getAllMorfologieSortById(Model model);
	
	String getAllTerreniSortById(Model model);
	
	String getAllProduzioniSortById(Model model);
}
