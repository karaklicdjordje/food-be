package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.OfferItemDTO;
import com.example.demo.services.OfferItemService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/offerItems")
public class OfferItemController {

	@Autowired
	private OfferItemService offerItemService;
	
	@GetMapping
	public ResponseEntity<List<OfferItemDTO>> findAll(){
		return new ResponseEntity<List<OfferItemDTO>>(offerItemService.findAll(), HttpStatus.OK);
	
	}
	
	@GetMapping("{id}")
	public ResponseEntity<OfferItemDTO> findById(@PathVariable Long id) {
		return new ResponseEntity<>(offerItemService.findByID(id), HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<Void> createShoppingCartItem(@RequestParam Integer quantity, @RequestParam Long foodId){
		offerItemService.createOfferItem(quantity, foodId);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<HttpStatus> deleteShoppingCartItem(@PathVariable Long id) {
		offerItemService.deleteOfferItem(id);
		return new ResponseEntity<>(HttpStatus.OK);

	}
}
