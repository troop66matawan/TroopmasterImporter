package com.troop66matawan.tm.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Scout {
	private String _lastName;
	private String _firstName;
	private List<MeritBadge> _meritBadges = new ArrayList<MeritBadge>();
	private List<ScoutAwards> _awards = new ArrayList<ScoutAwards>();
	private RankAdvancement _rankAdvancement;
	private Integer _campingNights;
	private List<Activity> _activities = new ArrayList<Activity>();
	private String _bsaID;
	private Date _dateOfBirth;

	public Integer get_campingNights() {
		return _campingNights;
	}
	public void set_campingNights(Integer _campingNights) {
		this._campingNights = _campingNights;
	}
	public RankAdvancement get_rankAdvancement() {
		return _rankAdvancement;
	}
	public void set_rankAdvancement(RankAdvancement _rankAdvancement) {
		this._rankAdvancement = _rankAdvancement;
	}
	Scout(String lastname, String firstname) {
		_lastName = lastname;
		_firstName = firstname;
	}
	public String get_lastName() {
		return _lastName;
	}
	public void set_lastName(String _lastName) {
		this._lastName = _lastName;
	}
	public String get_firstName() {
		return _firstName;
	}
	public void set_firstName(String _firstName) {
		this._firstName = _firstName;
	}
	@Override
	public boolean equals(Object obj) {
		boolean rv = false;
		try {
			Scout s = (Scout)obj;
			if (s._lastName.equals(_lastName) && s._firstName.equals(_firstName))
				rv = true;
		} catch (ClassCastException e) {
		}
		return rv;
	}
	
	public void addMeritBadge(MeritBadge m) {
		_meritBadges.add(m);
	}
	
	public List<MeritBadge> getMeritBadges() {
		return _meritBadges;
	}
	public List<ScoutAwards> get_awards() {
		return _awards;
	}
	public void addScoutAward(ScoutAwards a) {
		_awards.add(a);
	}
	public List<Activity> get_activities() {
		return _activities;
	}
	public void addActivity(Activity a) {
		_activities.add(a);
	}
	public String get_bsaID() {
		return _bsaID;
	}
	public void set_bsaID(String _bsaID) {
		this._bsaID = _bsaID;
	}
	public Date get_dateOfBirth() {
		return _dateOfBirth;
	}
	public void set_dateOfBirth(Date _dateOfBirth) {
		this._dateOfBirth = _dateOfBirth;
	}
	public int monthsUntil18(Date dob)
	{
		Calendar birthDay = new GregorianCalendar();
		birthDay.setTime(dob);
		Calendar eighteen = new GregorianCalendar();
		eighteen.setTime(dob);
		eighteen.set(Calendar.YEAR, birthDay.get(Calendar.YEAR)+18);
		
		Calendar now = Calendar.getInstance();
		int months = (eighteen.get(Calendar.YEAR)* 12 + eighteen.get(Calendar.MONTH)) - (now.get(Calendar.YEAR)* 12 + now.get(Calendar.MONTH));
		
		return months;		
	}

}
