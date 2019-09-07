package com.troop66matawan.sb.importer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.troop66matawan.tm.model.Scout;
import com.troop66matawan.tm.model.ScoutFactory;

public class ScoutbookImporter {
	protected BufferedReader _reader;
	protected List<String> _headerTokens;
	private final static String LAST_NAME_HEADER = "Last Name";
	protected Integer lastNameIndex;
	private final static String FIRST_NAME_HEADER = "First Name";
	protected Integer firstNameIndex;
	private final static String BSAID_HEADER = "BSA Member ID";
	protected Integer bsaIdIndex;

	ScoutbookImporter(String filename) throws IOException {
		_reader = new BufferedReader( new FileReader(filename));
		String headerLine = _reader.readLine();
		_headerTokens = Arrays.asList(headerLine.split(","));
		lastNameIndex = _headerTokens.indexOf(LAST_NAME_HEADER);
		firstNameIndex = _headerTokens.indexOf(FIRST_NAME_HEADER);
		bsaIdIndex = _headerTokens.indexOf(BSAID_HEADER);
	}
	
	List<String> readTokenizedLine() throws IOException {
		List<String> rv = null;
		String line = _reader.readLine();
		if (line != null) {
			rv = Arrays.asList(line.split(","));
		}
		return rv ;		
	}
	
	public Scout getScout(ScoutFactory f, List<String> tokenizedLine) {

			
		String lName = tokenizedLine.get(lastNameIndex);
		String fName = tokenizedLine.get(firstNameIndex);

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
