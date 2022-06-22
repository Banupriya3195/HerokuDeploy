package com.vmi.module.model;

import java.io.Serializable;

public class VmiSearchModel implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String company;
	private String materialNumber;
	private String shortDescription;
	private String description;
	private String partNumber;
	private String uom;
	private String location;

	public String getCompany() {
		return company;
	}

	public String getDescription() {
		return description;
	}

	public String getLocation() {
		return location;
	}

	public String getMaterialNumber() {
		return materialNumber;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public String getUom() {
		return uom;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setMaterialNumber(String materialNumber) {
		this.materialNumber = materialNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

}
