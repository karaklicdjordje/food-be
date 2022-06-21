package com.example.demo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.UserDTO;

import com.example.demo.entities.User;

@Mapper
public interface UserMapper {
	
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	public UserDTO entityToDto(User user);

	public User dtoToEntity(UserDTO userDTO);

}