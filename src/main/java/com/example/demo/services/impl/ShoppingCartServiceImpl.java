//package com.example.demo.services.impl;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//
//import com.example.demo.dto.ShoppingCartDTO;
//import com.example.demo.dto.ShoppingCartItemDTO;
//import com.example.demo.entities.Offer;
//import com.example.demo.entities.ShoppingCart;
//import com.example.demo.entities.ShoppingCartItem;
//import com.example.demo.entities.User;
//import com.example.demo.exceptions.UserNotFoundException;
//import com.example.demo.mappers.ShoppingCartItemMapper;
//import com.example.demo.mappers.ShoppingCartMapper;
//import com.example.demo.repositories.ShoppingCartItemRepository;
//import com.example.demo.repositories.ShoppingCartRepository;
//import com.example.demo.repositories.UserRepository;
//import com.example.demo.services.ShoppingCartService;
//
//@Service
//public class ShoppingCartServiceImpl implements ShoppingCartService{
//	
//	@Autowired
//	ShoppingCartRepository shoppingCartRepository;
//	
//	@Autowired
//	ShoppingCartItemRepository shoppingCartItemRepository;
//	
//	@Autowired
//	ShoppingCartService shoppingCartService;
//	
//	@Autowired
//	UserRepository userRepository;
//	
//	
//	public ShoppingCartDTO createShoppingCart (ShoppingCartDTO shoppingCartDto) {
//		
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		String userEmail = auth.getPrincipal().toString();
//		Optional<User> userOptional = userRepository.findByEmail(userEmail);
//		
//		if (userOptional == null) {
//			throw new UserNotFoundException("User ne postoji");
//		}
//		
//		ShoppingCart shoppingCart = ShoppingCartMapper.INSTANCE.dtoToEntity(shoppingCartDto);
//		shoppingCart.setUser(userOptional.get());
//		shoppingCart.setTotalPrice(shoppingCartDto.getTotalPrice());
//		Set<ShoppingCartItem> shoppingCardItems = new HashSet<>();
//		for (ShoppingCartItemDTO shoppingCartItemDto : shoppingCartDto.getShoppingCartItem()) {
//			shoppingCardItems.add(ShoppingCartItemMapper.INSTANCE.dtoToEntity(shoppingCartItemDto));
//		}
//		shoppingCart.setShoppingCartItems(shoppingCardItems);
//		
//		shoppingCartRepository.save(shoppingCart);
//		
////		shoppingCartDto.getShoppingCartItemDto().stream().map(itemDto -> ShoppingCartItemMapper.INSTANCE.dtoToEntity(itemDto)).forEach(item-> {
////			item.setShoppingCart(shoppingCart);
////			shoppingCartItemRepository.save(item);
////			shoppingCart.getShoppingCartItems().add(item);
//		
//		return null;}
//		
//		
//		
//		
//	
//	
//	
//
//	@Override
//	public List<ShoppingCartDTO> findAll() {
//		List<ShoppingCart> shoppingCarts = shoppingCartRepository.findAll();
//		return shoppingCarts.stream().map(shoppingCart -> ShoppingCartMapper.INSTANCE.entityToDTO(shoppingCart)).collect(Collectors.toList());
//	}
//
//	@Override
//	public ShoppingCartDTO findByID(Long id) {
//		ShoppingCart shoppingCart = shoppingCartRepository.getById(id);
//		return ShoppingCartMapper.INSTANCE.entityToDTO(shoppingCart);
//	}
//
//	@Override
//	public void deleteShoppingCartDTO(Long id) {
//		shoppingCartRepository.deleteById(id);
//	}
//	
//	public List<ShoppingCartDTO> getShoppingCartByUser(Long id) {
//		User user = userRepository.findById(id).orElseThrow();
//		return shoppingCartRepository.findByUser(user).stream().map(order -> ShoppingCartMapper.INSTANCE.entityToDTO(order)).collect(Collectors.toList());
//	}
//	
//	
//
//
//
//
//}
