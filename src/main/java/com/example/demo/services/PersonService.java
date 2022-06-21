package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.PersonDTO;
import com.example.demo.dto.UserDTO;

public interface PersonService {
	
	List<PersonDTO> findAll ();
	
	PersonDTO findById (Long id);
	
	PersonDTO createPerson (PersonDTO personDto);
	
	PersonDTO updatePerson (PersonDTO personDto, Long id);
	
	void deletePerson (Long id); 
	
	

}
