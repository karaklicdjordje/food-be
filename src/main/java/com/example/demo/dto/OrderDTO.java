package com.example.demo.dto;

import java.time.LocalDateTime;
import java.util.Set;

import com.example.demo.entities.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {
	
	private Long id;
	
	private Long userId;
	
	private Set<OrderItemDTO> orderItems;
	
	private LocalDateTime orderDateTime;
	
	private OrderStatus orderStatus;
	
	private double totalPrice;
	
	private Long restaurantId;

}
