package com.troop66matawan.tm.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ActivityFactory {

	private static ActivityFactory _instance = null;
	protected Map<String, List<Activity>> _activities = new HashMap<String, List<Activity>>();
	
	private ActivityFactory() {
		
	}
	
	public static ActivityFactory getInstance() {
		if (_instance == null)
			_instance = new ActivityFactory();
		return _instance;
	}

	public void addActivity(Activity a) {
		String activityType = a.getClass().getName();
		
		List<Activity> activities = _activities.get(activityType);
		if (activities == null) {
			activities = new ArrayList<Activity>();
		}
		activities.add(a);
		_activities.put(activityType, activities);
	}
	
	public List<Activity> getActivities(String activityType) {
		return _activities.get(activityType);
	}
	
	public Set<String> getActivityTypes() {
		return _activities.keySet();
	}
}
