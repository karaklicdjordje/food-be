package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.OrderDTO;
import com.example.demo.entities.enums.OrderStatus;

public interface OrderServices {

	public List<OrderDTO> findAll();

	public List<OrderDTO> findByUser(Long userId);

	public OrderDTO findById(Long id);
	
	public List<OrderDTO> findByOrderStatus(OrderStatus orderStatus);
	
	public List<OrderDTO> findByUserAndOrderStatus(Long userId, OrderStatus orderStatus);

	public OrderDTO createOrder(OrderDTO orderDTO);

	public OrderDTO updateOrderStatus(Long id, OrderStatus orderStatus);

}
