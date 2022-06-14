package com.department.department.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.department.department.entity.Department;

@Repository
@Transactional
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
	
//	@Modifying
//	@Query("UPDATE Department SET departmentName=INITCAP(departmentName)")
//	Department initcapdepartmentName(String departmentname);

	Optional<Department> findDepartmentBydepartmentName(String departmentName);
	
	public String deleteBydepartmentName(String departmentName);
	
	
	
}
