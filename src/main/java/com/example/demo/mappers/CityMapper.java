package com.example.demo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


import com.example.demo.dto.CityDTO;
import com.example.demo.entities.City;

@Mapper
public interface CityMapper {
	
	CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);
	
	public CityDTO entityToDTO (City city);
	
	public City dtoToEntity (CityDTO cityDto);

}
