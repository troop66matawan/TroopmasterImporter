package com.troop66matawan.tm.importer;

import java.io.IOException;
import java.util.ArrayList;

import com.troop66matawan.tm.model.MeritBadge;
import com.troop66matawan.tm.model.Scout;
import com.troop66matawan.tm.model.ScoutFactory;

public class MeritBadgesEarnedImporter extends TroopmasterImporter {
	private static final int MBNAME_COL1_start = 0;
	private static final int MBNAME_COL1_end = 19;
	private static final int MBNAME_COL2_start = 29;
	private static final int MBNAME_COL2_end = 48;
	private static final int MBNAME_COL3_start = 58;
	private static final int MBNAME_COL3_end = 77;
	private static final int MBDATE_COL1_start = 20;
	private static final int MBDATE_COL1_end = 27;
	private static final int MBDATE_COL2_start = 49;
	private static final int MBDATE_COL2_end = 56;
	private static final int MBDATE_COL3_start = 78;
	private static final int MBDATE_COL3_end = 85;
	
	private class MBTokenIndex {
		public int nameStart;
		public int nameEnd;
		public int dateStart;
		public int dateEnd;
		
		MBTokenIndex(int nameStartIndex, int nameEndIndex, int dateStartIndex, int dateEndIndex){
			nameStart = nameStartIndex;
			nameEnd = nameEndIndex;
			dateStart = dateStartIndex;
			dateEnd = dateEndIndex;
		}
	}
	
	private ArrayList<MBTokenIndex> mbcols = null;
	
	public MeritBadgesEarnedImporter(String filename) throws IOException {
		super(filename);
		
		mbcols = new ArrayList<MBTokenIndex>();
		mbcols.add(new MBTokenIndex(MBNAME_COL1_start, MBNAME_COL1_end, MBDATE_COL1_start, MBDATE_COL1_end));
		mbcols.add(new MBTokenIndex(MBNAME_COL2_start, MBNAME_COL2_end, MBDATE_COL2_start, MBDATE_COL2_end));
		mbcols.add(new MBTokenIndex(MBNAME_COL3_start, MBNAME_COL3_end, MBDATE_COL3_start, MBDATE_COL3_end));
		
	}
	
	private String trimMBName(String line, int startIndex, int endIndex) {
		String mbName = null;
		if ( endIndex <= line.trim().length())
			mbName = line.substring(startIndex, endIndex+1).replace('#', ' ').replace('*', ' ').trim();
		return mbName;
	}
	
	private void parseMB(Scout s, String line,  MBTokenIndex indexes) {
		String mbName = trimMBName(line, indexes.nameStart, indexes.nameEnd);
		if (mbName != null)	{
			String dateToken = line.substring(indexes.dateStart, indexes.dateEnd+1);
			MeritBadge mb = new MeritBadge(mbName);
			mb.set_earned(stringToDate(dateToken));
			s.addMeritBadge(mb);			
		}
		
	}
	private void parseMBLine(Scout s, String line) {
		if (line.length() >= MBDATE_COL1_end) {
			for (MBTokenIndex indexes : mbcols)	{
				parseMB(s, line, indexes );
			}
		}
	}
	public void doImport() throws IOException {
		ScoutFactory f = ScoutFactory.getInstance();
		String line = _reader.readLine();
		Scout scout = null;
		while (line != null) {
			// if no current scout, look for line containing string "Rank:"
			if (scout == null  &&  line.contains("Rank:")) {
				scout = getScout(f,line);
			} else if (scout != null) {
				if (line.contains("\f")){
					scout = null;
				} else {
					// Have scout, now get list of MBs
					parseMBLine(scout, line);					
				}
			}
			line = _reader.readLine();			
		}
	}
}
