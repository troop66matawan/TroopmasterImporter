package com.troop66matawan.tm.importer;

import java.io.IOException;

public class ActivityImporter extends TroopmasterImporter {
	private final static String ACTIVITY_HDR = "Activity Type";
	private final static String CABINCAMP_HDR = "Cabin Camp (Y/N)";
	private final static String SUMMERCAMP_HDR= "Summer Camp (Y/N)";
	private final static String VALID2ND1ST_HDR = "Valid for 2nd/1st Class (Y/N)";
	private final static String BACKPACKING_HDR = "Back Packing (Y/N)";
	//Attendence Percentage;
	private final static String DATE_HDR = "Activity Date";
	private final static String STARTTIME_HDR = "Start Time";
	private final static String ENDTIME_HDR= "End Time";
	private final static String AMOUNT_HDR = "Amount (Hours  Miles  etc.)";
	private final static String LOCATION_HDR = "Location";
	private final static String REMARKS_hdr = "Remarks";
	
	private int activityIdx;
	private int cabinCampIdx;
	private int summerCampIdx;
	private int valid2nd1stIdx;
	private int backpackingIdx;
	//Attendence Percentage;
	private int dateIdx;
	private int startTimeIdx;
	private int endTimeIdx;
	private int amountIdx;
	private int locationIdx;
	private int remarksIdx;
	
	ActivityImporter(String filename) throws  IOException, HeaderFormatException  {
		super(filename);
		activityIdx = _headerTokens.indexOf(ACTIVITY_HDR);
		cabinCampIdx = _headerTokens.indexOf(CABINCAMP_HDR);
		summerCampIdx = _headerTokens.indexOf(SUMMERCAMP_HDR);
		valid2nd1stIdx = _headerTokens.indexOf(VALID2ND1ST_HDR);
		backpackingIdx = _headerTokens.indexOf(BACKPACKING_HDR);
		dateIdx = _headerTokens.indexOf(DATE_HDR);
		startTimeIdx = _headerTokens.indexOf(STARTTIME_HDR);
		endTimeIdx = _headerTokens.indexOf(ENDTIME_HDR);
		amountIdx = _headerTokens.indexOf(AMOUNT_HDR);
		locationIdx = _headerTokens.indexOf(LOCATION_HDR);
		remarksIdx = _headerTokens.indexOf(REMARKS_hdr);
		
		if (activityIdx== -1 || cabinCampIdx== -1 || summerCampIdx== -1 || valid2nd1stIdx== -1 ||
		 backpackingIdx== -1 || dateIdx== -1 || startTimeIdx== -1 || endTimeIdx== -1 || amountIdx== -1 ||
		 locationIdx== -1 || remarksIdx== -1) {
			throw new HeaderFormatException();
		}
	}
}
