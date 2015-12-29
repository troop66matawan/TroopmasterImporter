package com.troop66matawan.tm;

import java.io.IOException;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.troop66matawan.tm.importer.HeaderFormatException;
import com.troop66matawan.tm.importer.RankAdvancementImporter;
import com.troop66matawan.tm.model.RankAdvancement;
import com.troop66matawan.tm.model.Scout;
import com.troop66matawan.tm.model.ScoutFactory;

public class T66RankAdvByYear {
	private Map<String, Integer> rankCount;
	private Date startDate;
	private Date endDate;

	T66RankAdvByYear(Integer year)
	{
		startDate = getDate(1,1,year);
		endDate = getDate(12,31,year);
		rankCount = new HashMap<String,Integer>();
	}
	
	private Date getDate(Integer month, Integer day, Integer year)
	{
		Calendar c = Calendar.getInstance();
		c.set(year, month-1, day);
		return c.getTime();		
	}
	
	void parseRankAdvancment(RankAdvancement rankAdv)
	{
		isRankInYear(rankAdv.get_scout(), RankAdvancementImporter.SCOUT_RANK);
		isRankInYear(rankAdv.get_tenderfoot(), RankAdvancementImporter.TENDERFOOT_RANK);
		isRankInYear(rankAdv.get_2ndClass(), RankAdvancementImporter.SECONDCLASS_RANK);
		isRankInYear(rankAdv.get_1stClass(), RankAdvancementImporter.FIRSTCLASS_RANK);
		isRankInYear(rankAdv.get_star(), RankAdvancementImporter.STAR_RANK);
		isRankInYear(rankAdv.get_life(), RankAdvancementImporter.LIFE_RANK);
		isRankInYear(rankAdv.get_eagle(), RankAdvancementImporter.EAGLE_RANK);
	}
	
	private void isRankInYear(Date rankDate, String rankString)
	{
		if ((rankDate != null) && (rankDate.equals(startDate) || rankDate.after(startDate) ) && (rankDate.before(endDate) || rankDate.equals(endDate)))
		{
			if (rankCount.containsKey(rankString))
			{
				rankCount.put(rankString, rankCount.get(rankString)+1);
			} else {
				rankCount.put(rankString, 1);
			}				
		}								
		
	}
	
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

	public  TreeMap<String, Integer> sortByValue() {
		ValueComparator vc =  new ValueComparator(rankCount);
		TreeMap<String,Integer> sortedMap = new TreeMap<String,Integer>(vc);
		sortedMap.putAll(rankCount);
		return sortedMap;
	}

	public static void main(String[] args) {
		Integer year;
		if (args.length == 2) {
			//System.out.println("Merit Badge importer");
			try {
				RankAdvancementImporter rai = new RankAdvancementImporter(args[0]);
				rai.doImport();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (HeaderFormatException e) {
				System.err.println("Error parsing rank advancement data");
				System.exit(1);
			}
			year = new Integer(args[1]);
		
			//ScoutFactory.getInstance().dump();
			T66RankAdvByYear t66Ra = new T66RankAdvByYear(year);
			ScoutFactory sf = ScoutFactory.getInstance();
			for (Scout s : sf.getScouts()) {
				t66Ra.parseRankAdvancment(s.get_rankAdvancement());
			}
			
			TreeMap<String, Integer> annualRanks = t66Ra.sortByValue();
			
			System.out.println("Rank,# of Scouts Achievied in " + year);
			for (Map.Entry<String, Integer> mapEntry : annualRanks.entrySet()){
				System.out.println(mapEntry.getKey() + ", " + mapEntry.getValue());
			}
			
			
		} else {
			System.out.println("Usage: <TM Scout Rank Advancement data> <year>");
		}
	}
}

