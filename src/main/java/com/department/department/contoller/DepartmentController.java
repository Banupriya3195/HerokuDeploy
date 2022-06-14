package com.department.department.contoller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.department.department.entity.Department;
import com.department.department.message.ResponseMessage;
import com.department.department.repository.DepartmentRepository;
import com.department.department.service.DepartmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private DepartmentRepository departmentRepository;
	
	
	@Operation(description = "Get all Department")
	@GetMapping("/showall")
//	@RequestMapping(path = "/showall", method = { RequestMethod.GET }, produces = {MediaType.APPLICATION_JSON_VALUE })
	public List<Department> findAllDepartments()
	{
		return departmentService.getDepartments();
		
	}
	
	@Operation(description = "Add new Records")
//	@PostMapping("/add")
	@RequestMapping(path = "/add", method = { RequestMethod.POST }, produces = {MediaType.APPLICATION_JSON_VALUE })

	public ResponseEntity<Object> postRoles(Department d) {
		Optional<Department> DepartmentBydepartmentName = departmentRepository.findDepartmentBydepartmentName(d.getDepartmentName());
			Department roleDetails = departmentService.saveDepartment(d);

		if (DepartmentBydepartmentName.isPresent()) {
			System.out.println("Role name is already exists");
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Object>(roleDetails, HttpStatus.OK);
		}
	}


	@Operation(description = "Get Department by name")
	@GetMapping("/read/{departmentName}") 
	public ResponseEntity<Object> rolebyname(@PathVariable String departmentName) {
		Optional<Department> DepartmentBydepartmentName = departmentRepository.findDepartmentBydepartmentName(namechange(departmentName));
		if (DepartmentBydepartmentName.isPresent()) {
			return new ResponseEntity<Object>(DepartmentBydepartmentName,HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(DepartmentBydepartmentName, HttpStatus.BAD_REQUEST);
		}
	}	
	
	@Operation(description = "Update Department")
	@PutMapping("/update")
	public ResponseEntity<Object> updateRoles( Department department){
		Optional<Department> RoleEntityByroleName = departmentRepository.findDepartmentBydepartmentName(department.getDepartmentName());
		Department roleDetails = departmentService.updateDepartment(department);
		if(RoleEntityByroleName.isPresent()) {
			return new ResponseEntity<Object>(roleDetails,HttpStatus.BAD_REQUEST);		
		}else {
			return new ResponseEntity<Object>(roleDetails, HttpStatus.OK);
		}
			
	}

	@Operation(description = "Delete Department by name")
	@DeleteMapping("/delete/{departmentName}") 	
	public ResponseEntity<Object> deleteName(@PathVariable String departmentName) {
		Optional<Department> DepartmentBydepartmentName = departmentRepository.findDepartmentBydepartmentName(namechange(departmentName));
		String del = departmentService.deleteDepartment(namechange(departmentName));
		if (DepartmentBydepartmentName.isPresent()) {
			return new ResponseEntity<Object>(del,HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}

	
	public String namechange(String departmentName)
	{
		return departmentName.substring(0, 1).toUpperCase() + departmentName.substring(1).toLowerCase();
		
	}
	
}
	
