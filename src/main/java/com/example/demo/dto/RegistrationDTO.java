package com.example.demo.dto;

import com.example.demo.entities.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RegistrationDTO {

	private String username;

	private String email;
	
	private String name;
	
	private String surname;

	private String password;

	private Role role;
	
	private String pib;
	
	private String jmbg;
	
	private AddressDTO address;

}
