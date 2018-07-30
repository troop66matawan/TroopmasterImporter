package com.troop66matawan.tm.importer;

import java.io.IOException;
import java.util.List;

import com.troop66matawan.tm.model.Adult;
import com.troop66matawan.tm.model.AdultFactory;
import com.troop66matawan.tm.model.ContactInfo;
import com.troop66matawan.tm.model.PositionOfResponsibility;

public class AdultDataImporter extends TroopmasterImporter {
	private static final String LASTNAME_HDR ="Last Name";
	private static final String FIRSTNAME_HDR ="First Name";
	private static final String NICKNAME_HDR = "Nickname";
	private static final String BSAID_HDR ="BSA ID#";
	private static final String DOB_HDR = "Date of Birth";

	// Contact info
	private static final String ADDRESS1_HDR = "Home Address Line 1";
	private static final String ADDRESS2_HDR = "Home Address Line 2";
	private static final String CITY_HDR = "Home City";
	private static final String STATE_HDR = "Home State";
	private static final String ZIP_HDR = "Home Zip";
	private static final String COUNTRY_HDR = "Home Country";
	private static final String HOMEPHONE_HDR = "Home Phone";
	private static final String WORKPHONE_HDR = "Work Phone";
	private static final String FAXPHONE_HDR = "Fax";
	private static final String CELLPHONE_HDR = "Cell Phone";
	private static final String PAGERPHONE_HDR = "Pager";
	private static final String VMPHONEPHDR_HDR = "Voice Mail";
	private static final String OTHERPHONE_HDR = "Other Phone";
	private static final String EMAIL1_HDR = "Email #1";
	private static final String EMAIL2_HDR = "Email #2";
	private static String JOINEDUNIT_HDR = "Joined Unit";
	private static String LDRPOS1_HDR = "Leadership Pos #1";
	private static String LDRPOS1DATE_HDR = "Leadership Pos Date #1";
	private static String LDRPOS2_HDR = "Leadership Pos #2";
	private static String LDRPOS2DATE_HDR = "Leadership Pos Date #2";
	private static String LDRPOS3_HDR = "Leadership Pos #3";
	private static String LDRPOS3DATE_HDR = "Leadership Pos Date #3";
	private static String LDRPOS4_HDR = "Leadership Pos #4";
	private static String LDRPOS4DATE_HDR = "Leadership Pos Date #4";
	
	private int lastNameIdx;
	private int firstNameIdx;
	private int bsaIDIdx;
	private int dobIdx;
	private int address1Idx;
	private int address2Idx;
	private int cityIdx;
	private int stateIdx;
	private int zipIdx;
	private int countryIdx;
	private int homephoneIdx;
	private int workphoneIdx;
	private int faxphoneIdx;
	private int cellphoneIdx;
	private int pagerphoneIdx;
	private int vmphonephdrIdx;
	private int otherphoneIdx;
	private int email1Idx;
	private int email2Idx;
	private int joinedunitidx;
	
	private int ldrPos1Idx;
	private int ldrPos1DateIdx;
	
	private int ldrPos2Idx;
	private int ldrPos2DateIdx;
	
	private int ldrPos3Idx;
	private int ldrPos3DateIdx;
	
	private int ldrPos4Idx;
	private int ldrPos4DateIdx;
	private boolean useNicknameAsFirstName = false;
	private int nicknameIdx;

