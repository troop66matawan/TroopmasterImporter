package com.troop66matawan.tm.model;

import java.util.Date;

import com.troop66matawan.tm.exporter.JSON;

public class PositionOfResponsibility {

	private String _position;
	private Date _startDate;
	private Date _endDate;
	
	public PositionOfResponsibility(String pos) {
		_position = pos;
	}

	public JSON toJSON() {
		JSON json = new JSON();
		
		json.addKeyValuePair("position", _position);
		json.addKeyValuePair("startDate", _startDate);
		json.addKeyValuePair("endDate", _endDate);
		
		return json;
	}

	public String get_position() {
		return _position;
	}

	public void set_position(String _position) {
		this._position = _position;
	}

	public Date get_startDate() {
		return _startDate;
	}

	public void set_startDate(Date _startDate) {
		this._startDate = _startDate;
	}

	public Date get_endDate() {
		return _endDate;
	}

	public void set_endDate(Date _endDate) {
		this._endDate = _endDate;
	}
	
	

}
