package com.example.demo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.AddressDTO;
import com.example.demo.entities.Address;


@Mapper
public interface AddressMapper {
	
	AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);
	
	public AddressDTO entityToDTO (Address address);
	
	public Address dtoToEntity (AddressDTO addressDTo);

}
