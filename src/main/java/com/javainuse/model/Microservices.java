package com.javainuse.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class Microservices {
	@Id
	@SequenceGenerator(
			name = "Microservices_Sequence",
			sequenceName = "Microservices_Sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "microservices_Sequence"
	)
	private long ID;
	private String Itemcode;
	private int Materialcode;
	private String Noun;
	private int ItemStatus;
	public Microservices() {
		super();
	}
	public Microservices(long iD, String itemcode, int materialcode, String noun, int itemStatus) {
		super();
		ID = iD;
		Itemcode = itemcode;
		Materialcode = materialcode;
		Noun = noun;
		ItemStatus = itemStatus;
	}
	public Microservices(String itemcode, int materialcode, String noun, int itemStatus) {
		super();
		Itemcode = itemcode;
		Materialcode = materialcode;
		Noun = noun;
		ItemStatus = itemStatus;
	}
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public String getItemcode() {
		return Itemcode;
	}
	public void setItemcode(String itemcode) {
		Itemcode = itemcode;
	}
	public int getMaterialcode() {
		return Materialcode;
	}
	public void setMaterialcode(int materialcode) {
		Materialcode = materialcode;
	}
	public String getNoun() {
		return Noun;
	}
	public void setNoun(String noun) {
		Noun = noun;
	}
	public int getItemStatus() {
		return ItemStatus;
	}
	public void setItemStatus(int itemStatus) {
		ItemStatus = itemStatus;
	}
	


}