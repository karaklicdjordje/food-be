package com.example.demo.utils;

import org.springframework.data.jpa.repository.JpaRepository;

public class EntityHelper {
	
	//Genericka metoda
	
	//Kreirana staticka metoda koja vraca genericku klasu T
	// kao parametre prosledjujemo id i repository koji mora naslediti JPA repository koji prihvata Klasu T i taj id
	
	public static <T> T getEntity(Long id, JpaRepository<T, Long> repository) {
		return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Entity with id " + id + " does not exist."));
	}

}
