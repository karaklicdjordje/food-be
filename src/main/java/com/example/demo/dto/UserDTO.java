package com.example.demo.dto;

import com.example.demo.entities.Address;
import com.example.demo.entities.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	private Long id;

	private String username;

	private String email;

	private Role role;
	
	private String pib;
	
	private String jmbg;
	
	private AddressDTO address;

}
