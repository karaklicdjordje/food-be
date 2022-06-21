package com.example.demo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.FoodDTO;

import com.example.demo.entities.Food;


@Mapper
public interface FoodMapper {
	
	FoodMapper INSTANCE = Mappers.getMapper(FoodMapper.class);

	public FoodDTO entityToDto(Food food);

	public Food dtoToEntity(FoodDTO foodDTO);

}
