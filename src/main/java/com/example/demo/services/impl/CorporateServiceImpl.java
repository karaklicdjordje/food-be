package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CorporateDTO;
import com.example.demo.dto.FoodDTO;
import com.example.demo.entities.Corporate;
import com.example.demo.entities.Food;
import com.example.demo.entities.enums.Role;
import com.example.demo.exceptions.NotUniqueException;
import com.example.demo.mappers.CorporateMapper;
import com.example.demo.mappers.FoodMapper;
import com.example.demo.repositories.CorporateRepository;
import com.example.demo.services.CorporateService;

@Service
public class CorporateServiceImpl implements CorporateService {

	@Autowired
	CorporateRepository corporateRepository;
	
	
	@Override
	public List<CorporateDTO> findAll() {

		List<Corporate> corporates = corporateRepository.findAll();
		List<CorporateDTO> corporateDto = new ArrayList<CorporateDTO>();
		for (Corporate corporate : corporates) {
			corporateDto.add(CorporateMapper.INSTANCE.entitysToDto(corporate));
		}

		return corporateDto;
	}

	@Override
	public CorporateDTO findByID(Long id) {

		Corporate corporate = corporateRepository.findById(id).orElseThrow(null);
		return CorporateMapper.INSTANCE.entitysToDto(corporate);
	}

	@Override
	public CorporateDTO createCorporate(CorporateDTO corporateDto) {

		Corporate corporate = CorporateMapper.INSTANCE.dtoToEntity(corporateDto);
		corporate.setRole(Role.ROLE_CORPORATE);
		corporateRepository.save(corporate);
		return CorporateMapper.INSTANCE.entitysToDto(corporate);

	}

	@Override
	public CorporateDTO updateCorporate(CorporateDTO corporateDto, Long id) {
		Corporate corporate = corporateRepository.findById(id).orElseThrow(null);
		corporate.setName(corporateDto.getName());
		corporate.setEmail(corporateDto.getEmail());
		corporateRepository.save(corporate);
		
		
		return CorporateMapper.INSTANCE.entitysToDto(corporate);
	}

	@Override
	public void deleteCorporate(Long id) {
		boolean hasCorporate = corporateRepository.existsById(id);
		if (!hasCorporate) {
			throw new NotUniqueException("Ovo nije dozvoljeno!");
			
		}
		corporateRepository.deleteById(id);
	}

}
