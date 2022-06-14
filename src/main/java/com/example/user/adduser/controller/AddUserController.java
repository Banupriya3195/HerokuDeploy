package com.example.user.adduser.controller;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.user.adduser.entity.AddUserEntity;
import com.example.user.adduser.repository.AddUserRepo;
import com.example.user.adduser.repository.EditUserRepo;
import com.example.user.adduser.service.AddUserService;



@RestController
@RequestMapping("/api/user")
public class AddUserController {

	@Autowired
	private AddUserRepo newUserRepository;
	
	@Autowired 
	AddUserService adduserService;
	@Autowired
	EditUserRepo editUserRepo;
	

public static String uploaddir=System.getProperty("user.dir")+"/src/main/resources/image";
	@PostMapping(value="/saveuser", consumes= "multipart/form-data")
	
	public AddUserEntity saveuser(@RequestParam String firstName,@RequestParam String lastName,@RequestParam String email,
			@RequestParam String password,@RequestParam String confirmpassword,@RequestParam long contactno,
			@RequestParam String location,@RequestParam String department,@RequestParam String bussiness,
			@RequestParam int zipcode,@RequestParam String plant,@RequestParam boolean status,
			@RequestParam boolean verification,@RequestParam(value = "image")  MultipartFile file) {
		
		AddUserEntity adduserEntity = new AddUserEntity();
		adduserEntity.setFirstName(firstName);
		adduserEntity.setLastName(lastName);
		adduserEntity.setEmail(email);
		adduserEntity.setPassword(password);
		adduserEntity.setContactno(contactno);
		adduserEntity.setLocation(location);
		adduserEntity.setDepartment(department);
		adduserEntity.setBussiness(bussiness);
		adduserEntity.setZipcode(zipcode);
		adduserEntity.setPlant(plant);
		adduserEntity.setStatus(status);
		adduserEntity.setVerification(verification);
		String filename= adduserEntity.getUserId()+file.getOriginalFilename();
		Path path=Paths.get(uploaddir,filename);
		String email1=adduserEntity.getEmail();
		AddUserService.isEmailValid(email1);
		adduserService.senttextemail(email1);
		System.out.println(email1);
		long contactno1=adduserEntity.getContactno();
		String contactno2=String.valueOf(contactno1);
		adduserService.validate(contactno2);
		try {
			Files.write(path, file.getBytes());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		adduserEntity.setImage(filename);
		
		//Base64.getEncoder().encodeToString(image.getLink())
		
		System.out.println(filename);
		
		return newUserRepository.save(adduserEntity);

	}

@PutMapping(value="{email}",consumes= "multipart/form-data")
public ResponseEntity<AddUserEntity> updateUser(@PathVariable String email, @RequestBody AddUserEntity edituser, @RequestParam(value = "image")  MultipartFile file) {
	
	try {
		AddUserEntity updateUser = editUserRepo.findByEmail(email);
		
//		String filename= updateUser.getUserId()+file.getOriginalFilename();
//		Path path=Paths.get(uploaddir,filename);
//		Files.write(path, file.getBytes());
//		updateUser.setImage(edituser.getImage(filename));
		
		updateUser.setFirstName(edituser.getFirstName());
		updateUser.setLastName(edituser.getLastName());
		updateUser.setPassword(edituser.getPassword());
		updateUser.setContactno(edituser.getContactno());
		updateUser.setLocation(edituser.getLocation());
		updateUser.setDepartment(edituser.getDepartment());
		updateUser.setBussiness(edituser.getBussiness());
		updateUser.setZipcode(edituser.getZipcode());
		updateUser.setPlant(edituser.getPlant());
		updateUser.setStatus(edituser.isStatus());
		updateUser.setVerification(edituser.isVerification());
		updateUser.setImage(edituser.getImage());
		editUserRepo.save(updateUser);
        return ResponseEntity.ok(updateUser);
        
	} catch (Exception e) {
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

@GetMapping("get/{email}")
public ResponseEntity<AddUserEntity> getByemail(@PathVariable String email) {
    
    Optional<AddUserEntity> detail = editUserRepo.getByEmail(email);
	if (detail.isPresent()) {
		return new ResponseEntity<AddUserEntity>(detail.get(), HttpStatus.OK);
	}
	
	return new ResponseEntity<AddUserEntity>(HttpStatus.NOT_FOUND);
    
}

@DeleteMapping("/delete/{email}")
public ResponseEntity<HttpStatus> deleteuser(@PathVariable String email) {
	try {
		Optional<AddUserEntity> detail = editUserRepo.getByEmail(email);
		if (detail.isPresent()) {
			editUserRepo.delete(detail.get());
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	} catch (Exception e) {
		return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
}