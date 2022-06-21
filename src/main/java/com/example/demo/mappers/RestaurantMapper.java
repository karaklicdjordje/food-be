package com.example.demo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.RegistrationDTO;
import com.example.demo.dto.RestaurantDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entities.Restaurant;

@Mapper
public interface RestaurantMapper {
	
	RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

	public UserDTO entityToDto(Restaurant restaurant);

	public Restaurant dtoToEntity(UserDTO userDTO);
	
	public RestaurantDTO entitysToDTO (Restaurant retaurant);
	
	public Restaurant dtoToEntity (RestaurantDTO restaurantDto);
	
	public Restaurant dtoToEntity(RegistrationDTO registrationDTO);

}
