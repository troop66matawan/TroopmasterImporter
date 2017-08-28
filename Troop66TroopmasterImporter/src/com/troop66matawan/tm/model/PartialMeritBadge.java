package com.troop66matawan.tm.model;

import java.util.ArrayList;
import java.util.Date;

public abstract class PartialMeritBadge extends MeritBadge {
	private Date startDate;
	private Date lastProgress;
	private ArrayList<Requirement> _requirements; 
	
	public PartialMeritBadge () {
		_requirements = new ArrayList<Requirement>();
	}
	public PartialMeritBadge(String name) {
		super(name);
		_requirements = new ArrayList<Requirement>();
	}

	public Date get_lastProgress() {
		return lastProgress;
	}
	public void set_lastProgress(Date lastProgress) {
		this.lastProgress = lastProgress;
	}
	public Date get_startDate() {
		return startDate;
	}
	public void set_startDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public ArrayList<Requirement> get_requirements() {
		return _requirements;
	}
	public void set_requirements(ArrayList<Requirement> _requirements) {
		this._requirements = _requirements;
	}
}
