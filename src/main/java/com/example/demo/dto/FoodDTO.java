package com.example.demo.dto;

import java.util.List;

import com.example.demo.entities.OrderItem;
import com.example.demo.entities.enums.TypeOfFood;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class FoodDTO {
	
	private Long id;
	
	private String name;
	
	private double price;
	
	private TypeOfFood typeOfFood;
	
//	private List<OfferDTO> offers;
	
//	private List<OrderItem> orderItems; 

}
