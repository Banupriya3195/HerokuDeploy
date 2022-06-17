package com.javainuse.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javainuse.model.Microservices;

@Repository
public interface MicroservicesRepository extends JpaRepository<Microservices , Long>{
	
}
