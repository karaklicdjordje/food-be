package com.example.demo.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
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

import com.example.demo.dto.OfferDTO;
import com.example.demo.services.OfferService;

@RestController
@RequestMapping("/offer")

public class OfferController {

	@Autowired
	OfferService offerService;
	
	@GetMapping
	public ResponseEntity<List<OfferDTO>> findAllOffer(){
		return new ResponseEntity<List<OfferDTO>>(offerService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/actual")
	public ResponseEntity<List<OfferDTO>> findActualOffer(){
		return new ResponseEntity<List<OfferDTO>>(offerService.findNotExpiredOffer(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OfferDTO> findByID(@PathVariable Long id){
		return new ResponseEntity<OfferDTO>(offerService.findByID(id), HttpStatus.OK);
	}
	
	@GetMapping("/findbydate/{date}")
	public ResponseEntity<OfferDTO> findByDate(@DateTimeFormat(iso = ISO.DATE) @PathVariable LocalDate date) {
		return new ResponseEntity<OfferDTO>(offerService.findByDate(date), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> createOffer(@Valid @RequestBody OfferDTO offerDto, BindingResult result){
		{
			
			if(result.hasErrors()) {
				List<String> errors = new ArrayList<>();
				for(ObjectError error : result.getAllErrors()) {
					errors.add(error.getDefaultMessage());
				}
				return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<>(offerService.createOffer(offerDto), HttpStatus.CREATED);
	}

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteOffer(@PathVariable Long id){
		offerService.deleteOffer(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("addFoodToOffer/{offerId}/{offerItemId}")
	public ResponseEntity<OfferDTO> addFoodToOffer(@PathVariable Long offerId, @PathVariable Long offerItemId){
		return new ResponseEntity<>(offerService.addFoodToOffer(offerId, offerItemId), HttpStatus.CREATED);
	}

	@PutMapping("deleteFoodFromOffer/{offerId}/{offerItemId}")
	public ResponseEntity<OfferDTO> deleteFoodFromOffer(@PathVariable Long offerId, @PathVariable Long offerItemId){
		return new ResponseEntity<>(offerService.deleteFoodFromOffer(offerId, offerItemId), HttpStatus.CREATED);
	}

}
