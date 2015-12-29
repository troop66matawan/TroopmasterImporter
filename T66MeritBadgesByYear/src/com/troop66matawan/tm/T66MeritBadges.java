package com.troop66matawan.tm;

import java.io.IOException;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;





import java.util.TreeMap;

import com.troop66matawan.tm.importer.MeritBadgeImporter;
import com.troop66matawan.tm.model.MeritBadge;
import com.troop66matawan.tm.model.Scout;
import com.troop66matawan.tm.model.ScoutFactory;

public class T66MeritBadges {
	private Map<String, Integer> mbcount;
	private Date startDate;
	private Date endDate;
	
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
		startDate = getDate(1,1,year);
		endDate = getDate(12,31,year);
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
	
	public static void main(String[] args) {
		Integer year;
		if (args.length == 2) {
			//System.out.println("Merit Badge importer");
			try {
				MeritBadgeImporter mbi = new MeritBadgeImporter(args[0]);
				mbi.doImport();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			year = new Integer(args[1]);
		
			//ScoutFactory.getInstance().dump();
			T66MeritBadges t66MB = new T66MeritBadges(year);
			ScoutFactory sf = ScoutFactory.getInstance();
			for (Scout s : sf.getScouts()) {
				t66MB.getMeritBadgesByYear(s.getMeritBadges());
			}
			
			TreeMap<String, Integer> annualMBs = t66MB.sortByValue();
			
			for (Map.Entry<String, Integer> mapEntry : annualMBs.entrySet()){
				System.out.println(mapEntry.getKey() + ", " + mapEntry.getValue());
			}
			
			
		} else {
			System.out.println("Usage: <TM Scout MB data> <year>");
		}
	}
}
