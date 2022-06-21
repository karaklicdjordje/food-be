package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.OrderEntity;
import com.example.demo.entities.User;
import com.example.demo.entities.enums.OrderStatus;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
	
	public List<OrderEntity> findByUser(User user);
	
	public List<OrderEntity> findByOrderStatus(OrderStatus orderStatus);
	
	public List<OrderEntity> findByUserAndOrderStatus(User user, OrderStatus orderStatus);

}
