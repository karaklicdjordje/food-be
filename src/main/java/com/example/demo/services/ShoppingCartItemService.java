package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.ShoppingCartItemDTO;


public interface ShoppingCartItemService {

	List<ShoppingCartItemDTO> findAll();
	
	ShoppingCartItemDTO findById(Long id);
	
	ShoppingCartItemDTO createShoopingCartItem(ShoppingCartItemDTO shoppingCartItemDto);
	
	ShoppingCartItemDTO updateShoppingCartItemDTO (ShoppingCartItemDTO shoppingCartItemDto, Long id);
	
	void deleteShoppingCartItem (Long id);
	
	void validateShoppingCartItem (ShoppingCartItemDTO shoppingCartItem);
}
