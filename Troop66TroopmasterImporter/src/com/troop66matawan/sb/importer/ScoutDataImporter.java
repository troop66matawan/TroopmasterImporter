package com.troop66matawan.sb.importer;


import java.io.IOException;
import java.util.List;

import com.troop66matawan.tm.importer.HeaderFormatException;
import com.troop66matawan.tm.model.ContactInfo;
import com.troop66matawan.tm.model.Parent;
import com.troop66matawan.tm.model.Scout;
import com.troop66matawan.tm.model.ScoutFactory;

public class ScoutDataImporter extends ScoutbookImporter {
	private static final String NICKNAME_HDR = "Nickname";
	private static final String DOB_HDR = "DOB";

	// Contact info
	private static final String ADDRESS1_HDR = "Address 1";
	private static final String ADDRESS2_HDR = "Address 2";
	private static final String CITY_HDR = "City";
	private static final String STATE_HDR = "State";
	private static final String ZIP_HDR = "Zip";
	private static final String HOMEPHONE_HDR = "Home Phone";

private static String JOINEDUNIT_HDR = "Date Joined Scouts BSA";
private static String PATROL_HDR = "Patrol Name";
private static String OA_HDR = "OA Active";
private static String PARENT1EMAIL1_HDR = "Parent #1 Email #1";
private static String PARENT2EMAIL1_HDR = "Parent #2 Email #1";
	
	private int dobIdx;

	private int address1Idx;
	private int address2Idx;
	private int cityIdx;
	private int stateIdx;
	private int zipIdx;
	private int homephoneIdx;
	private int joinedunitidx;
	private int patrolidx;
	private int oaidx;
	private int parent1email1idx;
	private int parent2email1idx;

	private boolean useNicknameAsFirstName = false;
	private int nicknameIdx;
	public ScoutDataImporter(String filename) throws IOException, HeaderFormatException {
		super(filename);
		nicknameIdx = _headerTokens.indexOf(NICKNAME_HDR);
		dobIdx = _headerTokens.indexOf(DOB_HDR);

		 address1Idx = _headerTokens.indexOf ( ADDRESS1_HDR);
		 address2Idx = _headerTokens.indexOf ( ADDRESS2_HDR);
		 cityIdx = _headerTokens.indexOf ( CITY_HDR);
		 stateIdx = _headerTokens.indexOf ( STATE_HDR);
		 zipIdx = _headerTokens.indexOf ( ZIP_HDR);
		 homephoneIdx = _headerTokens.indexOf ( HOMEPHONE_HDR);

		 joinedunitidx= _headerTokens.indexOf (JOINEDUNIT_HDR  );
		 patrolidx= _headerTokens.indexOf (PATROL_HDR  );
		 oaidx= _headerTokens.indexOf (OA_HDR  );
		 
		 parent1email1idx= _headerTokens.indexOf (PARENT1EMAIL1_HDR  );
		 parent2email1idx= _headerTokens.indexOf (PARENT2EMAIL1_HDR  );



		if (nicknameIdx == -1 || dobIdx == -1 ||
			  address1Idx == -1 || address2Idx == -1 || cityIdx == -1 || stateIdx == -1 || zipIdx == -1 ||
			   homephoneIdx == -1 ||  joinedunitidx== -1 || patrolidx== -1  || oaidx== -1 ||
			  parent1email1idx== -1 ||  parent2email1idx== -1 			)
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
			Scout s = getScout(f, tokens);
			s.set_bsaID(tokens.get(bsaIdIndex));
			s.set_dateOfBirth(stringToDate(tokens.get(dobIdx)));
			s.set_joinedUnit(stringToDate(tokens.get(joinedunitidx)));
			s.set_patrol(tokens.get(patrolidx));
			ContactInfo contact = new ContactInfo(tokens.get(address1Idx),tokens.get(cityIdx),tokens.get(stateIdx),tokens.get(zipIdx));
			contact.set_homephone(tokens.get(homephoneIdx));			
			s.set_contactInfo(contact);
			
			Parent parent1 = new Parent(tokens.get(address1Idx),tokens.get(cityIdx),tokens.get(stateIdx),tokens.get(zipIdx));
			parent1.set_email1( tokens.get(parent1email1idx));
			
			s.add_parentContactInfo(parent1);
						
			Parent parent2 = new Parent(tokens.get(address1Idx),tokens.get(cityIdx),tokens.get(stateIdx),tokens.get(zipIdx));
			parent2.set_email1( tokens.get(parent2email1idx));

			s.add_parentContactInfo(parent2);
			
			f.updateScout(s);
			tokens = super.readTokenizedLine();
		}
	}
}
