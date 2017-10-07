package com.troop66matawan.tm.importer;

import java.io.IOException;
import java.util.List;

import com.troop66matawan.tm.model.ContactInfo;
import com.troop66matawan.tm.model.Parent;
import com.troop66matawan.tm.model.Scout;
import com.troop66matawan.tm.model.ScoutFactory;

public class ScoutDataImporter extends TroopmasterImporter {
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
private static String CURRENTRANK_HDR = "Current Rank";
private static String RANKDATE_HDR = "Rank Date";
private static String PATROL_HDR = "Patrol";
private static String LDRPOS1_HDR = "Leadership Pos #1";
private static String LDRPOS1DATE_HDR = "Leadership Pos Date #1";
private static String LDRPOS2_HDR = "Leadership Pos #2";
private static String LDRPOS2DATE_HDR = "Leadership Pos Date #2";
private static String OA_HDR = "O/A Member (Y/N)";
private static String PARENT1RELATION_HDR = "Parent #1 Relation";
private static String PARENT1LASTNAME_HDR = "Parent #1 Last Name";
private static String PARENT1FIRSTNAME_HDR = "Parent #1 First Name";
//private static String "Parent #1 Middle Name";
//private static String "Parent #1 Suffix";
//private static String "Parent #1 Nickname";
private static String PARENT1GUARDIAN_HDR = "Parent #1 Guardian (Y/N)";
private static String PARENT1GENDER_HDR = "Parent #1 Sex (M/F)";
private static String PARENT1HOMEPHONE_HDR = "Parent #1 Home Phone";
private static String PARENT1WORKPHONE_HDR = "Parent #1 Work Phone";
private static String PARENT1FAX_HDR = "Parent #1 Fax";
private static String PARENT1CELL_HDR = "Parent #1 Cell Phone";
private static String PARENT1PAGE_HDR = "Parent #1 Pager";
private static String PARENT1VM_HDR = "Parent #1 Voice Mail";
private static String PARENT1OTHER_HDR = "Parent #1 Other Phone";
private static String PARENT1EMAIL1_HDR = "Parent #1 Email #1";
private static String PARENT1EMAIL2_HDR = "Parent #1 Email #2";
private static String PARENT2RELATION_HDR = "Parent #2 Relation";
private static String PARENT2LASTNAME_HDR = "Parent #2 Last Name";
private static String PARENT2FIRSTNAME_HDR = "Parent #2 First Name";
//private static String "Parent #2 Middle Name";
//private static String "Parent #2 Suffix";
//private static String "Parent #2 Nickname";
private static String PARENT2GUARDIAN_HDR = "Parent #2 Guardian (Y/N)";
private static String PARENT2GENGER_HDR = "Parent #2 Sex (M/F)";
private static String PARENT2HOMEPHONE_HDR = "Parent #2 Home Phone";
private static String PARENT2WORKPHONE_HDR = "Parent #2 Work Phone";
private static String PARENT2FAX_HDR = "Parent #2 Fax";
private static String PARENT2CELL_HDR = "Parent #2 Cell Phone";
private static String PARENT2PAGER_HDR = "Parent #2 Pager";
private static String PARENT2VM_HDR = "Parent #2 Voice Mail";
private static String PARENT2OTHER_HDR = "Parent #2 Other Phone";
private static String PARENT2EMAIL1_HDR = "Parent #2 Email #1";
private static String PARENT2EMAIL2_HDR = "Parent #2 Email #2";
	//Cub Scout From,Cub Scout To,Highest Cub Badge,Boys' Life (Y/N),
	//Mailing Address Line 1,Mailing Address Line 2,Mailing City,Mailing State,Mailing Zip,Mailing Country,
	//,Date of Birth,SSN,Drivers License,License State,Grade,School,Church,
	//Swimming Level,Swimming Date,Remarks,Boy Scout (Y/N),Varsity Scout (Y/N),Venturer (Y/N),SeaScout (Y/N),Emergency Contact #1,Emergency Phone #1,Emergency Contact #2,Emergency Phone #2,Health Form on File (Y/N),Doctor,Doctor's Phone,Insurance Company,Insurance Phone,Insurance Policy,Insurance Group,Medications,Allergies,Other Health Information,Health Form A,Health Form B,Health Form C,Health Form D,Tetanus Shot,
	//O/A Active (Y/N),O/A Election/Recommended Date,O/A Call Out Date,O/A Ordeal Date,O/A Brotherhood Date,O/A Vigil Date,O/A Vigil Name,O/A Leadership Pos,O/A Leadership Pos Date,O/A Remarks,MOS Foxman Date,MOS Brave Date,MOS Warrior Date,MOS Honorary Warrior/Honored Woman,MOS Tribal Council (Y/N),MOS She She Be Council (Y/N),MOS Leadership Pos,MOS Leadership Pos Date,MOS Tribal Name,MOS Remarks,
	//Parent #1 SSN,Parent #1 Drivers License,Parent #1 License State,Parent #1 Employer,Parent #1 Occupation,Parent #1 Work Code,
	//Parent #2 SSN,Parent #2 Drivers License,Parent #2 License State,Parent #2 Employer,Parent #2 Occupation,Parent #2 Work Code,Vehicle #1 Year,Make #1,Model #1,Number of Seat Belts #1,License Plate #1,Trailer Hitch (Y/N) #1,Ins Per Person #1,Ins Per Accident #1,Personal Property Ins #1,Vehicle #2 Year,Make #2,Model #2,Number of Seat Belts #2,License Plate #2,Trailer Hitch (Y/N) #2,Ins Per Person #2,Ins Per Accident #2,Personal Property Ins #2,
	//Parent #3 Relation,Parent #3 Last Name,Parent #3 First Name,Parent #3 Middle Name,Parent #3 Suffix,Parent #3 Nickname,Parent #3 Guardian (Y/N),Parent #3 Sex (M/F),Parent #3 Spouse,Parent #3 Home Address Line 1,Parent #3 Home Address Line 2,Parent #3 Home City,Parent #3 Home State,Parent #3 Home Zip,Parent #3 Home Country,Parent #3 Mailing Address Line 1,Parent #3 Mailing Address Line 2,Parent #3 Mailing City,Parent #3 Mailing State,Parent #3 Mailing Zip,Parent #3 Mailing Country,Parent #3 Home Phone,Parent #3 Work Phone,Parent #3 Fax,Parent #3 Cell Phone,Parent #3 Pager,Parent #3 Voice Mail,Parent #3 Other Phone,Parent #3 Email #1,Parent #3 Email #2,Parent #3 SSN,Parent #3 Drivers License,Parent #3 License State,Parent #3 Employer,Parent #3 Occupation,Parent #3 Work Code,Parent #3 Remarks,Parent #3 Vehicle Year,Parent #3 Make,Parent #3 Model,Parent #3 Number of Seat Belts,Parent #3 License Plate,Parent #3 Trailer Hitch (Y/N),Parent #3 Ins Per Person,Parent #3 Ins Per Accident,Parent #3 Personal Property Ins
	
