package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartItemDTO {
	
	private Long id;
	
	private int quantity;
	
	private OfferItemDTO offerItem;
	
	private ShoppingCartDTO shoppingCart;
	
	private double price;

}
