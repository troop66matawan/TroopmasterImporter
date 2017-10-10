package com.troop66matawan.tm;

import java.util.ArrayList;
import java.util.List;

public class ExportUsers {
	private String FirstName;
	private String LastName;
	private String Position;
	
	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public List<String> getAccess() {
		return access;
	}

	public void setAccess(List<String> access) {
		this.access = access;
	}

	public void addAccess(String name) {
		access.add(name);
	}
	private List<String> access = new ArrayList<String>();
	
	public ExportUsers() {
		Position = "none";
	}

	public String getPosition() {
		return Position;
	}

	public void setPosition(String position) {
		Position = position;
	}

}
