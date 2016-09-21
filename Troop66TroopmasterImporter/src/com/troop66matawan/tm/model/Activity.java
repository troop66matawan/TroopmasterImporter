package com.troop66matawan.tm.model;

import java.util.Date;

import com.troop66matawan.tm.exporter.JSON;

public abstract class Activity {
	protected Date activityDate;
	protected Integer amount;
	protected String location;
	protected String remarks;
	
	public JSON toJSON() {
		JSON json = new JSON();
		
		json.addKeyValuePair("date", activityDate);
		json.addKeyValuePair("amount", amount);
		json.addKeyValuePair("location", location.trim());
		json.addKeyValuePair("remarks", remarks.trim());
		
		return json;
	}
	public Date getActivityDate() {
		return activityDate;
	}
	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
