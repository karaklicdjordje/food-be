package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.OfferItem;

public interface OfferItemRepository extends JpaRepository<OfferItem, Long> {

}
