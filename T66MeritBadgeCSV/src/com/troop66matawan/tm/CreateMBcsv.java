package com.troop66matawan.tm;



import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;

import com.troop66matawan.tm.importer.MeritBadgeImporter;
import com.troop66matawan.tm.model.MeritBadge;
import com.troop66matawan.tm.model.Scout;
import com.troop66matawan.tm.model.ScoutFactory;

public class CreateMBcsv {
	private Collection<Scout> scouts;
	
	CreateMBcsv(Collection<Scout> s)
	{
		this.scouts = s;
	}
	
	public void printCSV()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

		System.out.println("Last Name,First Name,MB Name,MB Earn Date"); 
		for (Scout s : scouts)
		{
			for (MeritBadge mb : s.getMeritBadges())
			{
				System.out.print(s.get_lastName());
				System.out.print(",");
				System.out.print(s.get_firstName());
				System.out.print(",");
				System.out.print(mb.get_name());
				System.out.print(",");
				System.out.println(sdf.format(mb.get_earned()));
			}
		}
	}


	public static void main(String[] args) {
		if (args.length == 1) {
			try {
				MeritBadgeImporter mbi = new MeritBadgeImporter(args[0]);
				mbi.doImport();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
			ScoutFactory sf = ScoutFactory.getInstance();
			CreateMBcsv log = new CreateMBcsv(sf.getScouts_alphaSort());
			log.printCSV();
			
		}
		else {
			System.err.println("Required arguments: path to Scout Merit Badge export");
		}
	}
}
