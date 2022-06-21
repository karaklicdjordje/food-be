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

import com.example.demo.dto.CorporateDTO;
import com.example.demo.dto.FoodDTO;
import com.example.demo.services.CorporateService;

@RestController
@RequestMapping("/corporate")

public class CorporateController {
	
	@Autowired
	CorporateService corporateService;
	
	@GetMapping
	public ResponseEntity<List<CorporateDTO>> findAll() {
		return new ResponseEntity<List<CorporateDTO>>(corporateService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CorporateDTO> findByID(@PathVariable Long id){
		return new ResponseEntity<>(corporateService.findByID(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> createCorporate(@Valid @RequestBody CorporateDTO corporateDTO, BindingResult result) {
		
		if(result.hasErrors()) {
			List<String> errors = new ArrayList<>();
			for(ObjectError error : result.getAllErrors()) {
				errors.add(error.getDefaultMessage());
			}
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(corporateService.createCorporate(corporateDTO), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CorporateDTO> updateCorporate(@RequestBody CorporateDTO corporateDto, @PathVariable Long id) {
		return new ResponseEntity<>(corporateService.updateCorporate(corporateDto, id), HttpStatus.OK);
		}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteCorporate(@PathVariable Long id){
		corporateService.deleteCorporate(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
