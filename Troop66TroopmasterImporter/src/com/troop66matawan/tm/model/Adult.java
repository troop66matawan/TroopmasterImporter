package com.troop66matawan.tm.model;

import java.util.ArrayList;
import java.util.List;

public class Adult extends BSAPerson {
	protected List<PositionOfResponsibility> _leadership = new ArrayList<PositionOfResponsibility>();

	public Adult(String lastname, String firstname) {
		_lastName = lastname;
		_firstName = firstname;
	}
	
	public List<PositionOfResponsibility> get_leadership() {
		return _leadership;
	}

	public void add_leadership(PositionOfResponsibility pos) {
		this._leadership.add(pos);
	}
	public void set_leadership(List<PositionOfResponsibility> _leadership) {
		this._leadership = _leadership;
	}

	public boolean isScoutmaster() {
		boolean rv = false;
		
		for (PositionOfResponsibility p : _leadership) {
			String pos = p.get_position();
			if (pos.contains("Scoutmaster")) {
				rv = true;
				break;
			}
		}
		return rv;
	}

	public boolean isEagleCommittee() {
		boolean rv = false;
		
		for (PositionOfResponsibility p : _leadership) {
			String pos = p.get_position();
			if (pos.contains("Eagle")) {
				rv = true;
				break;
			}
		}
		return rv;	
	}
}
