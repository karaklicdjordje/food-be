package com.example.demo.entities.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority{

	ROLE_ADMINISTRATOR, ROLE_RESTAURANT, ROLE_CORPORATE, ROLE_PERSON, ROLE_CHARITY;
	
	@Override
	public String getAuthority() {
		return name();
	}
}
