package com.rolemanagement.addroles.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rolemanagement.addroles.entites.RoleEntity;

@Transactional
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long>{
	
	
		Optional<RoleEntity> findRoleEntityByroleName(String roleName);
	
		public String deleteByroleName(String roleName);
	
	
}
