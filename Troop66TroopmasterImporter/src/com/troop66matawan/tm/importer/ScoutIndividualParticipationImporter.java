package com.troop66matawan.tm.importer;

import java.io.IOException;

import com.troop66matawan.tm.model.Camping;
import com.troop66matawan.tm.model.Hiking;
import com.troop66matawan.tm.model.Meeting;
import com.troop66matawan.tm.model.Scout;
import com.troop66matawan.tm.model.ScoutFactory;
import com.troop66matawan.tm.model.ServiceProject;

public class ScoutIndividualParticipationImporter extends TroopmasterImporter{

	public ScoutIndividualParticipationImporter(String filename) throws IOException {
		super(filename);
	}

	public void doImport() throws IOException {
		ScoutFactory f = ScoutFactory.getInstance();
		String line = _reader.readLine();
		Scout scout = null;
		boolean activityList = false;
		while (line != null) {
			if (scout == null  && line.contains(",") && line.contains("Name:")) {
				String nameLine = line.substring(5).trim();
				int commaIndex = nameLine.indexOf(",");
				String lName = nameLine.substring(0, commaIndex);
				int spaceAfterNameIndex = nameLine.indexOf(" ", commaIndex+2);
				String fName = nameLine.substring(commaIndex+2, spaceAfterNameIndex);

				if (!f.hasScout(lName, fName)) {
					f.addScout(lName, fName);
				}
				scout = f.getScout(lName, fName);
			} else if (scout == null && line.contains(",") && line.contains("(cont)")) {
				// page continuation
				int commaIndex = line.indexOf(",");
				String lName = line.substring(0, commaIndex);
				int spaceAfterNameIndex = line.indexOf(" ", commaIndex+2);
				String fName = line.substring(commaIndex+2, spaceAfterNameIndex);

				if (!f.hasScout(lName, fName)) {
					f.addScout(lName, fName);
				}
				scout = f.getScout(lName, fName);
				
			}else if (scout != null)
			{
				if (line.contains("\f")){
					activityList = false;
					scout = null;
				} else 	if (activityList) {
					if (line.length() >= 79){
						String date = line.substring(0,8);
						String type = line.substring(16,29).trim();
						Integer amount = 0;
						try {
							amount = Integer.valueOf(line.substring(30,34).trim());
						} catch (NumberFormatException e)
						{
							// Try to see if floating 
							try {
								String value = line.substring(30,34).trim();
								Float amt = Float.valueOf(value);
								amount = Math.round(amt);
							} catch (NumberFormatException ex) {
								// give up do nothing;
							}
						}
						// Location field size = 19, Remarks Size = 29
						String location = line.substring(36,54);
						String remarks = line.substring(55,79);							
						
						if (type.contains("Camping"))
						{
							boolean isCabin = type.contains("*");
							scout.addActivity(new Camping(stringToDate(date), amount, location, remarks, isCabin));
						} else if (type.contains("Hiking"))
						{
							scout.addActivity(new Hiking(stringToDate(date), amount, location, remarks));
							
						} else if (type.contains("Serv"))
						{
							scout.addActivity(new ServiceProject(stringToDate(date), amount, location, remarks));
							
						} else if (type.contains("Meeting"))
						{
							scout.addActivity(new Meeting(stringToDate(date),amount, location, remarks ));
						}

						f.updateScout(scout);
					}
				} else if (line.contains("Date") && line.contains("Level"))
				{	
					activityList = true;
				}
			}
			line = _reader.readLine();
		}

	}
}

