package com.example.demo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.RegistrationDTO;
import com.example.demo.entities.User;

@Mapper
public interface RegistrationMapper {
	
	RegistrationMapper INSTANCE = Mappers.getMapper(RegistrationMapper.class);

//	public RegistrationDTO entityToDto(User user);

	public User dtoToEntity(RegistrationDTO registrationDTO);

}