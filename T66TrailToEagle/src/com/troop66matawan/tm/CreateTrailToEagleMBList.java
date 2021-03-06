package com.troop66matawan.tm;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;

import com.troop66matawan.tm.importer.HeaderFormatException;
import com.troop66matawan.tm.importer.LeadershipPositionDaysNeededImporter;
import com.troop66matawan.tm.importer.MeritBadgesEarnedImporter;
import com.troop66matawan.tm.importer.RankBadgeMatrixImporter;
import com.troop66matawan.tm.importer.ScoutDataImporter;
import com.troop66matawan.tm.model.EagleRequiredMeritBadges;
import com.troop66matawan.tm.model.RankAdvancement;
import com.troop66matawan.tm.model.Scout;
import com.troop66matawan.tm.model.ScoutFactory;

public class CreateTrailToEagleMBList {
	private ArrayList<Scout> trailToEagleScouts = new ArrayList<Scout>();
	private int scoutIndex=0;
	private int minAge;

	public CreateTrailToEagleMBList()
	{
		minAge = 16;
	}
	
	public CreateTrailToEagleMBList(int age)
	{
		minAge = age;
	}
	public static void usage() {
		System.err.println("Usage: <path to Merit Badge export> <path to Rank Advancement> <path to Scout Data> [<path to leadership remaining>]");
	}
	
	public int findTrailToEagleScouts(Collection<Scout> scouts)
	{
		int numberOfTrailToEagleScouts = 0;
		for (Scout scout : scouts)
		{
			RankAdvancement rank = scout.get_rankAdvancement();
			Date dob = scout.get_dateOfBirth();
			if ( (rank.get_eagle() == null) && (rank.get_life() != null || (rank.get_life() == null && (olderThanMinAge(dob)))))
			{
				trailToEagleScouts.add(scout);
				++numberOfTrailToEagleScouts;
			}
		}
		// Sort trailToEagleScouts by DateOfBirth
		Collections.sort(trailToEagleScouts, new Comparator<Scout>() {
	        @Override
	        public int compare(Scout  s1, Scout  s2)
	        {
	        	Date d1 = s1.get_dateOfBirth();
	        	Date d2 = s2.get_dateOfBirth();
	            return d1.compareTo(d2);  
	        }
	    });

		return numberOfTrailToEagleScouts;
	}
	
	public Scout firstScout()
	{
		scoutIndex = 0;
		return getScoutIndex(scoutIndex);
	}
	private Scout getScoutIndex(int index)
	{
		Scout scout = null;
		if (trailToEagleScouts.size() > index)
			scout = trailToEagleScouts.get(index);
		return scout;
	}
	public Scout nextScout()
	{		
		return getScoutIndex(++scoutIndex);
	}
		
	
	private boolean olderThanMinAge(Date dob)
	{
		boolean rv = false;
		Calendar birthDay = new GregorianCalendar();
		birthDay.setTime(dob);
		Calendar minAgeDate = new GregorianCalendar();
		minAgeDate.setTime(dob);
		minAgeDate.set(Calendar.YEAR, birthDay.get(Calendar.YEAR)+minAge);
		
		Calendar now = Calendar.getInstance();
		if (minAgeDate.compareTo(now) < 0)
			rv = true;
		
		return rv;
	}
	
	public String createCSVHeader()
	{
		return "Last Name, First Name, Rank, Birthday,Months to 18,Leadership Remaining,Total Merit Badges,Count of remaining Eagle Badges, Remaining Eagle Required Badges";
	}
	
	public String createCSVRecord(Scout scout)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		RankAdvancement rank = scout.get_rankAdvancement();
		Integer leadershipDays = rank.get_neededLeadership();
		Date dob = scout.get_dateOfBirth();
		EagleRequiredMeritBadges eagleMBs = new EagleRequiredMeritBadges(scout.getMeritBadges());
		String csvRecord=scout.get_lastName();
		csvRecord +=", ";
		csvRecord +=scout.get_firstName();
		csvRecord +=", ";
		csvRecord +=rank.currentRank();
		csvRecord +=", ";
		csvRecord += sdf.format(dob);
		csvRecord +=",";
		csvRecord += scout.monthsUntil18(dob);
		csvRecord +=", ";
		if (leadershipDays != null)
			csvRecord += leadershipDays;
		csvRecord += ", ";
		csvRecord +=scout.getMeritBadges().size();
		csvRecord +=", ";
		csvRecord += eagleMBs.countRemaining();
		csvRecord +=", ";
		csvRecord += eagleMBs.listRemaining();
		
		return csvRecord;
		
	}
	

	public void printCSV()
	{
		System.out.println(createCSVHeader());
		for (Scout s = firstScout(); s != null; s=nextScout())
		{
			System.out.println(createCSVRecord(s));
		}
		
	}
	
	public static void main(String[] args) {
	if (args.length >= 3 && args.length <= 4) {
		try {
			//MeritBadgeImporter mbi = new MeritBadgeImporter(args[0]);
			MeritBadgesEarnedImporter mbi = new MeritBadgesEarnedImporter(args[0]);
			mbi.doImport();
		} catch (IOException e) {
			usage();
			e.printStackTrace();
			System.exit(1);
		}
		try{
			//RankAdvancementImporter rai = new RankAdvancementImporter(args[1]);
			RankBadgeMatrixImporter rai = new RankBadgeMatrixImporter(args[1]);
			rai.doImport();
		} catch (IOException e) {
			usage();
			e.printStackTrace();
			System.exit(1);
/*		} catch (HeaderFormatException e) {
			System.err.println("Unable to parse header for Rank Advancement data");
			usage();
			System.exit(1);
*/		}
		try {
			ScoutDataImporter sdi = new ScoutDataImporter(args[2], true);
			sdi.doImport();
		}catch (IOException e) {
			usage();
			e.printStackTrace();
			System.exit(1);
		} catch (HeaderFormatException e) {
			System.err.println("Unable to parse header for Scout data");
			usage();
			System.exit(1);
		}
		if (args.length == 4) {
			try {
				LeadershipPositionDaysNeededImporter lpi = new LeadershipPositionDaysNeededImporter(args[3]);
				lpi.doImport();
			} catch (IOException e) {
				usage();
				e.printStackTrace();
				System.exit(1);
			}
		}
		
	
		ScoutFactory sf = ScoutFactory.getInstance();
		CreateTrailToEagleMBList report = new CreateTrailToEagleMBList(16);
		int numScouts = report.findTrailToEagleScouts(sf.getScouts());
		if (numScouts > 0)
			report.printCSV();
		
	}
	else {
		usage();
	}
}

}
