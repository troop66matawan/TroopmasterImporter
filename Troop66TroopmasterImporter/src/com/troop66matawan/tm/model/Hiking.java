package com.troop66matawan.tm.model;

import java.util.Date;

public class Hiking extends Activity {
	public Hiking(Date date, Integer miles, String location, String remarks ) {
		super.activityDate = date;
		super.amount = miles;
		super.location = location;
		super.remarks = remarks;		
	}

}
