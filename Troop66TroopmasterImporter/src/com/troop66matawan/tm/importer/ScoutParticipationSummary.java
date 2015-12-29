package com.troop66matawan.tm.importer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.troop66matawan.tm.model.Scout;
import com.troop66matawan.tm.model.ScoutFactory;

public class ScoutParticipationSummary {
	private BufferedReader _reader;

	public ScoutParticipationSummary(String filename) throws IOException {
		_reader = new BufferedReader( new FileReader(filename));
	}

	public void doImport() throws IOException {
		ScoutFactory f = ScoutFactory.getInstance();
		String line = _reader.readLine();
		while (line != null) {
			if (line.contains(",") && line.contains("/")) {
				//List<String> rv = Arrays.asList(line.split(","));
				int commaIndex = line.indexOf(",");
				String lName = line.substring(0, commaIndex);
				int spaceAfterNameIndex = line.indexOf(" ", commaIndex+2);
				String fName = line.substring(commaIndex+2, spaceAfterNameIndex);
				if (!f.hasScout(lName, fName)) {
					f.addScout(lName, fName);
				}
				Scout s = f.getScout(lName, fName);
				int slashIndex = line.indexOf("/",spaceAfterNameIndex);
				int spaceAfterNumberIndex = line.indexOf(" ", slashIndex+2);
				String nights = line.substring(slashIndex+2,spaceAfterNumberIndex);
				s.set_campingNights(Integer.valueOf(nights));
				f.updateScout(s);
			}
			line = _reader.readLine();
		}

	}
}
