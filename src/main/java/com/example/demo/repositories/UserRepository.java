package com.example.demo.repositories;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>   {
	
	Optional<User> findByEmail(String email);
	
	@Query("SELECT email FROM User")
	List<String> findAllEmail();



}
