package com.troop66matawan.tm;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.troop66matawan.tm.importer.MeritBadgesEarnedImporter;
import com.troop66matawan.tm.model.MeritBadge;
import com.troop66matawan.tm.model.Scout;
import com.troop66matawan.tm.model.ScoutFactory;

public class T66MeritBadges {
	private Map<String, Integer> mbcount;
	private Date startDate;
	private Date endDate;
	private String printStart;
	static DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
	
	class ValueComparator implements Comparator<String> {
		 
	    Map<String, Integer> map;
	 
	    public ValueComparator(Map<String, Integer> base) {
	        this.map = base;
	    }
	 
	    public int compare(String a, String b) {
	        if (map.get(a) >= map.get(b)) {
	            return -1;
	        } else {
	            return 1;
	        } // returning 0 would merge keys 
	    }
	}	
	
	T66MeritBadges(Integer year)
	{
		printStart = "in " + year.toString();
		startDate = getDate(1,1,year);
		endDate = getDate(12,31,year);
		mbcount = new HashMap<String,Integer>();
	}
	T66MeritBadges(Date _startDate)
	{	
		printStart = "since " + df.format(_startDate);
		startDate = _startDate;
		endDate = Calendar.getInstance().getTime();
		mbcount = new HashMap<String,Integer>();
	}

	private Date getDate(Integer month, Integer day, Integer year)
	{
		Calendar c = Calendar.getInstance();
		c.set(year, month-1, day);
		return c.getTime();		
	}
	void getMeritBadgesByYear(List<MeritBadge> mbs)
	{
		for (MeritBadge mb : mbs)
		{
			String meritBadgeName = mb.get_name();
			Date mbDate = mb.get_earned();
			if ((mbDate.equals(startDate) || mbDate.after(startDate) ) && (mbDate.before(endDate) || mbDate.equals(endDate)))
			{
				if (mbcount.containsKey(meritBadgeName))
				{
					mbcount.put(meritBadgeName, mbcount.get(meritBadgeName)+1);
				} else {
					mbcount.put(meritBadgeName, 1);
				}				
			}								
		}
	}
	
	public  TreeMap<String, Integer> sortByValue() {
		ValueComparator vc =  new ValueComparator(mbcount);
		TreeMap<String,Integer> sortedMap = new TreeMap<String,Integer>(vc);
		sortedMap.putAll(mbcount);
		return sortedMap;
	}
	private String printStart() {
		return printStart;
	}
	public static void main(String[] args) {
		Integer year;
		Date startDate;
		T66MeritBadges t66MB = null;
		if (args.length == 2) {
			//System.out.println("Merit Badge importer");
			try {
				MeritBadgesEarnedImporter mbi = new MeritBadgesEarnedImporter(args[0]);
				mbi.doImport();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				year = new Integer(args[1]);
				t66MB = new T66MeritBadges(year);
			} catch (NumberFormatException e) {
				try {
					startDate = df.parse(args[1]);
					t66MB = new T66MeritBadges(startDate);
				} catch (ParseException ex) {
					System.err.println("Usage: <TM Scout MB data> <year | startDate(mm/dd/yyyy)>");
					System.exit(1);
				}
			}
			ScoutFactory sf = ScoutFactory.getInstance();
			for (Scout s : sf.getScouts()) {
				t66MB.getMeritBadgesByYear(s.getMeritBadges());
			}
			
			TreeMap<String, Integer> annualMBs = t66MB.sortByValue();
			
			System.out.println("MB Name,# of Scouts Achievied " + t66MB.printStart());		
			for (Map.Entry<String, Integer> mapEntry : annualMBs.entrySet()){
				System.out.println(mapEntry.getKey() + ", " + mapEntry.getValue());
			}
			
			
		} else {
			System.out.println("Usage: <TM Scout MB data> <year| startDate(mm/dd/yyyy)>");
		}
	}
}
