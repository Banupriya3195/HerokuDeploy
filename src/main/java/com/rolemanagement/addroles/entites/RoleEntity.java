package com.rolemanagement.addroles.entites;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Roles")

public class RoleEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long role_Id;
	@Column(name = "roleName" ,unique = true ,length = 15 ,nullable = false ,columnDefinition = "initcap")
	private String roleName;
	@Column(name = "roleDescription" ,length = 250)
	private String roleDescription;
	private boolean roleStatus;
	
	
	public long getRole_Id() {
		return role_Id;
	}
	public void setRole_Id(long role_Id) {
		this.role_Id = role_Id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	public boolean isRoleStatus() {
		return roleStatus;
	}
	public Set<Privilege> getRoles() {
		return roles;
	}
	public void setRoles(Set<Privilege> roles) {
		this.roles = roles;
	}
	public void setRoleStatus(boolean roleStatus) {
		this.roleStatus = roleStatus;
	}
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name ="Roles_Privilege", joinColumns = @JoinColumn (name="role_Id"), inverseJoinColumns = @JoinColumn (name="pri_Id"))
	private Set<Privilege> roles=new HashSet<>();
	} 


