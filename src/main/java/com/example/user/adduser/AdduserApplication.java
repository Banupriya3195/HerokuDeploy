package com.example.user.adduser;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.user.adduser.controller.AddUserController;

@SpringBootApplication
public class AdduserApplication {

	public static void main(String[] args) {
		
		new File(AddUserController.uploaddir).mkdir();
		SpringApplication.run(AdduserApplication.class, args);
	}
	
}
