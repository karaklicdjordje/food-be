package com.example.demo.services;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.dto.OfferDTO;

public interface OfferService {
	
	List<OfferDTO> findAll();
	
	OfferDTO findByID (Long id);
	
	OfferDTO createOffer (OfferDTO offerDto);
	
	OfferDTO updateOffer (OfferDTO offerDto, Long id);
	
	void deleteOffer (Long id);

	OfferDTO addFoodToOffer(Long offerId, Long foodId);

	OfferDTO findByDate(LocalDate date);

	OfferDTO deleteFoodFromOffer(Long offerId, Long foodId);

	List<OfferDTO> findNotExpiredOffer();
	
	public void setOffersToExpired();
	

}
