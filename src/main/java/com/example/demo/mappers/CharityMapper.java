package com.example.demo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.CharityDTO;
import com.example.demo.dto.RegistrationDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entities.Charity;
import com.example.demo.entities.Restaurant;

@Mapper
public interface CharityMapper {
	
	CharityMapper INSTANCE = Mappers.getMapper(CharityMapper.class);

	public UserDTO entityToDto(Charity charity);

	public Charity dtoToEntity(UserDTO userDTO);
	
	public CharityDTO entitysToDto(Charity charity);
	
	public Charity dtoToEntity (CharityDTO charityDto);
	
	public Charity dtoToEntity(RegistrationDTO registrationDTO);

}
