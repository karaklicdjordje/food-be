package com.example.demo.services.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dto.RegistrationDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entities.User;
import com.example.demo.exceptions.NotUniqueException;
import com.example.demo.mappers.RegistrationMapper;
import com.example.demo.mappers.UserMapper;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService{
	
	protected final Log LOGGER = LogFactory.getLog(getClass());
	
	@Autowired
	UserRepository userRepository;
	@Lazy
	@Autowired
	RegistrationMapper registrationMapper;
	
	@Qualifier("userDetailsServiceImpl")
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public RegistrationDTO createUser(RegistrationDTO registrationDto) throws Exception {
		Optional<User> existUser = userRepository.findByEmail(registrationDto.getEmail());
		
		if (existUser != null || registrationDto.getPassword() == null || registrationDto.getPassword() == "")
			
			throw new Exception ("User with that email already exist or password is incorrect");
	
		else {
			User newUser = registrationMapper.dtoToEntity(registrationDto);
			
			
			
			userRepository.saveAndFlush(newUser);
		}
	
	
				
		return registrationDto;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByEmail(username);
		if (!user.isPresent()) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		} else {
			return user.get();
		}
	}
	
	@Override
	public List<UserDTO> findAll() {
		
		List<User> users = userRepository.findAll();
		List<UserDTO> usersDTO = new ArrayList<>();
		
		for (User user : users) {
			
			usersDTO.add(UserMapper.INSTANCE.entityToDto(user));
		}
		return usersDTO;
	}
	
	public UserDTO findByID(Long id) {
		
		User user = userRepository.findById(id).orElseThrow(null);
		
		return UserMapper.INSTANCE.entityToDto(user);
		
	}
	
	public void activatedUser (Long id) {
		
		boolean hasUser = userRepository.existsById(id);
		if (!hasUser) {
			throw new NotUniqueException("Ovo nije dozvoljeno!");
		}
		userRepository.deleteById(id);
			
		}
	}


