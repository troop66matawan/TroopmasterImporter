package com.troop66matawan.tm.model;

import java.util.Date;

import com.troop66matawan.tm.exporter.JSON;

public class Camping extends Activity {
	protected boolean isCabin = false;
	protected boolean isSummer = false;

	public boolean isCabin() {
		return isCabin;
	}

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
	public Camping(Date date, Integer nights, String location, String remarks, boolean isCabin, boolean isSummer ) {
		this(date,nights,location,remarks,isCabin);
		this.isSummer = isSummer;
	}
	public JSON toJSON() {
		JSON json = super.toJSON();
		
		json.addKeyValuePair("isCabin", isCabin);
		json.addKeyValuePair("isSummer", isSummer);
		return json;		
	}

}
