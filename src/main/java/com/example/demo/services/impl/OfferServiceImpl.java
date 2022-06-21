package com.example.demo.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.dto.OfferDTO;
import com.example.demo.entities.Offer;
import com.example.demo.entities.OfferItem;
import com.example.demo.exceptions.NotUniqueException;
import com.example.demo.mappers.OfferMapper;
import com.example.demo.repositories.OfferItemRepository;
import com.example.demo.repositories.OfferRepository;
import com.example.demo.services.OfferService;
import com.example.demo.utils.EntityHelper;

@Service
@EnableAsync
public class OfferServiceImpl implements OfferService {

	@Autowired
	OfferRepository offerRepository;

	@Autowired
	OfferItemRepository offerItemRepository;

	@Override
	public List<OfferDTO> findAll() {
		List<Offer> offers = offerRepository.findAll();
		List<OfferDTO> offersDto = new ArrayList<OfferDTO>();

		for (Offer offer : offers) {
			offersDto.add(OfferMapper.INSTANCE.entityToDTO(offer));

		}

		return offersDto;
	}
	
	@Override
	public List<OfferDTO> findNotExpiredOffer() {
		List<Offer> offers = offerRepository.findByExpired(false);
		List<OfferDTO> offersDto = new ArrayList<OfferDTO>();

		for (Offer offer : offers) {
			offersDto.add(OfferMapper.INSTANCE.entityToDTO(offer));

		}

		return offersDto;
	}

	@Override
	public OfferDTO findByID(Long id) {
		Offer offer = offerRepository.findById(id).orElseThrow(null);
		return OfferMapper.INSTANCE.entityToDTO(offer);
	}

	@Override
	public OfferDTO findByDate(LocalDate date) {
		Offer offer = offerRepository.findByDate(date).orElseThrow(null);
		OfferDTO offerDTO = OfferMapper.INSTANCE.entityToDTO(offer);
		return offerDTO;
	}

	@Override
	public OfferDTO createOffer(OfferDTO offerDto) {
		Offer offer = OfferMapper.INSTANCE.dtoToEntity(offerDto);
		offerRepository.save(offer);
		return OfferMapper.INSTANCE.entityToDTO(offer);
	}

	@Override
	public OfferDTO updateOffer(OfferDTO offerDto, Long id) {
		Offer offer = offerRepository.findById(id).orElseThrow(null);
		offer.setDate(offerDto.getDate());
		offer.setOfferItems(offerDto.getOfferItems());
		offer.setRestaurant(offerDto.getRestaurant());
		offer.setExpired(offerDto.isExpired());
		offerRepository.save(offer);
		return OfferMapper.INSTANCE.entityToDTO(offer);
	}

	@Override
	public void deleteOffer(Long id) {
		boolean hasOffer = offerRepository.existsById(id);
		if (!hasOffer) {
			throw new NotUniqueException("Ovo nije dozvoljeno!");

		}
		offerRepository.deleteById(id);
	}

	@Override
	public OfferDTO addFoodToOffer(Long offerId, Long offerItemId) {
		Offer offer = EntityHelper.getEntity(offerId, offerRepository);

		OfferItem offerItem = EntityHelper.getEntity(offerItemId, offerItemRepository);
		offerItem.setOffer(offer);
		offerItemRepository.save(offerItem);
		
		offer.getOfferItems().add(offerItem);

		return OfferMapper.INSTANCE.entityToDTO(offer);
	}

	@Override
	public OfferDTO deleteFoodFromOffer(Long offerId, Long offerItemId) {
		
		Offer offer = EntityHelper.getEntity(offerId, offerRepository);

		OfferItem offerItem = EntityHelper.getEntity(offerItemId, offerItemRepository);

		if(offer.getOfferItems().contains(offerItem)) {
			offerItem.setOffer(null);
			offerItemRepository.save(offerItem);
			offer.getOfferItems().remove(offerItem);
		}
		

		return OfferMapper.INSTANCE.entityToDTO(offer);
	}
	
	@Async
	@Scheduled(cron = "${cron-to-check-offer-expiration}")
	public void setOffersToExpired() {
		List<Offer> offers = offerRepository.findAll();
		LocalDate date = LocalDate.now();
		offers.forEach(offer -> {
			if(!offer.getDate().isAfter(date)) {
				offer.setExpired(true);
			} 
		});
		offerRepository.saveAll(offers);
	}
	

}
