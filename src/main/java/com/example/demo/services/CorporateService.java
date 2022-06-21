package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.CorporateDTO;

public interface CorporateService {

	List<CorporateDTO> findAll();
	
	CorporateDTO findByID (Long id);
	
	CorporateDTO createCorporate (CorporateDTO corporateDto);
	
	CorporateDTO updateCorporate (CorporateDTO corporateDto, Long id);
	
	void deleteCorporate (Long id);
	
}
