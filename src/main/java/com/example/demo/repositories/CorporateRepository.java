package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Corporate;
@Repository
public interface CorporateRepository extends JpaRepository<Corporate, Long>{

}
