//package com.example.demo.entities;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//
//
//@Entity
//@Table(name = "shopping_cart")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class ShoppingCart {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//	
//	@OneToOne(cascade = CascadeType.REFRESH)
//	@JoinColumn(name = "user_id", referencedColumnName = "id")
//	private User user;
//
//	
//	@OneToMany(mappedBy = "shoppingCart", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
//	@JsonIgnore
//	private Set<ShoppingCartItem> shoppingCartItems = new HashSet<>();
//	
//	private double totalPrice;
//}
