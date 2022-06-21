package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CharityDTO;
import com.example.demo.dto.FoodDTO;
import com.example.demo.entities.Charity;
import com.example.demo.entities.Food;
import com.example.demo.entities.enums.Role;
import com.example.demo.exceptions.NotUniqueException;
import com.example.demo.mappers.CharityMapper;
import com.example.demo.mappers.FoodMapper;
import com.example.demo.repositories.CharityRepository;
import com.example.demo.services.CharityService;

@Service
public class CharityServiceImpl implements CharityService {
	
	@Autowired
	CharityRepository charityRepository;
	

	@Override
	public List<CharityDTO> findAll() {

		List<Charity> charities = charityRepository.findAll();
		List<CharityDTO> charityDTO = new ArrayList<CharityDTO>();
		for (Charity charity : charities) {
			charityDTO.add(CharityMapper.INSTANCE.entitysToDto(charity));
		}

		return charityDTO;
	}

	@Override
	public CharityDTO findByID(Long id) {

		Charity charity = charityRepository.findById(id).orElseThrow(null);
		return CharityMapper.INSTANCE.entitysToDto(charity);
	}

	@Override
	public CharityDTO createCharity(CharityDTO charityDto) {

		Charity charity = CharityMapper.INSTANCE.dtoToEntity(charityDto);
		charity.setRole(Role.ROLE_CHARITY);
		charityRepository.save(charity);
		return CharityMapper.INSTANCE.entitysToDto(charity);

	}

	@Override
	public CharityDTO updateCharity(CharityDTO charityDto, Long id) {
		Charity charity = charityRepository.findById(id).orElseThrow(null);
		charity.setName(charityDto.getName());
		//charity.setAddress(charityDto.getAddress()); TODO
		charity.setEmail(charityDto.getEmail());
		//charity.setPassword(charityDto.getPassword()); TODO
		charityRepository.save(charity);
		
		
		return CharityMapper.INSTANCE.entitysToDto(charity);
	}

	@Override
	public void deleteCharity(Long id) {
		boolean hasCharity = charityRepository.existsById(id);
		if (!hasCharity) {
			throw new NotUniqueException("Ovo nije dozvoljeno!");
			
		}
		charityRepository.deleteById(id);
	}
	

}
