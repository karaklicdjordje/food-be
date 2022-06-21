package com.example.demo.factories;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.AddressDTO;
import com.example.demo.dto.RegistrationDTO;
import com.example.demo.entities.User;
import com.example.demo.mappers.CharityMapper;
import com.example.demo.mappers.CorporateMapper;
import com.example.demo.mappers.PersonMapper;
import com.example.demo.mappers.RestaurantMapper;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.AddressService;
import com.example.demo.utils.Encryption;

@Component
public class UserFactory {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AddressService addressService;
	
//	@Autowired
//	private ShoppingCartRepository shoppingCartRepository;
	

	@Transactional
	public void createUser(RegistrationDTO registrationDTO) {
		
		AddressDTO addressDto = registrationDTO.getAddress();
		
		if(addressDto != null) {
			addressService.createAddress(addressDto);
			registrationDTO.setAddress(addressDto);
		}
		
		User user = new User();
		String encriptedPassword = Encryption.getPassEncoded(registrationDTO.getPassword());
		
		switch (registrationDTO.getRole()) {
		case ROLE_ADMINISTRATOR:	
			break;	
		case ROLE_RESTAURANT:
			user = RestaurantMapper.INSTANCE.dtoToEntity(registrationDTO);
			user.setPassword(encriptedPassword);
			userRepository.save(user);
			break;		
		case ROLE_CORPORATE:
			user = CorporateMapper.INSTANCE.dtoToEntity(registrationDTO);
			user.setPassword(encriptedPassword);
			userRepository.save(user);
			break;		
		case ROLE_PERSON:
			user = PersonMapper.INSTANCE.dtoToEntity(registrationDTO);
			user.setPassword(encriptedPassword);
			userRepository.save(user);
			break;
		case ROLE_CHARITY:
			user = CharityMapper.INSTANCE.dtoToEntity(registrationDTO);
			user.setPassword(encriptedPassword);
			userRepository.save(user);
			break;
		default:
			break;
		}
	}
}
		
//		if(user != null) {
//			ShoppingCart shoppingCart = ShoppingCart.builder().user(user).build();
//			shoppingCartRepository.saveAndFlush(shoppingCart);
//		}
//	}

