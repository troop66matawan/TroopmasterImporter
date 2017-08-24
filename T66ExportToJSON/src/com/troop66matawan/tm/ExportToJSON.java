package com.troop66matawan.tm;

import java.io.IOException;
import java.util.Iterator;

import com.troop66matawan.tm.importer.HeaderFormatException;
import com.troop66matawan.tm.importer.LeadershipPositionDaysNeededImporter;
import com.troop66matawan.tm.importer.MeritBadgesEarnedImporter;
import com.troop66matawan.tm.importer.RankBadgeMatrixImporter;
import com.troop66matawan.tm.importer.ScoutDataImporter;
import com.troop66matawan.tm.importer.ScoutIndividualParticipationImporter;
import com.troop66matawan.tm.model.Scout;
import com.troop66matawan.tm.model.ScoutFactory;

public class ExportToJSON {

	public ExportToJSON()
	{
	}
	
	public static void usage() {
		System.err.println("Usage: <path to Merit Badge export> <path to Rank Advancement> <path to Scout Data> <path to Individual Participation> [<path to Leadership needed]");
	}
	
	public static void main(String[] args) {
	if (args.length >= 4 && args.length <= 5) {
		try {
			MeritBadgesEarnedImporter mbi = new MeritBadgesEarnedImporter(args[0]);
			mbi.doImport();
		} catch (IOException e) {
			usage();
			e.printStackTrace();
			System.exit(1);
		}
		try{
			RankBadgeMatrixImporter rai = new RankBadgeMatrixImporter(args[1]);
			rai.doImport();
		} catch (IOException e) {
			usage();
			e.printStackTrace();
			System.exit(1);
		}
		try {
			ScoutDataImporter sdi = new ScoutDataImporter(args[2], true);
			sdi.doImport();
		}catch (IOException e) {
			usage();
			e.printStackTrace();
			System.exit(1);
		} catch (HeaderFormatException e) {
			System.err.println("Unable to parse header for Scout data");
			usage();
			System.exit(1);
		}
		try {
			ScoutIndividualParticipationImporter sipi = new ScoutIndividualParticipationImporter(args[3]);
			sipi.doImport();
		} catch (IOException e ) {
			usage();
			e.printStackTrace();
			System.exit(1);			
		}
		if (args.length == 5) {
			try {
				LeadershipPositionDaysNeededImporter lpi = new LeadershipPositionDaysNeededImporter(args[4]);
				lpi.doImport();
			} catch (IOException e) {
				usage();
				e.printStackTrace();
				System.exit(1);
			}
		}
		
	
		ScoutFactory sf = ScoutFactory.getInstance();
		System.out.println("[");
		Iterator<Scout> it = sf.getScouts().iterator();
		Scout scout = it.next();
		System.out.print(scout.toJSON().toString());		
		while (it.hasNext()) {
			System.out.println(",");
			scout = it.next();
			System.out.print(scout.toJSON().toString());	
		}
		System.out.println("");
		System.out.println("]");

	}
	else {
		usage();
	}
}

}
