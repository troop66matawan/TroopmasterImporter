package com.troop66matawan.tm.model;

public class ContactInfo {
	private String _address1;
	private String _address2;
	private String _city;
	private String _state;
	private String _zip;
	private String _country;
	private String _homephone;
	private String _workphone;
	private String _faxphone;
	private String _cellphone;
	private String _pagerphone;
	private String _vmphonephdr;
	private String _otherphone;
	private String _email1;
	private String _email2;

	public ContactInfo(String address1, String city, String state, String zip) {
		this._address1 = address1;
		this._city = city;
		this._state = state;
		this._zip = zip;
	}

	public String get_address1() {
		return _address1;
	}

	public void set_address1(String _address1) {
		this._address1 = _address1;
	}

	public String get_address2() {
		return _address2;
	}

	public void set_address2(String _address2) {
		this._address2 = _address2;
	}

	public String get_city() {
		return _city;
	}

	public void set_city(String _city) {
		this._city = _city;
	}

	public String get_state() {
		return _state;
	}

	public void set_state(String _state) {
		this._state = _state;
	}

	public String get_zip() {
		return _zip;
	}

	public void set_zip(String _zip) {
		this._zip = _zip;
	}

	public String get_country() {
		return _country;
	}

	public void set_country(String _country) {
		this._country = _country;
	}

	public String get_homephone() {
		return _homephone;
	}

	public void set_homephone(String _homephone) {
		this._homephone = _homephone;
	}

	public String get_workphone() {
		return _workphone;
	}

	public void set_workphone(String _workphone) {
		this._workphone = _workphone;
	}

	public String get_faxphone() {
		return _faxphone;
	}

	public void set_faxphone(String _faxphone) {
		this._faxphone = _faxphone;
	}

	public String get_cellphone() {
		return _cellphone;
	}

	public void set_cellphone(String _cellphone) {
		this._cellphone = _cellphone;
	}

	public String get_pagerphone() {
		return _pagerphone;
	}

	public void set_pagerphone(String _pagerphone) {
		this._pagerphone = _pagerphone;
	}

	public String get_vmphonephdr() {
		return _vmphonephdr;
	}

	public void set_vmphonephdr(String _vmphonephdr) {
		this._vmphonephdr = _vmphonephdr;
	}

	public String get_otherphone() {
		return _otherphone;
	}

	public void set_otherphone(String _otherphone) {
		this._otherphone = _otherphone;
	}

	public String get_email1() {
		return _email1;
	}

	public void set_email1(String _email1) {
		this._email1 = _email1;
	}

	public String get_email2() {
		return _email2;
	}

	public void set_email2(String _email2) {
		this._email2 = _email2;
	}
}
