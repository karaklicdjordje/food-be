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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AddressDTO;
import com.example.demo.services.AddressService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	AddressService addressService;
	
	@GetMapping
	@Operation(summary = "Find all Address", description = "Find all address")
	public ResponseEntity<List<AddressDTO>> findAll() {
		return new ResponseEntity<List<AddressDTO>>(addressService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Fidn address by ID", description = "Find address by ID")
	public ResponseEntity<AddressDTO> findByID(@PathVariable Long id){
		return new ResponseEntity<>(addressService.findByID(id), HttpStatus.OK);
	}
	
	
	
	@PostMapping
	@Operation (summary = "Create address", description = "Create address")
	public ResponseEntity<?> createAddress(@Valid @RequestBody AddressDTO addressDto, BindingResult result) {
		
		if(result.hasErrors()) {
			List<String> errors = new ArrayList<>();
			for(ObjectError error : result.getAllErrors()) {
				errors.add(error.getDefaultMessage());
			}
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(addressService.createAddress(addressDto), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@Operation ( summary = "Update address", description = "Update address")
	public ResponseEntity<AddressDTO> updateAddress (@RequestBody AddressDTO addressDTO, @PathVariable Long id) {
		return new ResponseEntity<>(addressService.updateAddress(addressDTO, id), HttpStatus.OK);
		}
	
	@DeleteMapping("/{id}")
	@Operation (summary = "Delete address by ID", description = "Delete address")
	public ResponseEntity<HttpStatus> deleteAddress(@PathVariable Long id){
		addressService.deleteAddress(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
