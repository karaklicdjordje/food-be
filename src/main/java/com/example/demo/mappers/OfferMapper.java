package com.example.demo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.OfferDTO;
import com.example.demo.entities.Offer;

@Mapper
public interface OfferMapper {
	
	OfferMapper INSTANCE = Mappers.getMapper(OfferMapper.class);
	
	public OfferDTO entityToDTO (Offer offer);
	
	public Offer dtoToEntity (OfferDTO offerDTO);

}
