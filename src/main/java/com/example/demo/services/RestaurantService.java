package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.PersonDTO;
import com.example.demo.dto.RestaurantDTO;

public interface RestaurantService {
	
	List<RestaurantDTO> findAll ();
	
	RestaurantDTO findById (Long id);
	
	RestaurantDTO createRestaurant(RestaurantDTO restaurantDto);
	
	RestaurantDTO updateRestaurant(RestaurantDTO restaurantDto, Long id);
	
	void deleteResaurant (Long id);

	void subscribeUserToRestaurant(Long restaurantId, Long userId); 
	
	

}
