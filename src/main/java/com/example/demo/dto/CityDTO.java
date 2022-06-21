package com.example.demo.dto;

import java.util.Set;

import com.example.demo.entities.Address;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CityDTO {
	
	private Long id;
	
	private String name;
	
	private String zipCode;
	
	private Set<Address> address;

}
