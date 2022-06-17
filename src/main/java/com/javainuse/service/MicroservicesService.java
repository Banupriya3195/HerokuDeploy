package com.javainuse.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javainuse.dao.MicroservicesRepository;
import com.javainuse.model.Microservices;

@Service
public class MicroservicesService {
	
	private final MicroservicesRepository microservicesRepository;
	
	@Autowired
	public MicroservicesService(MicroservicesRepository microservicesRepository) {
		this.microservicesRepository = microservicesRepository;
	}
	public List<Microservices> hello(){
		return microservicesRepository.findAll();
		
	}
	
	public void addMicroservices(Microservices microservices) {
		// TODO Auto-generated method stub
		
	}
	public Microservices getProductById(Long id) {
        return microservicesRepository.findById(id).orElse(null);
    }
	  

}