package com.troop66matawan.tm.importer;

import java.io.IOException;
import java.util.List;

import com.troop66matawan.tm.model.Scout;
import com.troop66matawan.tm.model.ScoutAwards;
import com.troop66matawan.tm.model.ScoutFactory;

public class ScoutAwardsImporter extends TroopmasterImporter {

	private static final int LASTNAME_INDEX = 0;
	private static final int FIRSTNAME_INDEX = 1;
	private static final int FIRST_AWARD_INDEX = 4;

	public ScoutAwardsImporter(String filename) throws IOException {
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
			for (int index = FIRST_AWARD_INDEX; index < tokens.size(); index += 2) {
				ScoutAwards a = new ScoutAwards();
				a.set_name(tokens.get(index));
				a.set_earned(stringToDate(tokens.get(index+1)));
				s.addScoutAward(a);
			}
			f.updateScout(s);
			tokens = super.readTokenizedLine();
		}
	}

}
