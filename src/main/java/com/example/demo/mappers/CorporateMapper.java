package com.example.demo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.CorporateDTO;
import com.example.demo.dto.RegistrationDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entities.Corporate;
import com.example.demo.entities.Restaurant;

@Mapper
public interface CorporateMapper {

	CorporateMapper INSTANCE = Mappers.getMapper(CorporateMapper.class);

	public UserDTO entityToDto(Corporate corporate);

	public Corporate dtoToEntity(UserDTO userDTO);
	
	public Corporate dtoToEntity (CorporateDTO corporateDto);
	
	public CorporateDTO entitysToDto (Corporate corporate);
	
	public Corporate dtoToEntity(RegistrationDTO registrationDTO);
}
