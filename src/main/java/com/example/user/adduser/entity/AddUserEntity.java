package com.example.user.adduser.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "NewUser")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddUserEntity{
	@Column(name="FirstName") 
	public String firstName;
	@Column(name="LastName")
	public String lastName;
	@Email
	@Column(name="Email")
	public String email;
	
	@Column(name="UserId")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long userId;
	
	@Column(name="Password")
	@NonNull
    @Size(min = 4, max = 15)
	private String password;
	private String confirmpassword;
	@Column(name="ContactNo")
	private long contactno;
	@Column(name="Location")
	public String location;
	@Column(name="Department")
	public String department;
	@Column(name="Bussiness")
	public String bussiness;
	@Column(name="ZipCode")
	public int zipcode;
	@Column(name="Plant")
	public String plant;
	@Column(name="Status")
	public boolean status;
	@Column(name="verification")
	public boolean verification;
//	@Lob
	@Column(name = "image", unique = false, nullable = false, length = 100000)
    private String image;

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	public long getContactno() {
		return contactno;
	}
	public void setContactno(long contactno) {
		this.contactno = contactno;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getBussiness() {
		return bussiness;
	}
	public void setBussiness(String bussiness) {
		this.bussiness = bussiness;
	}
	public int getZipcode() {
		return zipcode;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	public String getPlant() {
		return plant;
	}
	public void setPlant(String plant) {
		this.plant = plant;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public boolean isVerification() {
		return verification;
	}
	public void setVerification(boolean verification) {
		this.verification = verification;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
}


