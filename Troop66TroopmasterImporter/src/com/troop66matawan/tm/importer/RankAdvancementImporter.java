package com.troop66matawan.tm.importer;

import java.io.IOException;
import java.util.List;

import com.troop66matawan.tm.model.RankAdvancement;
import com.troop66matawan.tm.model.Scout;
import com.troop66matawan.tm.model.ScoutFactory;

public class RankAdvancementImporter extends TroopmasterImporter {
	private static final int LASTNAME_INDEX = 0;
	private static final int FIRSTNAME_INDEX = 1;
	public static final String SCOUT_RANK ="Scout";
	public static final String TENDERFOOT_RANK = "Tenderfoot";
	public static final String SECONDCLASS_RANK ="2nd Class";
	public static final String FIRSTCLASS_RANK = "1st Class";
	public static final String STAR_RANK = "Star";
	public static final String LIFE_RANK = "Life";
	public static final String EAGLE_RANK = "Eagle";

	
	public RankAdvancementImporter(String filename) throws IOException {
		super(filename);
		}

	public void doImport() throws IOException, HeaderFormatException {
		ScoutFactory f = ScoutFactory.getInstance();
		List<String> tokens = super.readTokenizedLine(); 
		
		int scoutIndex = super._headerTokens.indexOf(SCOUT_RANK);
		int tenderfootIndex = super._headerTokens.indexOf(TENDERFOOT_RANK);
		int secClassIdx = super._headerTokens.indexOf(SECONDCLASS_RANK);
		int fstClassIdx = super._headerTokens.indexOf(FIRSTCLASS_RANK);
		int starIdx = super._headerTokens.indexOf(STAR_RANK);
		int lifeIdx = super._headerTokens.indexOf(LIFE_RANK);
		int eagleIdx = super._headerTokens.indexOf(EAGLE_RANK);
		if (scoutIndex == -1 || tenderfootIndex == -1 || secClassIdx == -1 || fstClassIdx == -1 || starIdx == -1 || lifeIdx == -1 || eagleIdx == -1)
			throw new HeaderFormatException();
		while (tokens != null) {
			String lName = tokens.get(LASTNAME_INDEX);
			String fName = tokens.get(FIRSTNAME_INDEX);
			if (!f.hasScout(lName, fName)) {
				f.addScout(lName, fName);
			}
			Scout s = f.getScout(lName, fName);
			RankAdvancement ra = new RankAdvancement();
			int lastIndex = tokens.size() -1;
			if (scoutIndex != -1 && scoutIndex <= lastIndex) {
				String date = tokens.get(scoutIndex);
				if (date.length() > 0)
					ra.set_scout(stringToDate(date));
			}
			if (tenderfootIndex != -1 && tenderfootIndex <= lastIndex) {
				String date = tokens.get(tenderfootIndex);
				if (date.length() > 0)
					ra.set_tenderfoot(stringToDate(date));
			}
			if (secClassIdx != -1 && secClassIdx <= lastIndex) {
				String date = tokens.get(secClassIdx);
				if (date.length() > 0)
					ra.set_2ndClass(stringToDate(date));
			}
			if (fstClassIdx != -1 && fstClassIdx <= lastIndex) {
				String date = tokens.get(fstClassIdx);
				if (date.length() > 0)
					ra.set_1stClass(stringToDate(date));
			}
			if (starIdx != -1 && starIdx <= lastIndex) {
				String date = tokens.get(starIdx);
				if (date.length() > 0)
					ra.set_star(stringToDate(date));
			}
			if (lifeIdx != -1 && lifeIdx <= lastIndex) {
				String date = tokens.get(lifeIdx);
				if (date.length() > 0)
					ra.set_life(stringToDate(date));
			}
			if (eagleIdx != -1 && eagleIdx <= lastIndex) {
				String date = tokens.get(eagleIdx);
				if (date.length() > 0)
					ra.set_eagle(stringToDate(date));
			}
			s.set_rankAdvancement(ra);

			f.updateScout(s);
			tokens = super.readTokenizedLine();
		}
	}

}
