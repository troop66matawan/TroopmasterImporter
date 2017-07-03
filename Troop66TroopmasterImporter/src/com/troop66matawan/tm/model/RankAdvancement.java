package com.troop66matawan.tm.model;

import java.util.Date;

import com.troop66matawan.tm.exporter.JSON;

public class RankAdvancement {
	private boolean _isValid = false;
	private Date _scout = null;
	private Date _tenderfoot = null;
	private Date _2ndClass = null;
	private Date _1stClass = null;
	private Date _star = null;
	private Date _life = null;
	private Date _eagle = null;
	private Integer _neededLeadership = null;
	private Integer _neededServiceHours = null;
	
	public RankAdvancement() {
		
	}
	public JSON toJSON() {
		final Integer JSONversion = 1;
		JSON json = new JSON();	
		
		json.addKeyValuePair("version",JSONversion.toString());
		json.addKeyValuePair("scout",_scout);
		json.addKeyValuePair("tenderfoot",_tenderfoot);
		json.addKeyValuePair("secondClass",_2ndClass);
		json.addKeyValuePair("firstClass",_1stClass);
		json.addKeyValuePair("star",_star);
		json.addKeyValuePair("life",_life);
		json.addKeyValuePair("eagle",_eagle);
		
		
		return json;
	}

	public boolean getIsValid() {
		return _isValid;
	}

	public Date get_scout() {
		return _scout;
	}

	public Date get_tenderfoot() {
		return _tenderfoot;
	}

	public void set_scout(Date _scout) {
		this._scout = _scout;
		_isValid = true;
	}

	public void set_tenderfoot(Date _tenderfoot) {
		this._tenderfoot = _tenderfoot;
		_isValid = true;
	}

	public void set_2ndClass(Date _2ndClass) {
		this._2ndClass = _2ndClass;
		_isValid = true;
	}

	public void set_1stClass(Date _1stClass) {
		this._1stClass = _1stClass;
		_isValid = true;
	}

	public void set_star(Date _star) {
		this._star = _star;
		_isValid = true;
	}

	public void set_life(Date _life) {
		this._life = _life;
		_isValid = true;
	}

	public void set_eagle(Date _eagle) {
		this._eagle = _eagle;
		_isValid = true;
	}

	public Date get_2ndClass() {
		return _2ndClass;
	}

	public Date get_1stClass() {
		return _1stClass;
	}

	public Date get_star() {
		return _star;
	}

	public Date get_life() {
		return _life;
	}

	public Date get_eagle() {
		return _eagle;
	}
	
	public String currentRank() {
		String rank = "none";
		if (_eagle != null)
			rank = "Eagle";
		else if (_life != null)
			rank = "Life";
		else if (_star != null)
			rank = "Star";
		else if (_1stClass != null) 
			rank = "First Class";
		else if (_2ndClass != null)
			rank = "Second Class";
		else if (_tenderfoot != null)
			rank = "Tenderfoot";
		else if (_scout != null) 
			rank = "Scout";
		
		return rank;
	}

	public Integer get_neededLeadership() {
		return _neededLeadership;
	}

	public void set_neededLeadership(Integer _neededLeadership) {
		this._neededLeadership = _neededLeadership;
		_isValid = true;
	}

	public Integer get_neededServiceHours() {
		return _neededServiceHours;
	}

	public void set_neededServiceHours(Integer _neededServiceHours) {
		this._neededServiceHours = _neededServiceHours;
		_isValid = true;
	}
}
