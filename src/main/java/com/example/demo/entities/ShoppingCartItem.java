//package com.example.demo.entities;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class ShoppingCartItem {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//		
//	private int quantity;
//	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "shoppingCart_id")
//	private ShoppingCart shoppingCart;
//	
//	private double price;
//		
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "offer_item_id")
//	private OfferItem offerItem;
//
//}
