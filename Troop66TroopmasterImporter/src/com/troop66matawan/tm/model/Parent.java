package com.troop66matawan.tm.model;

public class Parent extends ContactInfo {
	 private String _relation;
	 private String _lastName;
	 private String _firstName;
	 private boolean _guardian;
	 private String _gender;
	 
	public Parent(String address1, String city, String state, String zip) {
		super(address1, city, state, zip);
	}

	public String get_relation() {
		return _relation;
	}

	public void set_relation(String _relation) {
		this._relation = _relation;
	}

	public String get_lastName() {
		return _lastName;
	}

	public void set_lastName(String _lastName) {
		this._lastName = _lastName;
	}

	public String get_firstName() {
		return _firstName;
	}

	public void set_firstName(String _firstName) {
		this._firstName = _firstName;
	}

	public boolean is_guardian() {
		return _guardian;
	}

	public void set_guardian(boolean _guardian) {
		this._guardian = _guardian;
	}

	public String get_gender() {
		return _gender;
	}

	public void set_gender(String _gender) {
		this._gender = _gender;
	}

}
