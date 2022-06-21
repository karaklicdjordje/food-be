package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.OrderDTO;
import com.example.demo.entities.enums.OrderStatus;
import com.example.demo.services.OrderItemServices;
import com.example.demo.services.OrderServices;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderServices orderServices;
	
	@GetMapping
	public ResponseEntity<List<OrderDTO>> getAll() {
		return ResponseEntity.ok(orderServices.findAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<OrderDTO> getById(@PathVariable Long id) {
		
		return ResponseEntity.ok(orderServices.findById(id));
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<List<OrderDTO>> getByUserId(@PathVariable Long userId) {
		return ResponseEntity.ok(orderServices.findByUser(userId));
	}
	
	@GetMapping("/findByOrderStatus")
	public ResponseEntity<List<OrderDTO>> getByOrderStatus(@RequestParam OrderStatus orderStatus){
		return ResponseEntity.ok(orderServices.findByOrderStatus(orderStatus));
	}
	
	@GetMapping("/users/{userId}/orderStatus")
	public ResponseEntity<List<OrderDTO>> getByUserAndOrderStatus(@PathVariable Long userId, @RequestParam OrderStatus orderStatus){
		return ResponseEntity.ok(orderServices.findByUserAndOrderStatus(userId, orderStatus));
	}
	
	@PostMapping()
	public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO){
		return new ResponseEntity<OrderDTO>(orderServices.createOrder(orderDTO), HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{id}/changeStatus")
	public ResponseEntity<OrderDTO> updateOrderStatus(@PathVariable Long id, @RequestParam OrderStatus orderStatus){
		return ResponseEntity.ok(orderServices.updateOrderStatus(id, orderStatus));
	}
	
}
