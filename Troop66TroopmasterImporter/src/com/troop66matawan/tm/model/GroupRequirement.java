package com.troop66matawan.tm.model;

import java.util.ArrayList;

public class GroupRequirement extends Requirement {
	private ArrayList<Requirement> _childRequirements;
	private Integer _doN; // how many to do
	public static final Integer ALL = 0;

	public GroupRequirement(String reqNumber, String text) {
		super(reqNumber, text);
		_childRequirements = new ArrayList<Requirement>();
	}

	public GroupRequirement(String reqNumber) {
		super(reqNumber);
		_childRequirements = new ArrayList<Requirement>();
	}

	@Override
	public ArrayList<Requirement> get_childRequirements() {
		return _childRequirements;
	}

	public void set_childRequirements(ArrayList<Requirement> req) {
		this._childRequirements = req;
	}
	
	public void add_childRequirement(Requirement req) {
		_childRequirements.add(req);
	}

	public Integer get_doN() {
		return _doN;
	}

	public void set_doN(Integer _doN) {
		this._doN = _doN;
	}

	@Override
	public boolean isComplete() {
		super._isComplete = false; 
		
		if (_doN == ALL) {
			super._isComplete = true;
			for (Requirement req : _childRequirements) {
				if (!req.isComplete()) {
					super._isComplete = false;
					break;
				}
			}
		} else {
			int n = 0;
			for (Requirement req : _childRequirements) {
				if (req.isComplete()) {
					n++;
				}
			}
			if (n >= _doN) {
				super._isComplete = true;
			}
		}

		return super._isComplete;
	}
	
}
