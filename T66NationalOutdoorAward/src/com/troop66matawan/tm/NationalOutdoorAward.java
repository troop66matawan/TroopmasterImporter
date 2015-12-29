package com.troop66matawan.tm;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.troop66matawan.tm.importer.HeaderFormatException;
import com.troop66matawan.tm.importer.MeritBadgeImporter;
import com.troop66matawan.tm.importer.RankAdvancementImporter;
import com.troop66matawan.tm.importer.ScoutAwardsImporter;
import com.troop66matawan.tm.importer.ScoutParticipationSummary;
import com.troop66matawan.tm.model.MeritBadge;
import com.troop66matawan.tm.model.RankAdvancement;
import com.troop66matawan.tm.model.Scout;
import com.troop66matawan.tm.model.ScoutAwards;
import com.troop66matawan.tm.model.ScoutFactory;

public class NationalOutdoorAward {
	private static final MeritBadge CAMPING_MB = new MeritBadge("Camping");
	private static final MeritBadge COOKING_MB = new MeritBadge("Cooking"); 
	private static final MeritBadge FIRSTAID_MB = new MeritBadge("First Aid");
	private static final MeritBadge PIONEERING_MB = new MeritBadge("Pioneering");
	private static final MeritBadge HIKING_MB = new MeritBadge("Hiking");
	private static final MeritBadge BACKPACKING_MB = new MeritBadge("Backpacking");
	private static final MeritBadge ORIENTEERING_MB = new MeritBadge("Orienteering");
	private static final MeritBadge GEOCACHING_MB = new MeritBadge("Geocaching");
	private static final ScoutAwards NOA_CAMPING = new ScoutAwards("NOA - Camping Award");
	private SimpleDateFormat sdf;

	NationalOutdoorAward() {
		 sdf = new SimpleDateFormat("MM/dd/yyyy");
	}
	 
