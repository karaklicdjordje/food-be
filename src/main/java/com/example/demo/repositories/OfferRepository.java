package com.example.demo.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.demo.entities.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
	
	public Optional<Offer> findByDate(LocalDate date);
	
	public List<Offer> findByExpired(boolean expired);

}
