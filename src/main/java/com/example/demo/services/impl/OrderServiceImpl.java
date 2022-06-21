package com.example.demo.services.impl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.OrderItemDTO;
import com.example.demo.entities.OfferItem;
import com.example.demo.entities.OrderEntity;
import com.example.demo.entities.OrderItem;
import com.example.demo.entities.Restaurant;
import com.example.demo.entities.User;
import com.example.demo.entities.enums.OrderStatus;
import com.example.demo.entities.enums.Role;
import com.example.demo.repositories.OfferItemRepository;
import com.example.demo.repositories.OrderItemRepository;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.OrderServices;
import com.example.demo.utils.EntityHelper;

@Service
public class OrderServiceImpl implements OrderServices {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OfferItemRepository offerItemRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
//	@Autowired
//	private ShoppingCartRepository shoppingCartRepository;
	
	@Value("${number.of.free.charity.launch}")
	private Integer numberOfFreeCharityLaunch;
	
	@Override
	public List<OrderDTO> findAll() {

		List<OrderEntity> orders = orderRepository.findAll();
		
		return orders.stream().map(order -> entityToDTO(order)).collect(Collectors.toList());
	}

	@Override
	public List<OrderDTO> findByUser(Long userId) {
		User user = EntityHelper.getEntity(userId, userRepository);
		List<OrderEntity> orders = orderRepository.findByUser(user);
		return orders.stream().map(order -> entityToDTO(order)).collect(Collectors.toList());
	}

	@Override
	public OrderDTO findById(Long id) {
		OrderEntity order = EntityHelper.getEntity(id, orderRepository);
		
		return entityToDTO(order);
	}

	@Override
	public List<OrderDTO> findByOrderStatus(OrderStatus orderStatus) {
		List<OrderEntity> orders = orderRepository.findByOrderStatus(orderStatus);
		return orders.stream().map(order -> entityToDTO(order)).collect(Collectors.toList());
	}

	@Override
	public List<OrderDTO> findByUserAndOrderStatus(Long userId, OrderStatus orderStatus) {
		User user = EntityHelper.getEntity(userId, userRepository);
		List<OrderEntity> orders = orderRepository.findByUserAndOrderStatus(user, orderStatus);
		return orders.stream().map(order -> entityToDTO(order)).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public OrderDTO createOrder(OrderDTO orderDTO) {
		
		User user = EntityHelper.getEntity(orderDTO.getUserId(), userRepository);
		
		Long orderId = orderRepository.save(new OrderEntity()).getId();
		orderDTO.getOrderItems().forEach(item -> item.setOrderId(orderId));
		
		if(isCharity(user) && orderDTO.getOrderItems().size() > numberOfFreeCharityLaunch) {
			throw new IllegalArgumentException("Charity can get only " + numberOfFreeCharityLaunch + " free launches.");
		}
		OrderEntity order = dtoToEntity(orderDTO);
		order.setId(orderId);
		order.setOrderDateTime(LocalDateTime.now());
		
		if(isCharity(user)) {
			order.getOrderItems().forEach(item -> item.setPrice(0.0));
		} else {
			order.getOrderItems().forEach(item -> item.setPrice(item.getOfferItem().getFood().getPrice() * item.getQuantity()));
		}
		
		Double totalPrice = order.getOrderItems().stream().map(item -> item.getQuantity() * item.getOfferItem().getFood().getPrice())
				.reduce(Double::sum).orElse(null);
	
		
		order.setTotalPrice(totalPrice);
		order.setOrderStatus(OrderStatus.ORDERED);
		orderRepository.save(order);
		
		Set<OrderItem> orderItems = new HashSet<>();
		
		order.getOrderItems().forEach(orderItem -> {
			orderItems.add(orderItemRepository.save(orderItem));
		});
		
		order.setOrderItems(orderItems);
		
		//Currently not use shopping cart
//		ShoppingCart shoppingCart = shoppingCartRepository.findByUser(order.getUser()).orElseThrow(IllegalArgumentException::new);
//		shoppingCart.getShoppingCartItems().clear();
//		shoppingCartRepository.save(shoppingCart);
		
		
		order.getOrderItems().stream().forEach(item -> {
			OfferItem offerItem = item.getOfferItem();
			int quantity = offerItem.getQuantity() - item.getQuantity();
			if(quantity<0){
			throw new IllegalArgumentException("You can only buy " + offerItem.getQuantity() + " " + offerItem.getFood().getName());
		}
			offerItem.setQuantity(quantity);
			offerItemRepository.save(offerItem);
		});
		
		
		return entityToDTO(order);
		
	}
	
	private boolean isCharity(User user) {
		if (user.getRole().equals(Role.ROLE_CHARITY)) {
			return true;
		}
		return false;
	}

	@Override
	public OrderDTO updateOrderStatus(Long id, OrderStatus orderStatus) {
		OrderEntity order = EntityHelper.getEntity(id, orderRepository);
		order.setOrderStatus(orderStatus);
		orderRepository.save(order);
		return entityToDTO(order);
	}
	
	private OrderItemDTO OrderItemEntityToOrderItemDTO (OrderItem orderItem) {
		return OrderItemDTO.builder()
				.id(orderItem.getId())
				.offerItemId(orderItem.getOfferItem().getId())
				.orderId(orderItem.getOrder().getId())
				.price(orderItem.getPrice())
				.quantity(orderItem.getQuantity())
				.build();
	}

		private OrderItem OrderItemDtoToOrderItemEntity (OrderItemDTO orderItemDTO) {
			OrderEntity order = null;
			if(orderItemDTO.getOrderId() != null) {
				order = orderRepository.findById(orderItemDTO.getOrderId()).orElse(null);
			}
			
			OfferItem offerItem = EntityHelper.getEntity(orderItemDTO.getOfferItemId(), offerItemRepository);
			return OrderItem.builder()
					.id(orderItemDTO.getId())
					.offerItem(offerItem)
					.order(order)
					.price(orderItemDTO.getPrice())
					.quantity(orderItemDTO.getQuantity())
					.build();
		}
		
		private OrderDTO entityToDTO (OrderEntity order) {
			
			Set<OrderItemDTO> orderItems = order.getOrderItems().stream().map(orderItem -> OrderItemEntityToOrderItemDTO(orderItem)).collect(Collectors.toSet());
			
			return OrderDTO.builder()
					.id(order.getId())
					.orderDateTime(order.getOrderDateTime())
					.orderItems(orderItems)
					.orderStatus(order.getOrderStatus())
					.userId(order.getUser().getId())
					.restaurantId(order.getRestaurant().getId())
					.totalPrice(order.getTotalPrice())
					.build();
			
		}
			
			private OrderEntity dtoToEntity (OrderDTO orderDTO) {
				
				Restaurant restaurant = (Restaurant) EntityHelper.getEntity(orderDTO.getRestaurantId(), userRepository);
				User user = EntityHelper.getEntity(orderDTO.getUserId(), userRepository);
				
				Set<OrderItem> orderItems = orderDTO.getOrderItems().stream().map(orderItem -> OrderItemDtoToOrderItemEntity(orderItem)).collect(Collectors.toSet());
				
				return OrderEntity.builder()
						.id(orderDTO.getId())
						.orderDateTime(orderDTO.getOrderDateTime())
						.orderItems(orderItems)
						.orderStatus(orderDTO.getOrderStatus())
						.user(user)
						.restaurant(restaurant)
						.totalPrice(orderDTO.getTotalPrice())
						.build();
			}

}
