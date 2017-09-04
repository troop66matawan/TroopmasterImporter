package com.troop66matawan.tm.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.troop66matawan.tm.exporter.JSON;
import com.troop66matawan.tm.exporter.JSON.JSONArray;

public class Scout {
	private String _lastName;
	private String _firstName;
	private List<MeritBadge> _meritBadges = new ArrayList<MeritBadge>();
	private List<ScoutAwards> _awards = new ArrayList<ScoutAwards>();
	private RankAdvancement _rankAdvancement;
	private Integer _campingNights=0;
	private List<Activity> _activities = new ArrayList<Activity>();
	private String _bsaID;
	private Date _dateOfBirth;
	private List<PositionOfResponsibility> _leadership = new ArrayList<PositionOfResponsibility>();
	private Date _reportDate = new Date();

	public Date get_reportDate() {
		return _reportDate;
	}

	public JSON toJSON() {
		final Integer JSONversion = 2;
		JSON json = new JSON();	
		
		json.addKeyValuePair("reportDate", _reportDate);
		json.addKeyValuePair("version",JSONversion.toString());
		json.addKeyValuePair("lastName",_lastName);
		json.addKeyValuePair("firstName",_firstName);
		json.addKeyValuePair("campingnights",_campingNights);
		json.addKeyValuePair("bsaID",_bsaID);
		json.addKeyValuePair("dateOfBirth",_dateOfBirth);
		json.addKeyValuePair("ranks", _rankAdvancement.toJSON());
		JSONArray camping = json.new JSONArray();
		for (Activity act : _activities) {
			if (act instanceof Camping) {
				camping.addJson(act.toJSON());
			}
		}
		json.addKeyValuePair("camping", camping);
		JSONArray meritBadges = json.new JSONArray();
		for (MeritBadge mb : _meritBadges) {
			meritBadges.addJson(mb.toJSON());
		}
		json.addKeyValuePair("meritbadges", meritBadges);
		
		JSONArray leadership = json.new JSONArray();
		for (PositionOfResponsibility pos : _leadership) {
			leadership.addJson(pos.toJSON());
		}
		json.addKeyValuePair("leadership", leadership);
		return json;
	}

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

	public List<PositionOfResponsibility> get_leadership() {
		return _leadership;
	}

	public void add_leadership(PositionOfResponsibility pos) {
		this._leadership.add(pos);
	}
	public void set_leadership(List<PositionOfResponsibility> _leadership) {
		this._leadership = _leadership;
	}

}
