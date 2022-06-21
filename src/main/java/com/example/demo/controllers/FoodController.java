package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.FoodDTO;
import com.example.demo.entities.enums.TypeOfFood;
import com.example.demo.services.FoodService;

@RestController
@RequestMapping("/food")
public class FoodController {
	
	@Autowired
	FoodService foodService;
	
	@GetMapping
	public ResponseEntity<List<FoodDTO>> findAll() {
		return new ResponseEntity<List<FoodDTO>>(foodService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = {"/id"}, method = RequestMethod.GET)
	public ResponseEntity<FoodDTO> findByID(@PathVariable Long id){;
		return new ResponseEntity<>(foodService.findByID(id), HttpStatus.OK);
	}
	
	@GetMapping("/{typeOfFood}")
	public ResponseEntity<FoodDTO> findByTypeOfFood(@PathVariable TypeOfFood typeOfFood){
		return new ResponseEntity<>(foodService.findByType(typeOfFood), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> createFood(@Valid @RequestBody FoodDTO foodDTO, BindingResult result) {
		
		if(result.hasErrors()) {
			List<String> errors = new ArrayList<>();
			for(ObjectError error : result.getAllErrors()) {
				errors.add(error.getDefaultMessage());
			}
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(foodService.createFood(foodDTO), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<FoodDTO> updateFood (@RequestBody FoodDTO foodDTO, @PathVariable Long id) {
		return new ResponseEntity<>(foodService.updateFood(foodDTO, id), HttpStatus.OK);
		}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteFood(@PathVariable Long id){
		foodService.deleteFood(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	

}