	//,Middle Name,Suffix,,Sex (M/F),
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
	private int currentrankidx;
	private int rankdateidx;
	private int patrolidx;
	private int ldrpos1idx;
	private int ldrpos1dateidx;
	private int ldrpos2idx;
	private int ldrpos2dateidx;
	private int oaidx;
	private int parent1relationidx;
	private int parent1lastnameidx;
	private int parent1firstnameidx;
	//private int "parent #1 middle name";
	//private int "parent #1 suffix";
	//private int "parent #1 nickname";
	private int parent1guardianidx;
	private int parent1genderidx;
	private int parent1homephoneidx;
	private int parent1workphoneidx;
	private int parent1faxidx;
	private int parent1cellidx;
	private int parent1pageidx;
	private int parent1vmidx;
	private int parent1otheridx;
	private int parent1email1idx;
	private int parent1email2idx;
	private int parent2relationidx;
	private int parent2lastnameidx;
	private int parent2firstnameidx;
	//private int "parent #2 middle name";
	//private int "parent #2 suffix";
	//private int "parent #2 nickname";
	private int parent2guardianidx;
	private int parent2gengeridx;
	private int parent2homephoneidx;
	private int parent2workphoneidx;
	private int parent2faxidx;
	private int parent2cellidx;
	private int parent2pageridx;
	private int parent2vmidx;
	private int parent2otheridx;
	private int parent2email1idx;
	private int parent2email2idx;

