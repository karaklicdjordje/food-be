package com.example.demo.services;

import java.util.List;


import com.example.demo.dto.CityDTO;

public interface CityService {
	
	List<CityDTO> findAll ();
	
	CityDTO findByID (Long id);
	
	CityDTO createCity (CityDTO cityDto);
	
	CityDTO updateCity (CityDTO cityDto, Long id);
	
	void deleteCity (Long id);

	
}
