package com.agriflux.agrifluxweb.service;

import java.util.List;

import com.agriflux.agrifluxweb.model.ColturaDTO;

public interface DashboardService {
	
	List<ColturaDTO> findAllColtureSortById();
}
