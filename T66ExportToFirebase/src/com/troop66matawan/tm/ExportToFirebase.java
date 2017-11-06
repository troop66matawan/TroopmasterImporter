package com.troop66matawan.tm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.tasks.OnFailureListener;
import com.google.firebase.tasks.Task;
import com.troop66matawan.tm.importer.HeaderFormatException;
import com.troop66matawan.tm.importer.IndividualHistoryImporter;
import com.troop66matawan.tm.importer.LeadershipPositionDaysNeededImporter;
import com.troop66matawan.tm.importer.MeritBadgesEarnedImporter;
import com.troop66matawan.tm.importer.RankBadgeMatrixImporter;
import com.troop66matawan.tm.importer.ScoutDataImporter;
import com.troop66matawan.tm.importer.ScoutIndividualParticipationImporter;
import com.troop66matawan.tm.model.Adult;
import com.troop66matawan.tm.model.AdultFactory;
import com.troop66matawan.tm.model.ContactInfo;
import com.troop66matawan.tm.model.Parent;
import com.troop66matawan.tm.model.Scout;
import com.troop66matawan.tm.model.ScoutFactory;
import com.troop66matawan.utils.ConsoleOutput;
import com.troop66matawan.utils.Output;
import com.troop66matawan.utils.PasswordGenerator;

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

		Output logger = new ConsoleOutput();
		initializeFirebase(args[0], logger);
		export(logger);
	}
	else {
		usage();
	}
}

	static void initializeFirebase(String firebaseAccountSettings, Output logger) {
		try {
			FileInputStream serviceAccount = new FileInputStream(firebaseAccountSettings);

			FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
				.setDatabaseUrl("https://t66scoutinfo.firebaseio.com/")
			    .build();

			FirebaseApp.initializeApp(options);
		} catch (FileNotFoundException e) {
			logger.append("Unable to load firebase config:"+e);
			logger.append(e);
			System.exit(1);
		}
		catch (IOException e) {
			logger.append("Unable to initialize firebase");
			System.exit(1);
		}
		
	}
	
	static void updateUserSettings(Output logger) {
		Map<String,Object> usersUpdate = new HashMap<String,Object>();
		Map<String, Object> accessUpdate = new HashMap<String, Object>();

		FirebaseAuth authInst = FirebaseAuth.getInstance();
		FirebaseDatabase dbInst = FirebaseDatabase.getInstance();
		DatabaseReference usersRef = dbInst.getReference("users");
		DatabaseReference accessRef = dbInst.getReference("access");

		updateAdults(logger,usersUpdate,authInst);
		updateScouts(logger, usersUpdate, accessUpdate, authInst);
		logger.append("Uploading to firebase users");
		usersRef.updateChildren(usersUpdate);		
		accessRef.updateChildren(accessUpdate);
	}
	
	private static void updateAdults(Output logger, Map<String, Object> usersUpdate, FirebaseAuth authInst) {
		AdultFactory af = AdultFactory.getInstance();
		Iterator<Adult> it = af.getAdults().iterator();

		while (it.hasNext()) {
			Adult adult = it.next();
			Map<String,Task<UserRecord>> authRequests = new HashMap<String,Task<UserRecord>>();
			ContactInfo contact = adult.get_contactInfo();
			if (contact.get_email1().length() > 0){
				String firstName = adult.get_firstName();
				String lastName = adult.get_lastName();
						
				String email = contact.get_email1();
				getOrCreateUser(logger, authInst, authRequests, firstName, lastName, email);
			}
			//wait until user records valid
			Collection<Task<UserRecord>> r = authRequests.values();
			while (true) {
				boolean complete = true;
				Iterator<Task<UserRecord>> rIt = r.iterator();
				while (rIt.hasNext()) {
					Task<UserRecord> task = rIt.next();
					if (!task.isSuccessful()) {
						complete = false;
						break;
					}
				}
				if (complete) 
					break;			
				try {
					//logger.append("sleeping .5 sec");
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}			
			// all tasks complete 
			if (contact.get_email1().length() > 0 ) {
				String email = contact.get_email1();
				Task<UserRecord> t = authRequests.get(email);
				UserRecord userAuth = t.getResult();
				String uid = userAuth.getUid();
				ExportUsers exUser = new ExportUsers();
				exUser.setFirstName(adult.get_firstName());
				exUser.setLastName(adult.get_lastName());
				if (adult.isScoutmaster()) 
					exUser.setPosition("scoutmaster");
				else if (adult.isEagleCommittee()) 
					exUser.setPosition("eaglecommittee");
				usersUpdate.put(uid, exUser);				
			}
		}
	}

	private static void updateScouts(Output logger, Map<String, Object> usersUpdate, Map<String, Object> accessUpdate, FirebaseAuth authInst) {
		ScoutFactory sf = ScoutFactory.getInstance();
		Iterator<Scout> it = sf.getScouts().iterator();

		while (it.hasNext()) {
			Scout scout = it.next();
			Map<String,Task<UserRecord>> authRequests = new HashMap<String,Task<UserRecord>>();
			ContactInfo contact = scout.get_contactInfo();
			if (contact.get_email1().length() > 0){
				String firstName = scout.get_firstName();
				String lastName = scout.get_lastName();
						
				String email = contact.get_email1();
				getOrCreateUser(logger, authInst, authRequests, firstName, lastName, email);
			}
			List<Parent> parents = scout.get_parentContactInfo();
			for (Parent p : parents) {
				if (p.get_email1().length() > 0) {
					String firstName = p.get_firstName();
					String lastName = p.get_lastName();
							
					String email = p.get_email1();
					getOrCreateUser(logger, authInst, authRequests, firstName, lastName, email);
				}
			}
			//wait until user records valid
			Collection<Task<UserRecord>> r = authRequests.values();
			int iterations = 0;
			while (true) {
				boolean complete = true;
				Iterator<Task<UserRecord>> rIt = r.iterator();
				while (rIt.hasNext()) {
					Task<UserRecord> task = rIt.next();
					if (!task.isSuccessful()) {
						complete = false;
						break;
					} 
					else {
						if (iterations > 40) {
							System.out.println(scout.get_firstName()+scout.get_lastName()+":"+contact.get_email1()+":"+task.getException().toString());
							return;
						}
					}
				}
				if (complete) 
					break;			
				try {
					//logger.append("sleeping .5 sec");
					TimeUnit.MILLISECONDS.sleep(500);
					++iterations;
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			Map<String,Boolean> scoutAccess = new HashMap<String,Boolean>();
			// all tasks complete 
			if (contact.get_email1().length() > 0 ) {
				String email = contact.get_email1();
				Task<UserRecord> t = authRequests.get(email);
				UserRecord userAuth = t.getResult();
				String uid = userAuth.getUid();
				ExportUsers exUser = new ExportUsers();
				exUser.setFirstName(scout.get_firstName());
				exUser.setLastName(scout.get_lastName());
				exUser.addAccess(scout.get_firstName()+scout.get_lastName());
				usersUpdate.put(uid, exUser);				
				scoutAccess.put(uid, true);
			}
			for (Parent p : parents) {
				if (p.get_email1().length() > 0) {
					String firstName = p.get_firstName();
					String lastName = p.get_lastName();
							
					String email = p.get_email1();
					Task<UserRecord> t = authRequests.get(email);
					UserRecord userAuth = t.getResult();
					String uid = userAuth.getUid();
					
					ExportUsers exUser = (ExportUsers) usersUpdate.get(uid);
					if (exUser == null) {
						exUser = new ExportUsers();
						exUser.setFirstName(firstName);
						exUser.setLastName(lastName);
					}
					exUser.addAccess(scout.get_firstName()+scout.get_lastName());
					usersUpdate.put(uid, exUser);
					scoutAccess.put(uid, true);
				}
			}
			accessUpdate.put(scout.get_firstName()+scout.get_lastName(), scoutAccess);
		}
	}

	private static void getOrCreateUser(Output logger, FirebaseAuth authInst,
			Map<String, Task<UserRecord>> authRequests, String firstName, String lastName, String email) {
		Task<UserRecord> task = authInst.getUserByEmail(email);
		task.addOnFailureListener(new OnFailureListener() {
			
			@Override
			public void onFailure(Exception e) {
				FirebaseAuthException fae = (FirebaseAuthException)e;
				String errorCode = fae.getErrorCode();
				if (errorCode == "USER_NOT_FOUND_ERROR") {
					Task<UserRecord> newTask = createNewUser(authInst,firstName, lastName,email,logger);
					authRequests.put(email, newTask);
				}
				
			}
		});
		authRequests.put(email, task);
	}

	private static Task<UserRecord> createNewUser(FirebaseAuth authInst, String firstName, String lastName, String email, Output logger) {
		UserRecord.CreateRequest request = new UserRecord.CreateRequest();
		request.setEmail(email);
		PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
		        .useDigits(true)
		        .useLower(true)
		        .useUpper(true)
		        .build();
		String password = passwordGenerator.generate(8); // output ex.: lrU12fmM 75iwI90o
		request.setPassword(password);
		logger.append(firstName + ',' + lastName + ',' + email +',' + password+'\n');
		return authInst.createUser(request);
	}

	static void export(Output logger) {

		FirebaseDatabase dbInst = FirebaseDatabase.getInstance();
		DatabaseReference scoutRef = dbInst.getReference("scouts");
		DatabaseReference contactRef = dbInst.getReference("scout_contact");

		ScoutFactory sf = ScoutFactory.getInstance();
		Iterator<Scout> it = sf.getScouts().iterator();
		Map<String, Object> scoutUpdates = new HashMap<String, Object>();
		Map<String, Object> contactUpdates = new HashMap<String, Object>();

		Scout scout = it.next();
		ExportScout exScout = new ExportScout(scout);
		exScout.parseActivities();
		scoutUpdates.put(scout.get_firstName()+scout.get_lastName(),exScout);
		contactUpdates.put(scout.get_firstName()+scout.get_lastName(), scout.get_contactInfo());

		while (it.hasNext()) {
			scout = it.next();
			exScout = new ExportScout(scout);
			exScout.parseActivities();
			scoutUpdates.put(scout.get_firstName()+scout.get_lastName(),exScout);
			contactUpdates.put(scout.get_firstName()+scout.get_lastName(), scout.get_contactInfo());
		}
		logger.append("Uploading to firebase scouts");
		scoutRef.updateChildren(scoutUpdates);
		logger.append("Uploading to firebase scout_contact");
		contactRef.updateChildren(contactUpdates);
	}

}
