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

import com.example.demo.dto.CharityDTO;
import com.example.demo.dto.FoodDTO;
import com.example.demo.services.CharityService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/charity")

public class CharityController {

	@Autowired
	CharityService charityService;
	
	@GetMapping
	@Operation(summary = "Find all Charity", description = "Find all Charity")
	public ResponseEntity<List<CharityDTO>> findAll() {
		return new ResponseEntity<List<CharityDTO>>(charityService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Find Charity by ID", description = "Find Charity by ID")
	public ResponseEntity<CharityDTO> findByID(@PathVariable Long id){
		return new ResponseEntity<>(charityService.findByID(id), HttpStatus.OK);
	}
	
	@PostMapping
	@Operation(summary = "Create charity", description = "Create charity")
	public ResponseEntity<?> createCharity(@Valid @RequestBody CharityDTO charityDTO, BindingResult result) {
		
		if(result.hasErrors()) {
			List<String> errors = new ArrayList<>();
			for(ObjectError error : result.getAllErrors()) {
				errors.add(error.getDefaultMessage());
			}
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(charityService.createCharity(charityDTO), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "Update charity", description = "Update charity")
	public ResponseEntity<CharityDTO> updateCharity (@RequestBody CharityDTO charityDTO, @PathVariable Long id) {
		return new ResponseEntity<>(charityService.updateCharity(charityDTO, id), HttpStatus.OK);
		}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Delete charity by ID", description = "Delete charity by ID")
	public ResponseEntity<HttpStatus> deleteCharity(@PathVariable Long id){
		charityService.deleteCharity(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
