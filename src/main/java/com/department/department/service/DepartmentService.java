package com.department.department.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.department.department.entity.Department;
import com.department.department.repository.DepartmentRepository;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	public Department saveDepartment(Department d) {

		String name = d.getDepartmentName();
		name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
		d.setDepartmentName(name);
		return departmentRepository.save(d);
	}
	
	@Query("UPDATE Department SET departmentName=INITCAP(departmentName)")
	public List<Department> getDepartments() {
		return departmentRepository.findAll();

	}
	
	public String deleteDepartment(String departmentName) {
        departmentRepository.deleteBydepartmentName(departmentName);
        return "Records removed from " + departmentName + " name Successfully !!";
    }
	
	 public Department updateDepartment(Department department) {
		 String departmentName = department.getDepartmentName();
			departmentName = departmentName.substring(0, 1).toUpperCase() + departmentName.substring(1).toLowerCase();
			department.setDepartmentName(departmentName);

	        Department existingDepartment = departmentRepository.findById(department.getDepartmentId()).orElse(null);
	        existingDepartment.setDepartmentName(department.getDepartmentName());
//	        existingDepartment.setTimestamp(department.getTimestamp());
	        return departmentRepository.save(existingDepartment);
	    }
	
	

}
