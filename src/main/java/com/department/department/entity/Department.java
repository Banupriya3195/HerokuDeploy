package com.department.department.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;


@Entity
@AllArgsConstructor
//@Data
@NoArgsConstructor
@Table(name = "Department")
public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int departmentId;
	@Column(name = "departmentName", unique = true, nullable = false)
	private String departmentName;
	private boolean status;
	
   
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date timestamp = new Date();

//	@PrePersist
//	private void onCreate() { 
//	    timestamp = new Date();
//	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}	

	public Date getTimestamp() {
		return timestamp;
	}
//	public void setTimestamp(Date timestamp) {
//	this.timestamp = timestamp;
//}
//
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}


	
	

	

	
	

}
