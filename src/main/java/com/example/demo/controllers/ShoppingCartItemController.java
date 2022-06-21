//package com.example.demo.controllers;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.demo.dto.ShoppingCartItemDTO;
//import com.example.demo.services.ShoppingCartItemService;
//
//import io.swagger.v3.oas.annotations.parameters.RequestBody;
//
//@RequestMapping("/shoopingcartitem")
//@RestController
//
//public class ShoppingCartItemController {
//
//	@Autowired
//	ShoppingCartItemService shoppingCartItemService;
//	
//	@GetMapping
//	public ResponseEntity<List<ShoppingCartItemDTO>> findAll(){
//		return new ResponseEntity<List<ShoppingCartItemDTO>>(shoppingCartItemService.findAll(), HttpStatus.OK);
//	
//	}
//	@GetMapping("{id}")
//	public ResponseEntity<ShoppingCartItemDTO> findById(@PathVariable Long id) {
//		return new ResponseEntity<>(shoppingCartItemService.findById(id), HttpStatus.OK);
//	}
//	
//	@DeleteMapping("{id}")
//	public ResponseEntity<HttpStatus> deleteShoppingCartItem(@PathVariable Long id) {
//		shoppingCartItemService.deleteShoppingCartItem(id);
//		return new ResponseEntity<>(HttpStatus.OK);
//
//	}
//	
//	@PostMapping()
//	public ResponseEntity<Void> createShoppingCartItem(@RequestBody ShoppingCartItemDTO shoppingCartItemDto){
//		shoppingCartItemService.createShoopingCartItem(shoppingCartItemDto);
//		return ResponseEntity.ok().build();
//	}
//	
//}
//	
//
//
//
