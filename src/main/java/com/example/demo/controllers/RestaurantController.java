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

import com.example.demo.dto.RestaurantDTO;
import com.example.demo.services.RestaurantService;

@RestController
@RequestMapping("/restauraunt")

public class RestaurantController {

	@Autowired
	RestaurantService restaurantService;
	
	@GetMapping
	public ResponseEntity<List<RestaurantDTO>> findAll() {
		return new ResponseEntity<List<RestaurantDTO>>(restaurantService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RestaurantDTO> findByID(@PathVariable Long id) {
		return new ResponseEntity<>(restaurantService.findById(id), HttpStatus.OK);
		
	}
	
	@PostMapping
	public ResponseEntity<?> createPerson(@Valid @RequestBody RestaurantDTO restaurantDto, BindingResult result){
		

		if(result.hasErrors()) {
			List<String> errors = new ArrayList<>();
			for(ObjectError error : result.getAllErrors()) {
				errors.add(error.getDefaultMessage());
			}
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(restaurantService.createRestaurant(restaurantDto), HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<RestaurantDTO> updateRestaurant(@RequestBody RestaurantDTO restaurantDto,@PathVariable Long id){
		return new ResponseEntity<>(restaurantService.updateRestaurant(restaurantDto, id), HttpStatus.OK);
		
	}
	
	@PutMapping("/{restaurantId}/users/{userId}")
	public ResponseEntity<HttpStatus> subscribe(@PathVariable Long restaurantId, @PathVariable Long userId){
		restaurantService.subscribeUserToRestaurant(restaurantId, userId);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteRestaurant (@PathVariable Long id){
		restaurantService.deleteResaurant(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
