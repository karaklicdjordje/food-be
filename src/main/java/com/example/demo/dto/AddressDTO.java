package com.example.demo.dto;

import com.example.demo.entities.City;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
	
	private Long id;
	
	private City city;
	
	private String street;
	
	private String addressNumber;

}
