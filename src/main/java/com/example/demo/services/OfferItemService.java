package com.example.demo.services;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.dto.OfferDTO;
import com.example.demo.dto.OfferItemDTO;

public interface OfferItemService {
	
	List<OfferItemDTO> findAll();
	
	OfferItemDTO findByID (Long id);
	
	OfferItemDTO createOfferItem (Integer quantity, Long foodId);
	
	OfferItemDTO updateOfferItem (OfferItemDTO offerItemDto, Long id);
	
	void deleteOfferItem (Long id);

	

	

}
