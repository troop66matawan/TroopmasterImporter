package com.troop66matawan.tm.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdultFactory {

	private static AdultFactory _instance = null;
	private Map<String, Adult> _adults = new HashMap<String, Adult>();
	
	private AdultFactory() {
		
	}
	
	public static AdultFactory getInstance() {
		if (_instance == null)
			_instance = new AdultFactory();
		return _instance;
	}
	
	public boolean hasScout(String lastName, String firstName) {
		return _adults.containsKey(lastName+firstName);
	}
		
	public void addAdult(String lastName, String firstName) {
		_adults.put(lastName+firstName, new Adult(lastName,firstName));
	}
	
	public void updateAdult(Adult s) {
		_adults.put(s.get_lastName()+s.get_firstName(), s);
	}
	
	public Adult getAdult(String lastName, String firstName) {
		return _adults.get(lastName+firstName);
	}
	
	public Collection<Adult> getAdults() {
		return _adults.values();
	}
	
	public List<Adult> getScouts_alphaSort() {
		List<Adult> scouts = new ArrayList<Adult>(_adults.values());
		Collections.sort(scouts, new Comparator<Adult>() {
	        @Override
	        public int compare(Adult  s1, Adult  s2)
	        {
	        	int rv = s1.get_lastName().compareTo(s2.get_lastName());
	        	if (rv == 0)
	        		rv = s1.get_firstName().compareTo(s2.get_firstName());
	            return rv;
	        }
	    });
		return scouts;
	}
	public List<Adult> getAdults_dobSort() {
		List<Adult> scouts = new ArrayList<Adult>(_adults.values());
		Collections.sort(scouts, new Comparator<Adult>() {
	        @Override
	        public int compare(Adult  s1, Adult  s2)
	        {
	        	Date d1 = s1.get_dateOfBirth();
	        	Date d2 = s2.get_dateOfBirth();
	            return d1.compareTo(d2);  
	        }
	    });
		return scouts;
	}

	public void dump() {
		for (Adult s : _adults.values()) {
			System.out.print(s.get_lastName());
			System.out.print(",");
			System.out.print(s.get_firstName());
			System.out.print(",");
			System.out.println("");			
		}
	}
}
