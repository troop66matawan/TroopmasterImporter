package com.troop66matawan.tm.model;

import java.util.Date;

public class RankAdvancement {
	private Date _scout = null;
	private Date _tenderfoot = null;
	private Date _2ndClass = null;
	private Date _1stClass = null;
	private Date _star = null;
	private Date _life = null;
	private Date _eagle = null;
	
	public RankAdvancement() {
		
	}

	public Date get_scout() {
		return _scout;
	}

	public Date get_tenderfoot() {
		return _tenderfoot;
	}

	public void set_scout(Date _scout) {
		this._scout = _scout;
	}

	public void set_tenderfoot(Date _tenderfoot) {
		this._tenderfoot = _tenderfoot;
	}

	public void set_2ndClass(Date _2ndClass) {
		this._2ndClass = _2ndClass;
	}

	public void set_1stClass(Date _1stClass) {
		this._1stClass = _1stClass;
	}

	public void set_star(Date _star) {
		this._star = _star;
	}

	public void set_life(Date _life) {
		this._life = _life;
	}

	public void set_eagle(Date _eagle) {
		this._eagle = _eagle;
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
}