	public boolean checkHikingAward(Scout s, boolean displayToSystemOut) {
		boolean rv = false;
		List<MeritBadge> mb = s.getMeritBadges();
		RankAdvancement rank = s.get_rankAdvancement();
		
		if ( rank.get_1stClass() != null) {
			boolean hasHiking = mb.contains(HIKING_MB);
			boolean hasBackpacking =  mb.contains(BACKPACKING_MB);
			boolean hasOrienteering = mb.contains(ORIENTEERING_MB);
			boolean hasGeocaching = mb.contains(GEOCACHING_MB);

			if ((hasHiking || hasBackpacking) && (hasOrienteering || hasGeocaching)){
				rv = true;
				if (displayToSystemOut)
				{
					System.out.print("+ ");
					System.out.print(s.get_lastName());
					System.out.print(",");
					System.out.print(s.get_firstName());
					System.out.print(", ");
					System.out.print("1st Class: ");
					System.out.print(sdf.format(rank.get_1stClass()));
					if (hasHiking) {
						System.out.print(", Hiking MB: ");
						MeritBadge hasmb = mb.get(mb.indexOf(HIKING_MB));
						System.out.print(sdf.format(hasmb.get_earned()));
					}
					if (hasBackpacking) {
						System.out.print(", Backpacking MB: ");
						MeritBadge hasmb = mb.get(mb.indexOf(BACKPACKING_MB));
						System.out.print(sdf.format(hasmb.get_earned()));
					}
					if (hasOrienteering) {
						System.out.print(", Orienteering MB: ");
						MeritBadge hasmb = mb.get(mb.indexOf(ORIENTEERING_MB));
						System.out.print(sdf.format(hasmb.get_earned()));
					}
					if (hasGeocaching) {
						System.out.print(", Geocaching MB: ");
						MeritBadge hasmb = mb.get(mb.indexOf(GEOCACHING_MB));
						System.out.print(sdf.format(hasmb.get_earned()));
					}
				}
			}
		}
	
		return rv;
	}
	public boolean  checkCampingAward(Scout s, boolean displayToSystemOut) {
		boolean rv = false;
		// REQUIREMENTS: 1st class, Camping MB, 2 of [Cooking, First Aid, Pioneering], 25 Days/Nights Camping
		List<ScoutAwards> awards = s.get_awards();
		boolean hasNOA_Camping = awards.contains(NOA_CAMPING);
		List<MeritBadge> mb = s.getMeritBadges();
		boolean hasCamping = mb.contains(CAMPING_MB);
		boolean hasCooking = mb.contains(COOKING_MB);
		boolean hasFirstAid = mb.contains(FIRSTAID_MB);
		boolean hasPioneering = mb.contains(PIONEERING_MB);
		RankAdvancement rank = s.get_rankAdvancement();
		Integer campingNights = s.get_campingNights();
		
		if (campingNights != null && campingNights > 25 && hasCamping && rank.get_1stClass() != null) {
			int cooking = hasCooking ? 1 : 0;
			int firstAid = hasFirstAid ? 1 : 0;
			int pioneering = hasPioneering ? 1 : 0;
			if ((cooking + firstAid + pioneering) >= 2) {
				rv = true;
				if (displayToSystemOut)
				{
					if (hasNOA_Camping)
					{
						System.out.print("* ");
					}
					else {
						System.out.print("  ");
					}
					System.out.print(s.get_lastName());
					System.out.print(",");
					System.out.print(s.get_firstName());
					System.out.print(",");
					System.out.print("Camping Nights: ");
					System.out.print(s.get_campingNights().toString());
					System.out.print(", ");
					System.out.print("1st Class: ");
					System.out.print(sdf.format(rank.get_1stClass()));
					System.out.print(", ");
					System.out.print("Camping MB:");
					MeritBadge camping = mb.get(mb.indexOf(CAMPING_MB));
					System.out.print(sdf.format(camping.get_earned()));
					if (hasCooking) {
						System.out.print(", Cooking MB: ");
						MeritBadge cookingmb = mb.get(mb.indexOf(COOKING_MB));
						System.out.print(sdf.format(cookingmb.get_earned()));
					}
					if (hasFirstAid) {
						System.out.print(", First Aid MB: ");
						MeritBadge firstaidmb = mb.get(mb.indexOf(FIRSTAID_MB));
						System.out.print(sdf.format(firstaidmb.get_earned()));
					}
					if (hasPioneering) {
						System.out.print(", Pioneering MB: ");
						MeritBadge pioneeringmb = mb.get(mb.indexOf(PIONEERING_MB));
						System.out.print(sdf.format(pioneeringmb.get_earned()));
					}
					if (hasNOA_Camping) {
						System.out.print(", NOA-Camping: ");
						ScoutAwards noaCamping = awards.get(awards.indexOf(NOA_CAMPING));
						System.out.print(sdf.format(noaCamping.get_earned()));
					}
					if (campingNights >= 50  && campingNights < 75) {
						System.out.print(" + Gold device");
					} else if (campingNights >= 75 && campingNights < 100) {
						System.out.print(" + 2 Gold devices");					
					} else if (campingNights >= 100 && campingNights < 125) {
						System.out.print("+ Silver device");
					}
					System.out.println("");
				}
			}		
		}
		return rv;
	}
	
	public static void main(String[] args) {
		if (args.length == 4) {
			//System.out.println("Merit Badge importer");
			try {
				MeritBadgeImporter mbi = new MeritBadgeImporter(args[0]);
				mbi.doImport();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				RankAdvancementImporter rai = new RankAdvancementImporter(args[1]);
				rai.doImport();
			} catch (IOException e ){
				e.printStackTrace();
			} catch (HeaderFormatException e) {
				System.err.println("Unable to parse header for Rank Advancement data");
				System.exit(1);
			}
			
			ScoutParticipationSummary sum;
			try {
				sum = new ScoutParticipationSummary(args[2]);
				sum.doImport();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				ScoutAwardsImporter sai = new ScoutAwardsImporter(args[3]);
				sai.doImport();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//ScoutFactory.getInstance().dump();
		NationalOutdoorAward nao = new NationalOutdoorAward();
		ScoutFactory sf = ScoutFactory.getInstance();
		System.out.println("NOA - Camping");
		System.out.println("=============");
		for (Scout s : sf.getScouts()) {
			nao.checkCampingAward(s, true);
		}
		System.out.println("\nNOA - Hiking");
		System.out.println("=============");
		for (Scout s : sf.getScouts()) {
			nao.checkHikingAward(s, true);
		}
	}
}
