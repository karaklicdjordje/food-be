package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.RestaurantDTO;
import com.example.demo.entities.Restaurant;
import com.example.demo.entities.User;
import com.example.demo.entities.enums.Role;
import com.example.demo.exceptions.NotUniqueException;
import com.example.demo.mappers.RestaurantMapper;
import com.example.demo.repositories.RestaurantRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.RestaurantService;
import com.example.demo.utils.EntityHelper;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<RestaurantDTO> findAll() { 
		
		List<Restaurant> restaurants = restaurantRepository.findAll();
		
		List<RestaurantDTO> restaurantsDto = new ArrayList<RestaurantDTO>();
		
		for (Restaurant restaurant : restaurants)
			restaurantsDto.add(RestaurantMapper.INSTANCE.entitysToDTO(restaurant));
		
		return restaurantsDto;
	}

	@Override
	public RestaurantDTO findById(Long id) {
		Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(null);		
		return RestaurantMapper.INSTANCE.entitysToDTO(restaurant);
		
	}

	@Override
	public RestaurantDTO createRestaurant(RestaurantDTO restaurantDto) {
		Restaurant restaurant = RestaurantMapper.INSTANCE.dtoToEntity(restaurantDto);
		restaurant.setRole(Role.ROLE_RESTAURANT);
		restaurantRepository.save(restaurant);
		
		return RestaurantMapper.INSTANCE.entitysToDTO(restaurant);
	}

	@Override
	public RestaurantDTO updateRestaurant(RestaurantDTO restaurantDto, Long id) {
		Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(null);
		restaurant.setName(restaurantDto.getName());
		//restaurant.setAddress(restaurantDto.getAddress()); TODO
		restaurant.setEmail(restaurantDto.getEmail());
		restaurant.setOffers(restaurant.getOffers());
		restaurant.setPassword(restaurant.getPassword());
		restaurantRepository.save(restaurant);
		return RestaurantMapper.INSTANCE.entitysToDTO(restaurant);
	}

	@Override
	public void deleteResaurant(Long id) {
		
		boolean hasRestaurant = restaurantRepository.existsById(id);
		if (!hasRestaurant) {
			throw new NotUniqueException("Ovo nije dozvoljeno!");
			
		}
		restaurantRepository.deleteById(id);
		
	}

	@Override
	public void subscribeUserToRestaurant(Long restaurantId, Long userId) {
		Restaurant restaurant = EntityHelper.getEntity(restaurantId, restaurantRepository);
		User user = EntityHelper.getEntity(userId, userRepository);
		
		restaurant.getUsers().add(user);
		restaurantRepository.save(restaurant);
		
	}

}
