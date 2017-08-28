package com.troop66matawan.tm.model;

import java.util.ArrayList;

public abstract class Requirement {
	protected boolean _isComplete;
	private String _text;
	private String _reqNumber;
	

	public Requirement(String reqNumber) {
		_reqNumber = reqNumber;
	}
	public Requirement(String reqNumber, String text) {
		_reqNumber = reqNumber;
		_text = text;
	}
	public boolean isComplete() {
		return _isComplete;
	}

	public void set_isComplete(boolean isComplete) {
		this._isComplete = isComplete;
	}
	public String get_requirementText() {
		return _text;
	}

	public void set_requirementText(String text) {
		_text = text;
	}

	public abstract ArrayList<Requirement> get_childRequirements();
	
	public String get_requirementNumber() {
		return _reqNumber;
	}
}
