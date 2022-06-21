package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PersonDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entities.Person;
import com.example.demo.entities.User;
import com.example.demo.entities.enums.Role;
import com.example.demo.exceptions.NotUniqueException;
import com.example.demo.mappers.PersonMapper;
import com.example.demo.repositories.PersonRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PersonRepository personRepositroy;
	
	@Override
	public List<PersonDTO> findAll() { 
		
		List<Person> persons = personRepositroy.findAll();
		
		List<PersonDTO> personsDto = new ArrayList<PersonDTO>();
		
		for (Person person : persons)
			personsDto.add(PersonMapper.INSTANCE.entitysToDto(person));
		
		return personsDto;
	}
	
	@Override
	public PersonDTO findById (Long id) {
		Person person = personRepositroy.findById(id).orElseThrow(null);		
		return PersonMapper.INSTANCE.entitysToDto(person);
		
	}

	@Override
	public PersonDTO createPerson(PersonDTO personDto) {
		Person person = PersonMapper.INSTANCE.dtoToEntity(personDto);
		person.setRole(Role.ROLE_PERSON);
		personRepositroy.save(person);
		
		return PersonMapper.INSTANCE.entitysToDto(person);
	}

	@Override
	public PersonDTO updatePerson(PersonDTO personDto, Long id) {
		Person person = personRepositroy.findById(id).orElseThrow(null);
		person.setName(personDto.getName());
		person.setEmail(personDto.getEmail());
		person.setJmbg(personDto.getJmbg());
		person.setSurname(personDto.getSurname());
		person.setUsername(personDto.getUsername());
		personRepositroy.save(person);
		return PersonMapper.INSTANCE.entitysToDto(person);
	}

	@Override
	public void deletePerson(Long id) {
		
		boolean hasPerson = personRepositroy.existsById(id);
		if (!hasPerson) {
			throw new NotUniqueException("Ovo nije dozvoljeno!");
			
		}
		personRepositroy.deleteById(id);
		
	}
	

}
