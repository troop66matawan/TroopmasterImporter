package com.troop66matawan.tm.importer;

import java.io.IOException;

import com.troop66matawan.tm.model.RankAdvancement;
import com.troop66matawan.tm.model.Scout;
import com.troop66matawan.tm.model.ScoutFactory;
/*
 Format is below numbers are present only for spacing
`05/13/16   ( 26 Scouts  *Applicable toward next rank )        
                           Scouts Needing Service Project Hours                           
          1         2         3         4         5         6         7         8       
012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789
                                                                                  Hours   
Name                          Rank        Patrol              Phone             Remaining 
 
*LastName, FirstName          Star        Patrol name         (H)(###) ###-####     6     
*LastName, FirstName          Star        Patrol name         (H)(###) ###-####     6     
*LastName, FirstName          Star        Patrol name         (H)(###) ###-####     6     
*LastName, FirstName          Star        Patrol name         (H)(###) ###-####     6     
*LastName, FirstName          Star        Patrol name         (H)(###) ###-####    5.5     
*LastName, FirstName          Star        Patrol name         (H)(###) ###-####     6     
`

 */
public class ServiceProjectHoursNeededImporter extends TroopmasterImporter {
	private static final int SVC_HOURS_startindex = 83;

	public ServiceProjectHoursNeededImporter(String filename) throws IOException {
		super(filename);
	}

	public void doImport() throws IOException {
		ScoutFactory f = ScoutFactory.getInstance();
		String line = _reader.readLine();
		while (line != null) {
			if (line.length() > SVC_HOURS_startindex)
			{
				if (line.contains(",")){
					parseServiceHoursLine(f, line);
				}
			}
			line = _reader.readLine();
		}
	}
	
	private void parseServiceHoursLine(ScoutFactory f, String line) {
		Scout scout = getScout(f, line);
		RankAdvancement ra = scout.get_rankAdvancement();
		if (ra == null || ra.getIsValid() != true)
			ra = new RankAdvancement();
		int index = SVC_HOURS_startindex;
		int spaceAfterIndex = line.indexOf(" ", index+1);
		String hoursString = line.substring(index, spaceAfterIndex).trim();
		Integer hours=0;
		if (hoursString.contains(".")) {
			Float floatHours = Float.valueOf(hoursString);
			hours = Math.round(floatHours);
		} else {
			hours = Integer.valueOf(hoursString);
		}
		ra.set_neededServiceHours(hours);
				
		scout.set_rankAdvancement(ra);
		f.updateScout(scout);
	}

}
