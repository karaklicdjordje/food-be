package com.example.demo.dto;

import java.util.List;

import com.example.demo.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartDTO {
	
	private Long id;
	
	private User user;
	
	private double totalPrice;
	
	private List<ShoppingCartItemDTO> shoppingCartItem;
	
	private OfferItemDTO offerItem;

}
