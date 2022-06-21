package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AddressDTO;
import com.example.demo.entities.Address;
import com.example.demo.entities.City;
import com.example.demo.exceptions.NotUniqueException;
import com.example.demo.mappers.AddressMapper;
import com.example.demo.repositories.AddressRepository;
import com.example.demo.repositories.CityRepository;
import com.example.demo.services.AddressService;
import com.example.demo.utils.EntityHelper;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	CityRepository cityRepository;
	
	@Override
	public List<AddressDTO> findAll() {
		List<Address> addresses = addressRepository.findAll();
		List<AddressDTO> addressessDto = new ArrayList<AddressDTO>();
		
		for (Address address : addresses) {
			addressessDto.add(AddressMapper.INSTANCE.entityToDTO(address));
			
			
		}
			
		return addressessDto;
	}

	@Override
	public AddressDTO findByID(Long id) {
		Address address = EntityHelper.getEntity(id, addressRepository);
		return AddressMapper.INSTANCE.entityToDTO(address);
	}
	
	public AddressDTO findByCity (String city) {
		
		Address address = addressRepository.findByCity(city).orElseThrow(null);
		
		return AddressMapper.INSTANCE.entityToDTO(address);
		
	}

	@Override
	public AddressDTO createAddress(AddressDTO addressDto) {
		Address address = AddressMapper.INSTANCE.dtoToEntity(addressDto);
		City city = EntityHelper.getEntity(addressDto.getCity().getId(), cityRepository);
		address.setCity(city);
		addressRepository.save(address);
		
		return AddressMapper.INSTANCE.entityToDTO(address);
	}

	@Override
	public AddressDTO updateAddress(AddressDTO addressDto, Long id) {
		Address address = addressRepository.findById(id).orElseThrow(null);
		address.setAddressNumber(addressDto.getAddressNumber());
		address.setStreet(addressDto.getStreet());
		//address.setCity(addressDto.getCity()); TODO
		
		addressRepository.save(address);
		
		
		return AddressMapper.INSTANCE.entityToDTO(address);
	}

	@Override
	public void deleteAddress(Long id) {
		boolean hasAddress = addressRepository.existsById(id);
		if (!hasAddress) {
			throw new NotUniqueException("Ovo nije dozvoljeno!");
			
		}
		addressRepository.deleteById(id);
	}

}
