package com.troop66matawan.tm;

import java.util.ArrayList;
import java.util.List;

import com.troop66matawan.tm.model.Activity;
import com.troop66matawan.tm.model.Camping;
import com.troop66matawan.tm.model.Hiking;
import com.troop66matawan.tm.model.Scout;
import com.troop66matawan.tm.model.ServiceProject;

public class ExportScout extends Scout {
	protected List<Camping> _camping = new ArrayList<Camping>();
	public List<Camping> get_camping() {
		return _camping;
	}

	public List<ServiceProject> get_service() {
		return _service;
	}

	public List<Hiking> get_hiking() {
		return _hiking;
	}

	protected List<ServiceProject> _service = new ArrayList<ServiceProject>();
	protected List<Hiking> _hiking = new ArrayList<Hiking>();

	public ExportScout(Scout scout) {
		super(scout.get_lastName(), scout.get_firstName());
		
		super._activities = scout.get_activities();
		super._awards = scout.get_awards();
		super._bsaID = scout.get_bsaID();
		super._campingNights = scout.get_campingNights();
		super._dateOfBirth = scout.get_dateOfBirth();
		super._leadership = scout.get_leadership();
		super._meritBadges = scout.getMeritBadges();
		super._rankAdvancement = scout.get_rankAdvancement();
		super._reportDate = scout.get_reportDate();
	}
	
	public void parseActivities() {
		for (Activity act : super._activities) {
			if (act instanceof Camping) {
				_camping.add((Camping) act);
			} else if (act instanceof Hiking) {
				_hiking.add((Hiking)act);
			} else if (act instanceof ServiceProject) {
				_service.add((ServiceProject) act);
			}
		}
		super._activities = null;
	}
}
