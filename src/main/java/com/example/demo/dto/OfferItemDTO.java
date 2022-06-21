package com.example.demo.dto;



import com.example.demo.entities.Food;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OfferItemDTO {

	private Long id;
	
	private Food food;
	
	private int quantity;
	
	private OfferDTO offer;
}
