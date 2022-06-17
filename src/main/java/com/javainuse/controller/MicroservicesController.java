package com.javainuse.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javainuse.model.Microservices;
import com.javainuse.service.MicroservicesService;


@RestController
public class MicroservicesController {
	

	private final MicroservicesService microservicesService;
	
	@Autowired
	public MicroservicesController(MicroservicesService microservicesService) {
		super();
		this.microservicesService = microservicesService;
	}

	@GetMapping(path="/getall")
	public List<Microservices> hello() {
		return microservicesService.hello();
		
	}
	
	 
	@PostMapping(path="/post")
	public void registerNewMicroservices(Microservices microservices) {
		microservicesService.addMicroservices(microservices);
	}
	
	@GetMapping("/getById/{id}")
    public Microservices findProductById(@PathVariable Long id) {
        return microservicesService.getProductById(id);
    }

} 