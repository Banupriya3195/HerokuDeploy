package com.javainuse.config;
import java.util.List;


import org.springframework.boot.CommandLineRunner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.javainuse.dao.MicroservicesRepository;
import com.javainuse.model.Microservices;

@Configuration
public class MicroservicesConfig  {
	
	@Bean


	CommandLineRunner commandLineRunner(MicroservicesRepository repository) {
		return args -> {
			Microservices obj1 = new Microservices(
					1L,
					"SPN0008906",
					4387075,
					"HEAD",
					-1
			
			);
			repository.saveAll(List.of(obj1));
			}; 
	}
}