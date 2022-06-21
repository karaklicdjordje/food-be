package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.ShoppingCartDTO;

public interface ShoppingCartService {
	
	List<ShoppingCartDTO> findAll();
	
	ShoppingCartDTO findByID (Long id);
	
	void deleteShoppingCartDTO (Long id);
	
	ShoppingCartDTO createShoppingCart (ShoppingCartDTO shoppingCartDto);
	

}
