package com.troop66matawan.tm.importer;

import java.io.IOException;
import java.util.List;

import com.troop66matawan.tm.model.MeritBadge;
import com.troop66matawan.tm.model.Scout;
import com.troop66matawan.tm.model.ScoutFactory;

public class MeritBadgeImporter extends TroopmasterImporter {

	private static final int LASTNAME_INDEX = 0;
	private static final int FIRSTNAME_INDEX = 1;
	private static final int FIRST_MB_INDEX = 4;

	public MeritBadgeImporter(String filename) throws IOException {
		super(filename);
	}
	
	public void doImport() throws IOException {
		ScoutFactory f = ScoutFactory.getInstance();
		List<String> tokens = super.readTokenizedLine(); 
		while (tokens != null) {
			String lName = tokens.get(LASTNAME_INDEX);
			String fName = tokens.get(FIRSTNAME_INDEX);
			if (!f.hasScout(lName, fName)) {
				f.addScout(lName, fName);
			}
			Scout s = f.getScout(lName, fName);
			for (int index = FIRST_MB_INDEX; index < tokens.size(); index += 2) {
				MeritBadge m = new MeritBadge();
				m.set_name(tokens.get(index));
				m.set_earned(stringToDate(tokens.get(index+1)));
				s.addMeritBadge(m);
			}
			f.updateScout(s);
			tokens = super.readTokenizedLine();
		}
	}
}
