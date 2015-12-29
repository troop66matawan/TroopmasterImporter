package com.troop66matawan.tm.model;

import java.util.Date;

public class ServiceProject extends Activity {
	public ServiceProject(Date date, Integer hours, String location, String remarks ) {
		super.activityDate = date;
		super.amount = hours;
		super.location = location;
		super.remarks = remarks;		
	}

}
