package com.troop66matawan.tm.model;

import java.util.Date;

public class ScoutAwards {
	private String _name;
	private Date _earned;
	
	public ScoutAwards() {
	}
	public ScoutAwards(String name) {
		_name = name;
	}
	public String get_name() {
		return _name;
	}
	public void set_name(String _name) {
		this._name = _name;
	}
	public Date get_earned() {
		return _earned;
	}

	@Override
	public boolean equals(Object obj) {
		boolean rv = false;
		try {
			ScoutAwards a = (ScoutAwards)obj;
			if (a._name.equals(_name))
				rv = true;
		} catch (ClassCastException e) {
		}
		return rv;

	}
	public void set_earned(Date earned) {
		_earned = earned;
	}

}
