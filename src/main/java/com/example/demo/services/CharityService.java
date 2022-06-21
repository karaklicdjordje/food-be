package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.CharityDTO;

public interface CharityService {
	
	List<CharityDTO> findAll ();
	
	CharityDTO findByID (Long id);
	
	CharityDTO createCharity (CharityDTO charityDto);
	
	CharityDTO updateCharity (CharityDTO charityDto, Long id);
	
	void deleteCharity (Long id);

	
}