	AdultDataImporter(String filename) throws  IOException, HeaderFormatException  {
		super(filename);
		lastNameIdx = _headerTokens.indexOf(LASTNAME_HDR);
		firstNameIdx = _headerTokens.indexOf(FIRSTNAME_HDR);
		bsaIDIdx = _headerTokens.indexOf(BSAID_HDR);
		nicknameIdx = _headerTokens.indexOf(NICKNAME_HDR);
		dobIdx = _headerTokens.indexOf(DOB_HDR);

		 address1Idx = _headerTokens.indexOf ( ADDRESS1_HDR);
		 address2Idx = _headerTokens.indexOf ( ADDRESS2_HDR);
		 cityIdx = _headerTokens.indexOf ( CITY_HDR);
		 stateIdx = _headerTokens.indexOf ( STATE_HDR);
		 zipIdx = _headerTokens.indexOf ( ZIP_HDR);
		 countryIdx = _headerTokens.indexOf ( COUNTRY_HDR);
		 homephoneIdx = _headerTokens.indexOf ( HOMEPHONE_HDR);
		 workphoneIdx = _headerTokens.indexOf ( WORKPHONE_HDR);
		 faxphoneIdx = _headerTokens.indexOf ( FAXPHONE_HDR);
		 cellphoneIdx = _headerTokens.indexOf ( CELLPHONE_HDR);
		 pagerphoneIdx = _headerTokens.indexOf ( PAGERPHONE_HDR);
		 vmphonephdrIdx = _headerTokens.indexOf ( VMPHONEPHDR_HDR);
		 otherphoneIdx = _headerTokens.indexOf (OTHERPHONE_HDR );
		 email1Idx = _headerTokens.indexOf ( EMAIL1_HDR);
		 email2Idx = _headerTokens.indexOf ( EMAIL2_HDR);

		 joinedunitidx= _headerTokens.indexOf (JOINEDUNIT_HDR  );
		 ldrPos1Idx= _headerTokens.indexOf (LDRPOS1_HDR  );
		 ldrPos1DateIdx= _headerTokens.indexOf (LDRPOS1DATE_HDR  );
		 ldrPos2Idx= _headerTokens.indexOf (LDRPOS2_HDR  );
		 ldrPos2DateIdx= _headerTokens.indexOf (LDRPOS2DATE_HDR  );		 
		 ldrPos3Idx= _headerTokens.indexOf (LDRPOS3_HDR  );
		 ldrPos3DateIdx= _headerTokens.indexOf (LDRPOS3DATE_HDR  );
		 ldrPos4Idx= _headerTokens.indexOf (LDRPOS4_HDR  );
		 ldrPos4DateIdx= _headerTokens.indexOf (LDRPOS4DATE_HDR  );

		 
		 
		if (lastNameIdx == -1 || firstNameIdx == -1 || bsaIDIdx == -1 || nicknameIdx == -1 || dobIdx == -1 ||
		 address1Idx == -1 || address2Idx == -1 || cityIdx == -1 || stateIdx == -1 || zipIdx == -1 ||
		 countryIdx == -1 || homephoneIdx == -1 || workphoneIdx == -1 || faxphoneIdx == -1 || cellphoneIdx == -1 ||
		 pagerphoneIdx == -1 || vmphonephdrIdx == -1 || otherphoneIdx == -1 || email1Idx == -1 || email2Idx == -1 ||
		 joinedunitidx== -1 || ldrPos1Idx== -1 || ldrPos1DateIdx== -1 || ldrPos2Idx== -1 || ldrPos2DateIdx== -1 ||
		 ldrPos3Idx== -1 || ldrPos3DateIdx== -1 || ldrPos4Idx== -1 ||ldrPos4DateIdx== -1) {
			throw new HeaderFormatException();
		}
	}
	public AdultDataImporter(String filename, boolean useNicknameAsFirstName) throws IOException, HeaderFormatException {
		this(filename);
		this.useNicknameAsFirstName = useNicknameAsFirstName;
	}
	
	public void doImport() throws IOException {
		AdultFactory f = AdultFactory.getInstance();
		List<String> tokens = super.readTokenizedLine(); 
		while (tokens != null) {
			String lName = tokens.get(lastNameIdx);
			String fName;
			if (this.useNicknameAsFirstName)
				fName = tokens.get(nicknameIdx);
			else
				fName = tokens.get(firstNameIdx);
			if (!f.hasScout(lName, fName)) {
				f.addAdult(lName, fName);
			}
			Adult a = f.getAdult(lName, fName);
			a.set_bsaID(tokens.get(bsaIDIdx));
			a.set_dateOfBirth(stringToDate(tokens.get(dobIdx)));
			a.set_joinedUnit(stringToDate(tokens.get(joinedunitidx)));
			ContactInfo contact = new ContactInfo(tokens.get(address1Idx),tokens.get(cityIdx),tokens.get(stateIdx),tokens.get(zipIdx));
			contact.set_email1(tokens.get(email1Idx));
			contact.set_email2(tokens.get(email2Idx));
			contact.set_homephone(tokens.get(homephoneIdx));
			contact.set_workphone(tokens.get(workphoneIdx));
			contact.set_cellphone(tokens.get(cellphoneIdx));
			
			a.set_contactInfo(contact);
			
			PositionOfResponsibility ldrPos1 = new PositionOfResponsibility(tokens.get(ldrPos1Idx));
			ldrPos1.set_startDate(stringToDate(tokens.get(ldrPos1Idx)));
			a.add_leadership(ldrPos1);
			
			PositionOfResponsibility ldrPos2 = new PositionOfResponsibility(tokens.get(ldrPos2Idx));
			ldrPos2.set_startDate(stringToDate(tokens.get(ldrPos2Idx)));
			a.add_leadership(ldrPos2);

			PositionOfResponsibility ldrPos3 = new PositionOfResponsibility(tokens.get(ldrPos3Idx));
			ldrPos3.set_startDate(stringToDate(tokens.get(ldrPos3Idx)));
			a.add_leadership(ldrPos3);

			PositionOfResponsibility ldrPos4 = new PositionOfResponsibility(tokens.get(ldrPos4Idx));
			ldrPos4.set_startDate(stringToDate(tokens.get(ldrPos4Idx)));
			a.add_leadership(ldrPos4);
			
			f.updateAdult(a);
			tokens = super.readTokenizedLine();
		}
	}
}
