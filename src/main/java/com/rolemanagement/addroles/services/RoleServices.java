package com.rolemanagement.addroles.services;

import java.util.List;

import org.hibernate.query.criteria.internal.expression.function.LengthFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rolemanagement.addroles.entites.RoleEntity;
import com.rolemanagement.addroles.repository.RoleRepository;

@Service
@Transactional
public class RoleServices {

	@Autowired
	private RoleRepository roleRepository;

	public RoleEntity giveRoles(RoleEntity write) {
		String name = write.getRoleName();
		name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
		write.setRoleName(name);
		return roleRepository.save(write);

	}

	@Query("UPDATE RoleEntity SET roleName=INITCAP(roleName)")
	public List<RoleEntity> getallRoles() {
		return roleRepository.findAll();
	}

	public RoleEntity updateRoles(RoleEntity updatename) {
		String name = updatename.getRoleName();
		name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
		updatename.setRoleName(name);
		RoleEntity updateDetails = roleRepository.findRoleEntityByroleName(updatename.getRoleName()).orElse(updatename);
		updateDetails.setRoleName(updatename.getRoleName());
		updateDetails.setRoleDescription(updatename.getRoleDescription());
		updateDetails.setRoleStatus(updatename.isRoleStatus());
		return roleRepository.save(updateDetails);
	}

	public String deleterole(String roleName) {
		roleRepository.deleteByroleName(roleName);
        return "Records removed from " + roleName + " name !!";
	}
	
}

