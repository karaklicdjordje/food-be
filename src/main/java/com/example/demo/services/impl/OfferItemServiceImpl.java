package com.example.demo.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.OfferItemDTO;
import com.example.demo.entities.Food;
import com.example.demo.entities.OfferItem;
import com.example.demo.mappers.OfferItemMapper;
import com.example.demo.mappers.OfferMapper;
import com.example.demo.repositories.FoodRepository;
import com.example.demo.repositories.OfferItemRepository;
import com.example.demo.services.OfferItemService;

@Service
public class OfferItemServiceImpl implements OfferItemService {

	@Autowired
	private OfferItemRepository offerItemRepository;
	
	@Autowired
	private FoodRepository foodRepository;

	@Override
	public List<OfferItemDTO> findAll() {
		List<OfferItem> offerItems = offerItemRepository.findAll();
		return offerItems.stream().map(offerItem -> OfferItemMapper.INSTANCE.entityToDTO(offerItem))
				.collect(Collectors.toList());

	}

	@Override
	public OfferItemDTO findByID(Long id) {
		OfferItem offerItem = offerItemRepository.getById(id);
		return OfferItemMapper.INSTANCE.entityToDTO(offerItem);
	}

	@Override
	public OfferItemDTO createOfferItem(Integer quantity, Long foodId) {
		Food food = foodRepository.findById(foodId).orElseThrow(IllegalArgumentException::new);
		OfferItem offerItem = OfferItem.builder().food(food).quantity(quantity).build();
		offerItemRepository.save(offerItem);
		return OfferItemMapper.INSTANCE.entityToDTO(offerItem);
	}

	@Override
	public OfferItemDTO updateOfferItem(OfferItemDTO offerItemDto, Long id) {
		OfferItem offerItem = offerItemRepository.getById(id);
		Food food = foodRepository.findById(offerItemDto.getFood().getId()).orElseThrow(IllegalArgumentException::new);
		offerItem.setFood(food);
		offerItem.setOffer(OfferMapper.INSTANCE.dtoToEntity(offerItemDto.getOffer()));
		offerItem.setQuantity(offerItemDto.getQuantity());
		offerItemRepository.save(offerItem);
		food.getOfferItems().add(offerItem);
		foodRepository.save(food);
		return OfferItemMapper.INSTANCE.entityToDTO(offerItem);
	}

	@Override
	public void deleteOfferItem(Long id) {
		if (!offerItemRepository.existsById(id)) {
			throw new IllegalArgumentException();
		}
		offerItemRepository.deleteById(id);
	}
}
