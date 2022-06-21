//package com.example.demo.mappers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.example.demo.dto.OrderItemDTO;
//import com.example.demo.entities.OfferItem;
//import com.example.demo.entities.OrderEntity;
//import com.example.demo.entities.OrderItem;
//import com.example.demo.repositories.OfferItemRepository;
//import com.example.demo.repositories.OrderRepository;
//import com.example.demo.utils.EntityHelper;
//
//@Component
//public class CustomOrderItemMapper {
//	
//	@Autowired
//	private OfferItemRepository offerItemRepository;
//	
//	@Autowired
//	private OrderRepository orderRepository;
//
//	
//public OrderItemDTO entityToDTO (OrderItem orderItem) {
//	return OrderItemDTO.builder()
//			.id(orderItem.getId())
//			.offerItemId(orderItem.getOfferItem().getId())
//			.orderId(orderItem.getOrder().getId())
//			.price(orderItem.getPrice())
//			.quantity(orderItem.getQuantity())
//			.build();
//}
//
//	public OrderItem dtoToEntity (OrderItemDTO orderItemDTO) {
//		OrderEntity order = null;
//		if(orderItemDTO.getOrderId() != null) {
//			order = orderRepository.findById(orderItemDTO.getOrderId()).orElse(null);
//		}
//		
//		OfferItem offerItem = EntityHelper.getEntity(orderItemDTO.getOfferItemId(), offerItemRepository);
//		return OrderItem.builder()
//				.id(orderItemDTO.getId())
//				.offerItem(offerItem)
//				.order(order)
//				.price(orderItemDTO.getPrice())
//				.quantity(orderItemDTO.getQuantity())
//				.build();
//	}
//
//}
