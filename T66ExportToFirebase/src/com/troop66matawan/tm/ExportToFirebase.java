package com.troop66matawan.tm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.troop66matawan.tm.importer.HeaderFormatException;
import com.troop66matawan.tm.importer.LeadershipPositionDaysNeededImporter;
import com.troop66matawan.tm.importer.MeritBadgesEarnedImporter;
import com.troop66matawan.tm.importer.RankBadgeMatrixImporter;
import com.troop66matawan.tm.importer.ScoutDataImporter;
import com.troop66matawan.tm.importer.ScoutIndividualParticipationImporter;
import com.troop66matawan.tm.importer.*;
import com.troop66matawan.tm.model.Scout;
import com.troop66matawan.tm.model.ScoutFactory;

public class ExportToFirebase {

	public ExportToFirebase()
	{
	}

	public static void usage() {
		System.err.println("Usage: <path to firebase creds> <path to Merit Badge export> <path to Rank Advancement> <path to Scout Data> <path to Individual Participation> [<path to Leadership needed]");
	}

	public static void main(String[] args) {
	if (args.length >= 6 && args.length <= 7) {
		try {
			MeritBadgesEarnedImporter mbi = new MeritBadgesEarnedImporter(args[1]);
			mbi.doImport();
		} catch (IOException e) {
			usage();
			e.printStackTrace();
			System.exit(1);
		}
		try{
			RankBadgeMatrixImporter rai = new RankBadgeMatrixImporter(args[2]);
			rai.doImport();
		} catch (IOException e) {
			usage();
			e.printStackTrace();
			System.exit(1);
		}
		try {
			ScoutDataImporter sdi = new ScoutDataImporter(args[3], true);
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
			ScoutIndividualParticipationImporter sipi = new ScoutIndividualParticipationImporter(args[4]);
			sipi.doImport();
		} catch (IOException e ) {
			usage();
			e.printStackTrace();
			System.exit(1);
		}
		try {
			IndividualHistoryImporter sipi = new IndividualHistoryImporter(args[5]);
			sipi.doImport();
		} catch (IOException e ) {
			usage();
			e.printStackTrace();
			System.exit(1);			
		}		
		if (args.length == 7) {
			try {
				LeadershipPositionDaysNeededImporter lpi = new LeadershipPositionDaysNeededImporter(args[6]);
				lpi.doImport();
			} catch (IOException e) {
				usage();
				e.printStackTrace();
				System.exit(1);
			}
		}

		export(args[0]);
	}
	else {
		usage();
	}
}

	private static void export(String firebaseAccountSettings) {
		try {
			FileInputStream serviceAccount = new FileInputStream(firebaseAccountSettings);

			FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
				.setDatabaseUrl("https://t66scoutinfo.firebaseio.com/")
			    .build();

			FirebaseApp.initializeApp(options);
		} catch (FileNotFoundException e) {
			System.err.println("Unable to load firebase config:"+e);
			e.printStackTrace();
			System.exit(1);
		}
		catch (IOException e) {
			System.err.println("Unable to initialize firebase");
			System.exit(1);
		}

		DatabaseReference scoutRef = FirebaseDatabase
			.getInstance()
			.getReference("scouts");

		ScoutFactory sf = ScoutFactory.getInstance();
		Iterator<Scout> it = sf.getScouts().iterator();
		Map<String, Object> scoutUpdates = new HashMap<String, Object>();

		Scout scout = it.next();
		scoutUpdates.put(scout.get_firstName()+scout.get_lastName(),scout);

		while (it.hasNext()) {
			scout = it.next();
			scoutUpdates.put(scout.get_firstName()+scout.get_lastName(),scout);
		}
		scoutRef.updateChildren(scoutUpdates);
	}

}
