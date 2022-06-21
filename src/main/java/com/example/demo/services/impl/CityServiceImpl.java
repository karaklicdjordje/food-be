package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AddressDTO;
import com.example.demo.dto.CityDTO;
import com.example.demo.entities.Address;
import com.example.demo.entities.City;
import com.example.demo.exceptions.NotUniqueException;
import com.example.demo.mappers.AddressMapper;
import com.example.demo.mappers.CityMapper;
import com.example.demo.repositories.CityRepository;
import com.example.demo.services.CityService;
import com.example.demo.utils.EntityHelper;
@Service
public class CityServiceImpl implements CityService {

	@Autowired
	CityRepository cityRepository;
	
	@Override
	public List<CityDTO> findAll() {
		List<City> cities = cityRepository.findAll();
		List<CityDTO> citiesDto = new ArrayList<CityDTO>();
		
		for (City city : cities) {
			citiesDto.add(CityMapper.INSTANCE.entityToDTO(city));
			
			
		}
			
		return citiesDto;
	}

	@Override
	public CityDTO findByID(Long id) {
		City city = EntityHelper.getEntity(id, cityRepository);
		return CityMapper.INSTANCE.entityToDTO(city);
	}

	@Override
	public CityDTO createCity(CityDTO cityDto) {
		City city = CityMapper.INSTANCE.dtoToEntity(cityDto);
		cityRepository.save(city);
		
		return CityMapper.INSTANCE.entityToDTO(city);
	}

	@Override
	public CityDTO updateCity(CityDTO cityDto, Long id) {
		City city = cityRepository.findById(id).orElseThrow(null);
		city.setName(cityDto.getName());
		city.setZipCode(cityDto.getZipCode());
		cityRepository.save(city);
		
		return CityMapper.INSTANCE.entityToDTO(city);
	}

	@Override
	public void deleteCity(Long id) {
		boolean hasCity = cityRepository.existsById(id);
		if (!hasCity) {
			throw new NotUniqueException("Ovo nije dozvoljeno!");
			
		}
		cityRepository.deleteById(id);
	}

}
