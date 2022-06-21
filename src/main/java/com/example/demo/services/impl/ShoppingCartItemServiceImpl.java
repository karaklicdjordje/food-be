//package com.example.demo.services.impl;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.example.demo.dto.ShoppingCartItemDTO;
//import com.example.demo.entities.ShoppingCart;
//import com.example.demo.entities.ShoppingCartItem;
//import com.example.demo.exceptions.NotUniqueException;
//import com.example.demo.mappers.ShoppingCartItemMapper;
//import com.example.demo.mappers.ShoppingCartMapper;
//import com.example.demo.repositories.ShoppingCartItemRepository;
//import com.example.demo.services.ShoppingCartItemService;
//
//@Service
//public class ShoppingCartItemServiceImpl implements ShoppingCartItemService{
//	
//	@Autowired
//	ShoppingCartItemRepository shoppingCartItemRepository;
//
//	@Override
//	public List<ShoppingCartItemDTO> findAll() {
//		
//		List<ShoppingCartItem> items = shoppingCartItemRepository.findAll();
//		
//		List<ShoppingCartItemDTO> itemsDto = new ArrayList<>();
//		
////		for (ShoppingCartItem item : items) {
////			itemsDto.add(ShoppingCartItemMapper.INSTANCE.entityToDto(item));
////		}
//		
//		
//		return itemsDto;
//	}
//
//	@Override
//	public ShoppingCartItemDTO findById(Long id) {
//			ShoppingCartItem shoppingCart = shoppingCartItemRepository.getById(id);
//			return ShoppingCartItemMapper.INSTANCE.entityToDto(shoppingCart);
//		}
//	
//
//	@Override
//	public ShoppingCartItemDTO createShoopingCartItem(ShoppingCartItemDTO shoppingCartItemDto) {
//		ShoppingCartItem shoppingCartItem = ShoppingCartItemMapper.INSTANCE.dtoToEntity(shoppingCartItemDto);
//		shoppingCartItemRepository.save(shoppingCartItem);
//		return ShoppingCartItemMapper.INSTANCE.entityToDto(shoppingCartItem);
//	}
//
//	@Override
//	public ShoppingCartItemDTO updateShoppingCartItemDTO(ShoppingCartItemDTO shoppingCartItemDto, Long id) {
//		ShoppingCartItem shoppingCartItem = shoppingCartItemRepository.findById(id).orElseThrow();
//		shoppingCartItem.setPrice(shoppingCartItemDto.getPrice());
//		shoppingCartItem.setQuantity(shoppingCartItemDto.getQuantity());
//		//shoppingCartItem.setOfferItem(shoppingCartItemDto.getOfferItem());TODO
//		shoppingCartItemRepository.save(shoppingCartItem);
//		
//		return ShoppingCartItemMapper.INSTANCE.entityToDto(shoppingCartItem);
//	}
//
//	@Override
//	public void deleteShoppingCartItem(Long id) { 
//		boolean hasShoppingCartItem = shoppingCartItemRepository.existsById(id);
//		if (!hasShoppingCartItem) {
//			throw new NotUniqueException("Ovo nije dozvoljeno!");
//		}
//		
//		shoppingCartItemRepository.deleteById(id);
//		
//		
//	}
//
//	@Override
//	public void validateShoppingCartItem(ShoppingCartItemDTO shoppingCartItem) {
//		// TODO Auto-generated method stub
//		
//	}
//
//}
