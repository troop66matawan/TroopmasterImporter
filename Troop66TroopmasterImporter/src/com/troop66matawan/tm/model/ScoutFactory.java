package com.troop66matawan.tm.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoutFactory {

	private static ScoutFactory _instance = null;
	private Map<String, Scout> _scouts = new HashMap<String, Scout>();
	
	private ScoutFactory() {
		
	}
	
	public static ScoutFactory getInstance() {
		if (_instance == null)
			_instance = new ScoutFactory();
		return _instance;
	}
	
	public boolean hasScout(String lastName, String firstName) {
		return _scouts.containsKey(lastName+firstName);
	}
		
	public void addScout(String lastName, String firstName) {
		_scouts.put(lastName+firstName, new Scout(lastName,firstName));
	}
	
	public void updateScout(Scout s) {
		_scouts.put(s.get_lastName()+s.get_firstName(), s);
	}
	
	public Scout getScout(String lastName, String firstName) {
		return _scouts.get(lastName+firstName);
	}
	
	public Collection<Scout> getScouts() {
		return _scouts.values();
	}
	
	public List<Scout> getScouts_alphaSort() {
		List<Scout> scouts = new ArrayList<Scout>(_scouts.values());
		Collections.sort(scouts, new Comparator<Scout>() {
	        @Override
	        public int compare(Scout  s1, Scout  s2)
	        {
	        	int rv = s1.get_lastName().compareTo(s2.get_lastName());
	        	if (rv == 0)
	        		rv = s1.get_firstName().compareTo(s2.get_firstName());
	            return rv;
	        }
	    });
		return scouts;
	}
	public List<Scout> getScouts_dobSort() {
		List<Scout> scouts = new ArrayList<Scout>(_scouts.values());
		Collections.sort(scouts, new Comparator<Scout>() {
	        @Override
	        public int compare(Scout  s1, Scout  s2)
	        {
	        	Date d1 = s1.get_dateOfBirth();
	        	Date d2 = s2.get_dateOfBirth();
	            return d1.compareTo(d2);  
	        }
	    });
		return scouts;
	}

	public void dump() {
		for (Scout s : _scouts.values()) {
			System.out.print(s.get_lastName());
			System.out.print(",");
			System.out.print(s.get_firstName());
			System.out.print(",");
			for (MeritBadge m : s.getMeritBadges()) {
				System.out.print(m.get_name());
				System.out.print(",");
			}
			System.out.println("");			
		}
	}
}
