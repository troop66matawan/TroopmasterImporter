package com.troop66matawan.tm.importer;

import java.io.IOException;

import com.troop66matawan.tm.model.RankAdvancement;
import com.troop66matawan.tm.model.Scout;
import com.troop66matawan.tm.model.ScoutFactory;

public class LeadershipPositionDaysNeededImporter extends TroopmasterImporter {
	private static final int LDR_POS_startindex = 83;

	LeadershipPositionDaysNeededImporter(String filename) throws IOException {
		super(filename);
	}
	public void doImport() throws IOException {
		ScoutFactory f = ScoutFactory.getInstance();
		String line = _reader.readLine();
		while (line != null) {
			if (line.length() > LDR_POS_startindex)
			{
				if (line.contains(",")){
					parseLeaderPositionLine(f, line);
				}
			}
			line = _reader.readLine();
		}
	}
	
	private void parseLeaderPositionLine(ScoutFactory f, String line) {
		Scout scout = getScout(f, line);
		RankAdvancement ra = scout.get_rankAdvancement();
		if (ra == null || ra.getIsValid() != true)
			ra = new RankAdvancement();
		int index = LDR_POS_startindex;
		int spaceAfterIndex = line.indexOf(" ", index);
		String hoursString = line.substring(index, spaceAfterIndex).trim();
		Integer days = Integer.valueOf(hoursString);
		ra.set_neededLeadership(days);
				
		scout.set_rankAdvancement(ra);
		f.updateScout(scout);
	}

}
