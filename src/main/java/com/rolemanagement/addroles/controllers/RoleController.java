package com.rolemanagement.addroles.controllers;

import java.util.List;
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

import com.rolemanagement.addroles.entites.RoleEntity;
import com.rolemanagement.addroles.repository.RoleRepository;
import com.rolemanagement.addroles.services.RoleServices;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "roles")
public class RoleController {

	@Autowired
	private RoleServices roleservices;

	@Autowired
	private RoleRepository roleRepository;

	@Operation(description = "Get all roles")
	@GetMapping(path = "/read/roleread")
	public List<RoleEntity> getRoles() {
		return roleservices.getallRoles();
	}

	@Operation(description = "Get role by name")
	@GetMapping(path="/read/getbyname") 
	public ResponseEntity<Object> rolebyname(@RequestParam String roleName) {
		Optional<RoleEntity> RoleEntityByroleName = roleRepository.findRoleEntityByroleName(roleCaps(roleName));
		if (RoleEntityByroleName.isPresent()) {
			return new ResponseEntity<Object>(RoleEntityByroleName,HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}

	@Operation(description = "Add new roles")
	@PostMapping(path = "/write/saverole")
	public ResponseEntity<Object> postRoles(RoleEntity write) {
		Optional<RoleEntity> RoleEntityByroleName = roleRepository.findRoleEntityByroleName(write.getRoleName());
		RoleEntity roleDetails = roleservices.giveRoles(write);
		if (RoleEntityByroleName.isPresent()) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Object>(roleDetails, HttpStatus.OK);
		}
	}
	
	@Operation(description = "Update the roles")
	@PutMapping(path="/update/roles")
	public ResponseEntity<Object> updateRoles(@RequestBody RoleEntity updatename){
		Optional<RoleEntity> RoleEntityByroleName = roleRepository.findRoleEntityByroleName(updatename.getRoleName());
		RoleEntity roleDetails = roleservices.updateRoles(updatename);
		if(RoleEntityByroleName.isPresent()) {
			return new ResponseEntity<Object>(roleDetails,HttpStatus.BAD_REQUEST);		
		}else {
			return new ResponseEntity<Object>(roleDetails, HttpStatus.OK);
		}
			
	}
	

	@Operation(description = "Delete Role by name")
	@DeleteMapping("/delete/{roleName}") 
	public ResponseEntity<Object> deleterRoles(@PathVariable String roleName) {
		Optional<RoleEntity> RolesByroleName = roleRepository.findRoleEntityByroleName(roleName);
		String del = roleservices.deleterole((roleName));
		if (RolesByroleName.isPresent()) {
			return new ResponseEntity<Object>(del,HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	public String roleCaps(String roleName) {
		return roleName.substring(0, 1).toUpperCase() + roleName.substring(1).toLowerCase();
	}
}
