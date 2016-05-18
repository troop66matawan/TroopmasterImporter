package com.troop66matawan.tm.importer;

import java.io.IOException;
import java.util.Date;

import com.troop66matawan.tm.model.RankAdvancement;
import com.troop66matawan.tm.model.Scout;
import com.troop66matawan.tm.model.ScoutFactory;

public class RankBadgeMatrixImporter extends TroopmasterImporter {
	private static final int SCOUT_DATE_startindex = 31;
	
	private enum Ranks {
		SCOUT,
		TENDERFOOT,
		SecondClass,
		FirstClass,
		Star,
		Life,
		Eagle
	}

	public RankBadgeMatrixImporter(String filename) throws IOException {
		super(filename);
	}
	
	public void doImport() throws IOException {
		ScoutFactory f = ScoutFactory.getInstance();
		String line = _reader.readLine();
		while (line != null) {
			if (line.length() > SCOUT_DATE_startindex)
			{
				if (line.contains(",")){
					parseRankDateLine(f, line);
				}
					
			}
			line = _reader.readLine();
		}
	}

	private void parseRankDateLine(ScoutFactory f, String line) {
		Scout scout = getScout(f, line);
		RankAdvancement ra = scout.get_rankAdvancement();
		if (ra == null || ra.getIsValid() != true)
			ra = new RankAdvancement();
		int index = SCOUT_DATE_startindex;
		for (Ranks r : Ranks.values()) {
			String dateString = getDateString(line,index);
			if (dateString.length() > 1) {
				Date date = stringToDate(dateString);
				switch (r) {
				case SCOUT:
					ra.set_scout(date);
					break;
				case TENDERFOOT:
					ra.set_tenderfoot(date);
					break;
				case SecondClass:
					ra.set_2ndClass(date);
					break;
				case FirstClass:
					ra.set_1stClass(date);
					break;
				case Star:
					ra.set_star(date);
					break;
				case Life:
					ra.set_life(date);
					break;
				case Eagle:
					ra.set_eagle(date);
					break;
				}
			}
			index += 10;
		}
		scout.set_rankAdvancement(ra);
		f.updateScout(scout);
	}

	private String getDateString(String line, int startIndex) {
		int spaceAfterIndex = line.indexOf(" ", startIndex);
		String dateString = line.substring(startIndex, spaceAfterIndex).trim();
		
		return dateString;
	}
	

}
