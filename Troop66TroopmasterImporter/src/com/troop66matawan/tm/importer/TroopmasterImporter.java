package com.troop66matawan.tm.importer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.troop66matawan.tm.model.Scout;
import com.troop66matawan.tm.model.ScoutFactory;

public class TroopmasterImporter {

	protected BufferedReader _reader;
	protected List<String> _headerTokens;

	TroopmasterImporter(String filename) throws IOException {
		_reader = new BufferedReader( new FileReader(filename));
		String headerLine = _reader.readLine();
		_headerTokens = Arrays.asList(headerLine.split(","));
	}
	
	List<String> readTokenizedLine() throws IOException {
		List<String> rv = null;
		String line = _reader.readLine();
		if (line != null) {
			rv = Arrays.asList(line.split(","));
		}
		return rv ;		
	}
	public Scout getScout(ScoutFactory f, String line) {
		// Substring before comma is last name, from comma to space is first name
		int commaIndex = line.indexOf(",");
		int startIndex = 0;
		if (line.startsWith("*"))
		{
			startIndex = 1;
		}
			
		String lName = line.substring(startIndex, commaIndex);
		int spaceAfterNameIndex = line.indexOf(" ", commaIndex+2);
		String fName = line.substring(commaIndex+2, spaceAfterNameIndex);

		if (!f.hasScout(lName, fName)) {
			f.addScout(lName, fName);
		}
		return f.getScout(lName, fName);
	}


	public Date stringToDate(String x) {
		String[] date = x.split("/");
		if (date.length ==3) {
			Calendar c = Calendar.getInstance();
			Integer year = Integer.valueOf( date[2]);
			if (year < 100)
				year += 2000;
			c.clear();
			c.set(year, Integer.valueOf(date[0])-1, Integer.valueOf(date[1]) );
			return  c.getTime();			
		}
		return null;

	}

	public boolean stringToBoolean(String x ) {
		boolean rv = false;
		if (x.toLowerCase().startsWith("y"))
			rv = true;
		return rv;	
	}
}
