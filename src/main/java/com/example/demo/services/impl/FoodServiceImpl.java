package com.example.demo.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.FoodDTO;
import com.example.demo.dto.OfferDTO;
import com.example.demo.entities.Food;
import com.example.demo.entities.Offer;
import com.example.demo.entities.enums.TypeOfFood;
import com.example.demo.exceptions.NotUniqueException;
import com.example.demo.mappers.FoodMapper;
import com.example.demo.mappers.OfferMapper;
import com.example.demo.repositories.FoodRepository;
import com.example.demo.services.FoodService;

@Service
public class FoodServiceImpl implements FoodService {

	@Autowired
	FoodRepository foodRepository;

	@Override
	public List<FoodDTO> findAll() {

		List<Food> foods = foodRepository.findAll();
		List<FoodDTO> foodsDTO = new ArrayList<FoodDTO>();
		for (Food food : foods) {
			foodsDTO.add(FoodMapper.INSTANCE.entityToDto(food));
		}

		return foodsDTO;
	}

	public FoodDTO findByID(Long id) {

		Food food = foodRepository.findById(id).orElseThrow(null);
		return FoodMapper.INSTANCE.entityToDto(food);
	}

	public FoodDTO createFood(FoodDTO foodDTO) {

		Food food = FoodMapper.INSTANCE.dtoToEntity(foodDTO);
		foodRepository.save(food);
		return FoodMapper.INSTANCE.entityToDto(food);

	}
	
	public FoodDTO updateFood (FoodDTO foodDTO, Long id) {
		Food food = foodRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
		food.setName(foodDTO.getName());
		food.setPrice(foodDTO.getPrice());
		food.setTypeOfFood(foodDTO.getTypeOfFood());
		foodRepository.save(food);
		
		
		return FoodMapper.INSTANCE.entityToDto(food);
	}
	
	public void deleteFood (Long id) {
		boolean hasFood = foodRepository.existsById(id);
		if (!hasFood) {
			throw new NotUniqueException("Ovo nije dozvoljeno!");
			
		}
		foodRepository.deleteById(id);
	}

	@Override
	
	public FoodDTO findByType(TypeOfFood typeOfFood) {
		Food food = foodRepository.findByTypeOfFood(typeOfFood).orElseThrow(null);
		FoodDTO foodDTO = FoodMapper.INSTANCE.entityToDto(food);
		return foodDTO;
	}
}
