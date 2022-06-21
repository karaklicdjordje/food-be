package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor @Getter @Setter
public class UserTokenStateDTO {

	private String accessToken;
    private Long expiresIn;

}
