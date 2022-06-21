package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Charity;
@Repository
public interface CharityRepository extends JpaRepository<Charity, Long>{

}
