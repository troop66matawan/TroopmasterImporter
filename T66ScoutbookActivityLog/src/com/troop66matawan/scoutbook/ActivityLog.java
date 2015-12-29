package com.troop66matawan.scoutbook;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;













import com.troop66matawan.tm.importer.HeaderFormatException;
import com.troop66matawan.tm.importer.ScoutDataImporter;
import com.troop66matawan.tm.importer.ScoutIndividualParticipationImporter;
import com.troop66matawan.tm.model.Activity;
import com.troop66matawan.tm.model.Camping;
import com.troop66matawan.tm.model.Hiking;
import com.troop66matawan.tm.model.Scout;
import com.troop66matawan.tm.model.ScoutFactory;
import com.troop66matawan.tm.model.ServiceProject;

public class ActivityLog {
	private Collection<Scout> scouts;
	
	ActivityLog(Collection<Scout> s)
	{
		this.scouts = s;
	}
	
	public void printCSV()
	{
		System.out.println("BSA Member ID,First Name,Middle Name,Last Name,Log Type,Date,Nights,Days,Miles,Hours,Frost Points,Location/Name,Notes");
		for (Scout s : scouts)
		{
			String bsaId = s.get_bsaID();
			for (Activity a : s.get_activities())
			{
				if (bsaId != null)
					System.out.print(bsaId);
				System.out.print(",");
				System.out.print(s.get_firstName());
				System.out.print(",");
				System.out.print(","); // No Middle Name
				System.out.print(s.get_lastName());
				System.out.print(",");
				printCSV(a);
				System.out.println();
			}
		}
	}

	public void printCSV(Activity a)
	{
		if (a instanceof Camping)
			printCSV((Camping)a);
		else if (a instanceof Hiking)
			printCSV((Hiking)a);
		else if (a instanceof ServiceProject)
			printCSV((ServiceProject)a);
	}

	public void printCSV(Camping c) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		System.out.print("Camping,");
		System.out.print(sdf.format(c.getActivityDate()));
		System.out.print(",");
		System.out.print(c.getAmount().toString()); // Nights
		System.out.print(",");
		System.out.print("0,"); //Days
		System.out.print(","); // Miles
		System.out.print(","); // Hours
		System.out.print("0,"); // Frost Points
		System.out.print(c.getLocation().replace(',','-'));
		System.out.print(",");
		System.out.print(c.getRemarks().replace(',', '-'));
	}
	public void printCSV(Hiking c) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		System.out.print("Hiking,");
		System.out.print(sdf.format(c.getActivityDate()));
		System.out.print(",");
		System.out.print(","); //Miles
		System.out.print(","); //Days
		System.out.print(c.getAmount().toString()); // Miles
		System.out.print(","); // Hours
		System.out.print(","); // Frost Points
		System.out.print(c.getLocation().replace(',','-'));
		System.out.print(",");
		System.out.print(c.getRemarks().replace(',', '-'));
	}
	public void printCSV(ServiceProject c) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		System.out.print("Service,");
		System.out.print(sdf.format(c.getActivityDate()));
		System.out.print(",");
		System.out.print(c.getAmount().toString()); // Nights
		System.out.print(",");
		System.out.print("0,"); //Days
		System.out.print(","); // Miles
		System.out.print(","); // Hours
		System.out.print("0,"); // Frost Points
		System.out.print(c.getLocation().replace(',','-'));
		System.out.print(",");
		System.out.print(c.getRemarks().replace(',', '-'));
	}

	public static void main(String[] args) {
		if (args.length == 1) {
			try {
				ScoutIndividualParticipationImporter ipi = new ScoutIndividualParticipationImporter(args[0]);
				ipi.doImport();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			ScoutFactory sf = ScoutFactory.getInstance();
			ActivityLog log = new ActivityLog(sf.getScouts());
			log.printCSV();
			
		}
		else if (args.length == 2) {
			try {
				ScoutDataImporter sdi = new ScoutDataImporter(args[0], true);
				sdi.doImport();
				ScoutIndividualParticipationImporter ipi = new ScoutIndividualParticipationImporter(args[1]);
				ipi.doImport();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (HeaderFormatException e) {
				System.err.println("Unable to parse header for Scout data");
				System.exit(1);
			}
		
			ScoutFactory sf = ScoutFactory.getInstance();
			ActivityLog log = new ActivityLog(sf.getScouts());
			log.printCSV();
			
			
		} else {
			System.err.println("Required arguments: path to Troopmaster Individual Participation Report");
		}
	}
}
