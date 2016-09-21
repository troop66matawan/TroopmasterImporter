package com.troop66matawan.tm.model;

import java.util.Date;

import com.troop66matawan.tm.exporter.JSON;

public class Camping extends Activity {
	protected boolean isCabin = false;

	/**
	 * 
	 */
	public Camping(Date date, Integer nights, String location, String remarks, boolean isCabin ) {
		super.activityDate = date;
		super.amount = nights;
		super.location = location;
		super.remarks = remarks;
		this.isCabin = isCabin;		
		
	}

	public JSON toJSON() {
		JSON json = super.toJSON();
		
		json.addKeyValuePair("isCabin", isCabin);
		return json;		
	}

}
