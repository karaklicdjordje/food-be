//package com.example.demo.mappers;
//
//import java.util.Set;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.example.demo.dto.OrderDTO;
//import com.example.demo.dto.OrderItemDTO;
//import com.example.demo.entities.OrderEntity;
//import com.example.demo.entities.OrderItem;
//import com.example.demo.entities.Restaurant;
//import com.example.demo.entities.User;
//import com.example.demo.repositories.UserRepository;
//import com.example.demo.utils.EntityHelper;
//
//@Component
//public class CustomOrderMapper {
//	
//	@Autowired
//	private UserRepository userRepository;
//	
//	@Autowired
//	private CustomOrderItemMapper orderItemMapper;
//	
//public OrderDTO entityToDTO (OrderEntity order) {
//	
//	Set<OrderItemDTO> orderItems = order.getOrderItems().stream().map(orderItem -> orderItemMapper.entityToDTO(orderItem)).collect(Collectors.toSet());
//	
//	return OrderDTO.builder()
//			.id(order.getId())
//			.orderDateTime(order.getOrderDateTime())
//			.orderItems(orderItems)
//			.orderStatus(order.getOrderStatus())
//			.userId(order.getUser().getId())
//			.restaurantId(order.getRestaurant().getId())
//			.totalPrice(order.getTotalPrice())
//			.build();
//	
//}
//	
//	public OrderEntity dtoToEntity (OrderDTO orderDTO) {
//		
//		Restaurant restaurant = (Restaurant) EntityHelper.getEntity(orderDTO.getRestaurantId(), userRepository);
//		User user = EntityHelper.getEntity(orderDTO.getUserId(), userRepository);
//		
//		Set<OrderItem> orderItems = orderDTO.getOrderItems().stream().map(orderItem -> orderItemMapper.dtoToEntity(orderItem)).collect(Collectors.toSet());
//		
//		return OrderEntity.builder()
//				.id(orderDTO.getId())
//				.orderDateTime(orderDTO.getOrderDateTime())
//				.orderItems(orderItems)
//				.orderStatus(orderDTO.getOrderStatus())
//				.user(user)
//				.restaurant(restaurant)
//				.totalPrice(orderDTO.getTotalPrice())
//				.build();
//	}
//
//}
