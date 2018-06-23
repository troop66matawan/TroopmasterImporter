package com.troop66matawan.tm.model;

import java.util.Date;

import com.troop66matawan.tm.exporter.JSON;

public class Meeting extends Activity {
	public Meeting(Date date, Integer nights, String location, String remarks) {
		super.activityDate = date;
		super.amount = nights;
		super.location = location;
		super.remarks = remarks;
	}
	public JSON toJSON() {
		JSON json = super.toJSON();
		return json;		
	}
}
