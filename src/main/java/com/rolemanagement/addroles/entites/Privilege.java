package com.rolemanagement.addroles.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Privilege")
public class Privilege {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long pri_Id;
	
	
	
}
