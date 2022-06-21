package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PersonDTO;
import com.example.demo.services.PersonService;

@RestController
@RequestMapping("/person")

public class PersonController {

	@Autowired
	PersonService personService;
	
	@GetMapping
	public ResponseEntity<List<PersonDTO>> findAll() {
		return new ResponseEntity<List<PersonDTO>>(personService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PersonDTO> findByID(@PathVariable Long id) {
		return new ResponseEntity<>(personService.findById(id), HttpStatus.OK);
		
	}
	
	@PostMapping
	public ResponseEntity<?> createPerson(@Valid @RequestBody PersonDTO personDto, BindingResult result){
		

		if(result.hasErrors()) {
			List<String> errors = new ArrayList<>();
			for(ObjectError error : result.getAllErrors()) {
				errors.add(error.getDefaultMessage());
			}
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(personService.createPerson(personDto), HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PersonDTO> updatePerson (@RequestBody PersonDTO personDto,@PathVariable Long id){
		return new ResponseEntity<>(personService.updatePerson(personDto, id), HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deletePerson (@PathVariable Long id){
		personService.deletePerson(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
