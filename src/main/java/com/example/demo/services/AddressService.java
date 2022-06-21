package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.AddressDTO;

public interface AddressService{
	
	List<AddressDTO> findAll ();
	
	AddressDTO findByID (Long id);
	
	AddressDTO createAddress (AddressDTO addressDto);
	
	AddressDTO updateAddress (AddressDTO addressDto, Long id);
	
	void deleteAddress (Long id);

	
}
