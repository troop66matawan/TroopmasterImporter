package com.troop66matawan.tm.importer;

import java.io.IOException;
import java.util.List;

import com.troop66matawan.tm.model.Scout;
import com.troop66matawan.tm.model.ScoutFactory;

public class ScoutDataImporter extends TroopmasterImporter {
	private static final String LASTNAME_HDR ="Last Name";
	private static final String FIRSTNAME_HDR ="First Name";
	private static final String NICKNAME_HDR = "Nickname";
	private static final String BSAID_HDR ="BSA ID#";
	private static final String DOB_HDR = "Date of Birth";

	//,Middle Name,Suffix,,Sex (M/F),Home Address Line 1,Home Address Line 2,Home City,Home State,Home Zip,Home Country,Mailing Address Line 1,Mailing Address Line 2,Mailing City,Mailing State,Mailing Zip,Mailing Country,Home Phone,Work Phone,Fax,Cell Phone,Pager,Voice Mail,Other Phone,Email #1,Email #2,,Date of Birth,SSN,Drivers License,License State,Grade,School,Church,Joined Unit,Cub Scout From,Cub Scout To,Highest Cub Badge,Boys' Life (Y/N),Current Rank,Rank Date,Patrol,Swimming Level,Swimming Date,Remarks,Boy Scout (Y/N),Varsity Scout (Y/N),Venturer (Y/N),SeaScout (Y/N),Emergency Contact #1,Emergency Phone #1,Emergency Contact #2,Emergency Phone #2,Health Form on File (Y/N),Doctor,Doctor's Phone,Insurance Company,Insurance Phone,Insurance Policy,Insurance Group,Medications,Allergies,Other Health Information,Health Form A,Health Form B,Health Form C,Health Form D,Tetanus Shot,Leadership Pos #1,Leadership Pos Date #1,Leadership Pos #2,Leadership Pos Date #2,O/A Member (Y/N),O/A Active (Y/N),O/A Election/Recommended Date,O/A Call Out Date,O/A Ordeal Date,O/A Brotherhood Date,O/A Vigil Date,O/A Vigil Name,O/A Leadership Pos,O/A Leadership Pos Date,O/A Remarks,MOS Foxman Date,MOS Brave Date,MOS Warrior Date,MOS Honorary Warrior/Honored Woman,MOS Tribal Council (Y/N),MOS She She Be Council (Y/N),MOS Leadership Pos,MOS Leadership Pos Date,MOS Tribal Name,MOS Remarks,Parent #1 Relation,Parent #1 Last Name,Parent #1 First Name,Parent #1 Middle Name,Parent #1 Suffix,Parent #1 Nickname,Parent #1 Guardian (Y/N),Parent #1 Sex (M/F),Parent #1 Home Phone,Parent #1 Work Phone,Parent #1 Fax,Parent #1 Cell Phone,Parent #1 Pager,Parent #1 Voice Mail,Parent #1 Other Phone,Parent #1 Email #1,Parent #1 Email #2,Parent #1 SSN,Parent #1 Drivers License,Parent #1 License State,Parent #1 Employer,Parent #1 Occupation,Parent #1 Work Code,Parent #2 Relation,Parent #2 Last Name,Parent #2 First Name,Parent #2 Middle Name,Parent #2 Suffix,Parent #2 Nickname,Parent #2 Guardian (Y/N),Parent #2 Sex (M/F),Parent #2 Home Phone,Parent #2 Work Phone,Parent #2 Fax,Parent #2 Cell Phone,Parent #2 Pager,Parent #2 Voice Mail,Parent #2 Other Phone,Parent #2 Email #1,Parent #2 Email #2,Parent #2 SSN,Parent #2 Drivers License,Parent #2 License State,Parent #2 Employer,Parent #2 Occupation,Parent #2 Work Code,Vehicle #1 Year,Make #1,Model #1,Number of Seat Belts #1,License Plate #1,Trailer Hitch (Y/N) #1,Ins Per Person #1,Ins Per Accident #1,Personal Property Ins #1,Vehicle #2 Year,Make #2,Model #2,Number of Seat Belts #2,License Plate #2,Trailer Hitch (Y/N) #2,Ins Per Person #2,Ins Per Accident #2,Personal Property Ins #2,Parent #3 Relation,Parent #3 Last Name,Parent #3 First Name,Parent #3 Middle Name,Parent #3 Suffix,Parent #3 Nickname,Parent #3 Guardian (Y/N),Parent #3 Sex (M/F),Parent #3 Spouse,Parent #3 Home Address Line 1,Parent #3 Home Address Line 2,Parent #3 Home City,Parent #3 Home State,Parent #3 Home Zip,Parent #3 Home Country,Parent #3 Mailing Address Line 1,Parent #3 Mailing Address Line 2,Parent #3 Mailing City,Parent #3 Mailing State,Parent #3 Mailing Zip,Parent #3 Mailing Country,Parent #3 Home Phone,Parent #3 Work Phone,Parent #3 Fax,Parent #3 Cell Phone,Parent #3 Pager,Parent #3 Voice Mail,Parent #3 Other Phone,Parent #3 Email #1,Parent #3 Email #2,Parent #3 SSN,Parent #3 Drivers License,Parent #3 License State,Parent #3 Employer,Parent #3 Occupation,Parent #3 Work Code,Parent #3 Remarks,Parent #3 Vehicle Year,Parent #3 Make,Parent #3 Model,Parent #3 Number of Seat Belts,Parent #3 License Plate,Parent #3 Trailer Hitch (Y/N),Parent #3 Ins Per Person,Parent #3 Ins Per Accident,Parent #3 Personal Property Ins
	private int lastNameIdx;
	private int firstNameIdx;
	private int bsaIDIdx;
	private int dobIdx;

	private boolean useNicknameAsFirstName = false;
	private int nicknameIdx;
	public ScoutDataImporter(String filename) throws IOException, HeaderFormatException {
		super(filename);
		lastNameIdx = _headerTokens.indexOf(LASTNAME_HDR);
		firstNameIdx = _headerTokens.indexOf(FIRSTNAME_HDR);
		bsaIDIdx = _headerTokens.indexOf(BSAID_HDR);
		nicknameIdx = _headerTokens.indexOf(NICKNAME_HDR);
		dobIdx = _headerTokens.indexOf(DOB_HDR);
		
		if (lastNameIdx == -1 || firstNameIdx == -1 || bsaIDIdx == -1 || nicknameIdx == -1 || dobIdx == -1)
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
			f.updateScout(s);
			tokens = super.readTokenizedLine();
		}
	}
}