	private boolean useNicknameAsFirstName = false;
	private int nicknameIdx;
	public ScoutDataImporter(String filename) throws IOException, HeaderFormatException {
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
		 currentrankidx= _headerTokens.indexOf (CURRENTRANK_HDR  );
		 rankdateidx= _headerTokens.indexOf (RANKDATE_HDR  );
		 patrolidx= _headerTokens.indexOf (PATROL_HDR  );
		 ldrpos1idx= _headerTokens.indexOf (LDRPOS1_HDR  );
		 ldrpos1dateidx= _headerTokens.indexOf (LDRPOS1DATE_HDR  );
		 ldrpos2idx= _headerTokens.indexOf (LDRPOS2_HDR  );
		 ldrpos2dateidx= _headerTokens.indexOf (LDRPOS2DATE_HDR  );
		 oaidx= _headerTokens.indexOf (OA_HDR  );
		 
		 parent1relationidx= _headerTokens.indexOf (PARENT1RELATION_HDR  );
		 parent1lastnameidx= _headerTokens.indexOf (PARENT1LASTNAME_HDR  );
		 parent1firstnameidx= _headerTokens.indexOf (PARENT1FIRSTNAME_HDR  );
		 parent1guardianidx= _headerTokens.indexOf (PARENT1GUARDIAN_HDR);
		 parent1genderidx= _headerTokens.indexOf (PARENT1GENDER_HDR  );
		 parent1homephoneidx= _headerTokens.indexOf (PARENT1HOMEPHONE_HDR  );
		 parent1workphoneidx= _headerTokens.indexOf (PARENT1WORKPHONE_HDR  );
		 parent1faxidx= _headerTokens.indexOf (PARENT1FAX_HDR  );
		 parent1cellidx= _headerTokens.indexOf (PARENT1CELL_HDR  );
		 parent1pageidx= _headerTokens.indexOf (PARENT1PAGE_HDR  );
		 parent1vmidx= _headerTokens.indexOf (PARENT1VM_HDR  );
		 parent1otheridx= _headerTokens.indexOf (PARENT1OTHER_HDR  );
		 parent1email1idx= _headerTokens.indexOf (PARENT1EMAIL1_HDR  );
		 parent1email2idx= _headerTokens.indexOf (PARENT1EMAIL2_HDR  );
		 parent2relationidx= _headerTokens.indexOf (PARENT2RELATION_HDR  );
		 parent2lastnameidx= _headerTokens.indexOf (PARENT2LASTNAME_HDR  );
		 parent2firstnameidx= _headerTokens.indexOf (PARENT2FIRSTNAME_HDR  );
		 parent2guardianidx= _headerTokens.indexOf (PARENT2GUARDIAN_HDR  );
		 parent2gengeridx= _headerTokens.indexOf (PARENT2GENGER_HDR  );
		 parent2homephoneidx= _headerTokens.indexOf (PARENT2HOMEPHONE_HDR  );
		 parent2workphoneidx= _headerTokens.indexOf (PARENT2WORKPHONE_HDR  );
		 parent2faxidx= _headerTokens.indexOf (PARENT2FAX_HDR  );
		 parent2cellidx= _headerTokens.indexOf (PARENT2CELL_HDR  );
		 parent2pageridx= _headerTokens.indexOf (PARENT2PAGER_HDR  );
		 parent2vmidx= _headerTokens.indexOf (PARENT2VM_HDR  );
		 parent2otheridx= _headerTokens.indexOf (PARENT2OTHER_HDR  );
		 parent2email1idx= _headerTokens.indexOf (PARENT2EMAIL1_HDR  );
		 parent2email2idx= _headerTokens.indexOf (PARENT2EMAIL2_HDR  );



		if (lastNameIdx == -1 || firstNameIdx == -1 || bsaIDIdx == -1 || nicknameIdx == -1 || dobIdx == -1 ||
			  address1Idx == -1 || address2Idx == -1 || cityIdx == -1 || stateIdx == -1 || zipIdx == -1 ||
			  countryIdx == -1 || homephoneIdx == -1 || workphoneIdx == -1 || faxphoneIdx == -1 ||
			  cellphoneIdx == -1 || pagerphoneIdx == -1 || vmphonephdrIdx == -1 || otherphoneIdx == -1 ||
			  email1Idx == -1 || email2Idx == -1 || joinedunitidx== -1 || currentrankidx== -1 ||
			  rankdateidx== -1 || patrolidx== -1 || ldrpos1idx== -1 || ldrpos1dateidx== -1 || ldrpos2idx== -1 ||
			  ldrpos2dateidx== -1 || oaidx== -1 || parent1relationidx== -1 || parent1lastnameidx== -1 ||
			  parent1firstnameidx== -1 || parent1guardianidx== -1 || parent1genderidx== -1 ||
			  parent1homephoneidx== -1 || parent1workphoneidx== -1 || parent1faxidx== -1 ||
			  parent1cellidx== -1 || parent1pageidx== -1 || parent1vmidx== -1 || parent1otheridx== -1 ||
			  parent1email1idx== -1 || parent1email2idx== -1 ||
			  parent2relationidx== -1 || parent2lastnameidx== -1 || parent2firstnameidx== -1 ||
			  parent2guardianidx== -1 || parent2gengeridx== -1 || parent2homephoneidx== -1 || 
			  parent2workphoneidx== -1 || parent2faxidx== -1 || parent2cellidx== -1 || 
			  parent2pageridx== -1 || parent2vmidx== -1 || parent2otheridx== -1 || 
			  parent2email1idx== -1 || parent2email2idx== -1 				)
			throw new HeaderFormatException();
	}
	public ScoutDataImporter(String filename, boolean useNicknameAsFirstName) throws IOException, HeaderFormatException {
		this(filename);
		this.useNicknameAsFirstName = useNicknameAsFirstName;
	}
	
