package com.troop66matawan.tm.model;

import java.util.ArrayList;

public class SingleRequirement extends Requirement {

	public SingleRequirement(String reqNumber) {
		super(reqNumber);
	}

	public SingleRequirement(String reqNumber, String text) {
		super(reqNumber, text);
	}

	public ArrayList<Requirement> get_childRequirements() {
		return null;
	}
}
