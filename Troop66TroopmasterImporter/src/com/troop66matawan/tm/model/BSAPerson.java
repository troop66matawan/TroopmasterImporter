package com.troop66matawan.tm.model;

import java.util.Date;

public abstract class BSAPerson {
	protected String _lastName;
	protected String _firstName;
	protected String _bsaID;
	protected Date _dateOfBirth;
	protected Date _reportDate = new Date();
	protected ContactInfo _contactInfo;
	
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
	public String get_bsaID() {
		return _bsaID;
	}
	public void set_bsaID(String _bsaID) {
		this._bsaID = _bsaID;
	}
	public Date get_dateOfBirth() {
		return _dateOfBirth;
	}
	public void set_dateOfBirth(Date _dateOfBirth) {
		this._dateOfBirth = _dateOfBirth;
	}

	public Date get_reportDate() {
		return _reportDate;
	}

	public ContactInfo get_contactInfo() {
		return _contactInfo;
	}

	public void set_contactInfo(ContactInfo _contactInfo) {
		this._contactInfo = _contactInfo;
	}

}
