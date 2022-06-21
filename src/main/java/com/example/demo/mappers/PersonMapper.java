package com.example.demo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.PersonDTO;
import com.example.demo.dto.RegistrationDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entities.Person;
import com.example.demo.entities.Restaurant;

@Mapper
public interface PersonMapper {
	
	PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

	public UserDTO entityToDto(Person person);

	public Person dtoToEntity(UserDTO userDTO);
	
	public PersonDTO entitysToDto (Person person);
	
	public Person dtoToEntity(PersonDTO personDTO);
	
	public Person dtoToEntity(RegistrationDTO registrationDTO);

}
