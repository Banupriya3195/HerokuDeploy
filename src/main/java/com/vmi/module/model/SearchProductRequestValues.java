package com.vmi.module.model;

import java.io.Serializable;

public class SearchProductRequestValues implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 98964279467537028L;
	private int count;
	private int start=0;
	private String query;
	private boolean disabled=false;
	private String strQuery;
	
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}

}
