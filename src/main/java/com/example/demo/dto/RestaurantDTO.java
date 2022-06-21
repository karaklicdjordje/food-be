package com.example.demo.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDTO {

	
	private Long id;
	
	private String pib;
	
	private String name;
	
	private String email;
	
	private Set<UserDTO> users;
	
//	private List<OfferDTO> offers;
	
	private AddressDTO address;
	

}
