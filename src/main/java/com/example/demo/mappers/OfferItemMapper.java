package com.example.demo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.OfferItemDTO;
import com.example.demo.entities.OfferItem;

@Mapper
public interface OfferItemMapper {
	
	OfferItemMapper INSTANCE = Mappers.getMapper(OfferItemMapper.class);
	
	public OfferItemDTO entityToDTO (OfferItem offerItem);
	
	public OfferItem dtoToEntity (OfferItemDTO offerItemDTo);

}
