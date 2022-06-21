package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.FoodDTO;
import com.example.demo.entities.enums.TypeOfFood;

import aj.org.objectweb.asm.Type;

public interface FoodService {

	List<FoodDTO> findAll();
	
	FoodDTO findByID (Long id);
	
	FoodDTO createFood (FoodDTO foodDTO);
	
	FoodDTO updateFood (FoodDTO foodDTO, Long id);
	
	FoodDTO findByType (TypeOfFood typeOfFood);
	
	void deleteFood (Long id);
}
