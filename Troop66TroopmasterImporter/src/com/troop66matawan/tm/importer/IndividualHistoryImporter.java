package com.troop66matawan.tm.importer;

import java.io.IOException;
import java.util.Date;

import com.troop66matawan.tm.model.PositionOfResponsibility;
import com.troop66matawan.tm.model.Scout;
import com.troop66matawan.tm.model.ScoutFactory;

public class IndividualHistoryImporter extends TroopmasterImporter {

	public IndividualHistoryImporter(String filename) throws IOException {
		super(filename);
	}
	public void doImport() throws IOException {
		ScoutFactory f = ScoutFactory.getInstance();
		String line = _reader.readLine();
		Scout scout = null;
		boolean leadershipStart = false;
		boolean partialMBStart = false;
		boolean positionLine = false;
		
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
			} else if (scout != null)	{
				if (line.contains("\f")){
					leadershipStart = false;
					positionLine = false;
					partialMBStart = false;
					scout = null;
				} else if (line.contains("Pos'n of Respons")) {
					// get current Position of Responsibility
					String pos = line.substring(16,35).trim();
					if (pos.length() > 0 ) {
						String startDate = line.substring(46,54).trim();
						Date today = new Date();
						
						PositionOfResponsibility position = new PositionOfResponsibility(pos);
						position.set_startDate(stringToDate(startDate));
						position.set_endDate(today);
						scout.add_leadership(position);
						f.updateScout(scout);
					}
					positionLine = true;
				} else if (positionLine) {
					if (line.length() > 1) {
						// get current Position of Responsibility
						String pos = line.substring(16,35).trim();
						if (pos.length() > 0 ) {
							String startDate = line.substring(46,54).trim();
							Date today = new Date();
							
							PositionOfResponsibility position = new PositionOfResponsibility(pos);
							position.set_startDate(stringToDate(startDate));
							position.set_endDate(today);
							scout.add_leadership(position);
							f.updateScout(scout);
						}
					} else {
						positionLine = false;
					}
				}/*else if (line.contains("Partial MB's and Reqt's")) {
					partialMBStart = true;
				} else if (partialMBStart) {
					
				} */else if (line.contains("Leadership History")) {	
					leadershipStart = true;
				} else 	if (leadershipStart) {
					if (line.length() >= 79){
						String pos1 = line.substring(0,19).trim();
						if (pos1.length() > 0) {
							int indexOfDash = line.indexOf('-',20);
							String startDate = line.substring(indexOfDash-10,indexOfDash-1).trim();
							String endDate = line.substring(indexOfDash+2,indexOfDash+10).trim();
							PositionOfResponsibility position = new PositionOfResponsibility(pos1);
							position.set_startDate(stringToDate(startDate));
							position.set_endDate(stringToDate(endDate));
							scout.add_leadership(position);
							
							f.updateScout(scout);
						}
						
						String pos2 = line.substring(45,63).trim();
						if (pos2.length() > 0) {
							int indexOfDash = line.indexOf('-',63);
							String startDate = line.substring(63,indexOfDash-1).trim();
							String endDate = line.substring(indexOfDash+2,indexOfDash+10).trim();
							PositionOfResponsibility position = new PositionOfResponsibility(pos2);
							position.set_startDate(stringToDate(startDate));
							position.set_endDate(stringToDate(endDate));
							scout.add_leadership(position);
							
							f.updateScout(scout);
						}
					}
				} 
			}
			line = _reader.readLine();
		}
	}
}
