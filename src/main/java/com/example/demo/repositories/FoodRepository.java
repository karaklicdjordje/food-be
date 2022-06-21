package com.example.demo.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Food;
import com.example.demo.entities.enums.TypeOfFood;
@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
	
	public Optional<Food> findByTypeOfFood (TypeOfFood typeOfFood); //properti se mora zvati isto kao i atribut;

}