	public void doImport() throws IOException {
		ScoutFactory f = ScoutFactory.getInstance();
		List<String> tokens = super.readTokenizedLine(); 
		while (tokens != null) {
			String lName = tokens.get(lastNameIdx);
			String fName;
			if (this.useNicknameAsFirstName)
				fName = tokens.get(nicknameIdx);
			else
				fName = tokens.get(firstNameIdx);
			if (!f.hasScout(lName, fName)) {
				f.addScout(lName, fName);
			}
			Scout s = f.getScout(lName, fName);
			s.set_bsaID(tokens.get(bsaIDIdx));
			s.set_dateOfBirth(stringToDate(tokens.get(dobIdx)));
			ContactInfo contact = new ContactInfo(tokens.get(address1Idx),tokens.get(cityIdx),tokens.get(stateIdx),tokens.get(zipIdx));
			contact.set_email1(tokens.get(email1Idx));
			contact.set_email2(tokens.get(email2Idx));
			contact.set_homephone(tokens.get(homephoneIdx));
			contact.set_workphone(tokens.get(workphoneIdx));
			contact.set_cellphone(tokens.get(cellphoneIdx));
			
			s.set_contactInfo(contact);
			
			Parent parent1 = new Parent(tokens.get(address1Idx),tokens.get(cityIdx),tokens.get(stateIdx),tokens.get(zipIdx));
			parent1.set_relation(tokens.get(parent1relationidx));
			parent1.set_lastName( tokens.get(parent1lastnameidx));
			parent1.set_firstName( tokens.get(parent1firstnameidx));
			parent1.set_guardian( stringToBoolean(tokens.get(parent1guardianidx)));
			parent1.set_gender( tokens.get(parent1genderidx));
			parent1.set_homephone( tokens.get(parent1homephoneidx));
			parent1.set_workphone( tokens.get(parent1workphoneidx));
			parent1.set_faxphone( tokens.get(parent1faxidx));
			parent1.set_cellphone( tokens.get(parent1cellidx));
			parent1.set_email1( tokens.get(parent1email1idx));
			parent1.set_email2( tokens.get(parent1email2idx));
			
			s.add_parentContactInfo(parent1);
						
			Parent parent2 = new Parent(tokens.get(address1Idx),tokens.get(cityIdx),tokens.get(stateIdx),tokens.get(zipIdx));
			parent2.set_relation(tokens.get(parent2relationidx));
			parent2.set_lastName( tokens.get(parent2lastnameidx));
			parent2.set_firstName( tokens.get(parent2firstnameidx));
			parent2.set_guardian( stringToBoolean(tokens.get(parent2guardianidx)));
			parent2.set_gender( tokens.get(parent2gengeridx));
			parent2.set_homephone( tokens.get(parent2homephoneidx));
			parent2.set_workphone( tokens.get(parent2workphoneidx));
			parent2.set_faxphone( tokens.get(parent2faxidx));
			parent2.set_cellphone( tokens.get(parent2cellidx));
			parent2.set_email1( tokens.get(parent2email1idx));
			parent2.set_email2( tokens.get(parent2email2idx));

			s.add_parentContactInfo(parent2);
			
			f.updateScout(s);
			tokens = super.readTokenizedLine();
		}
	}
}
