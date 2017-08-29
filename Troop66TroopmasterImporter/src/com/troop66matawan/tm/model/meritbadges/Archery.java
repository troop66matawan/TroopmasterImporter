package com.troop66matawan.tm.model.meritbadges;
import java.util.ArrayList;

import com.troop66matawan.tm.model.GroupRequirement;
import com.troop66matawan.tm.model.PartialMeritBadge;
import com.troop66matawan.tm.model.Requirement;
import com.troop66matawan.tm.model.SingleRequirement;
class  Archery  extends PartialMeritBadge {
	
	private void initRequirements() {
	
		GroupRequirement req1 = new GroupRequirement("1","Do the following");
		req1.set_doN(GroupRequirement.ALL);
		GroupRequirement r1a = new GroupRequirement("1a","State and explain the Range Safety Rules.");
		r1a.set_doN(GroupRequirement.ALL);
		r1a.add_childRequirement(new SingleRequirement("1a1","Three safety rules when on the shooting line."));
		r1a.add_childRequirement(new SingleRequirement("1a2","Three safety rules when retrieving arrows."));
		r1a.add_childRequirement(new SingleRequirement("1a3","The four whistle commands used on a range and their related verbal commands."));
		req1.add_childRequirement(r1a);
		req1.add_childRequirement(new SingleRequirement("1b","State and explain the general safety rules for archery. Demonstrate how to safely carry arrows in your hands."));
		req1.add_childRequirement(new SingleRequirement("1c","Tell about your local and state laws for owning and using archery tackle."));
		super._requirements.add(req1);
	
		GroupRequirement req2 = new GroupRequirement("2","Do the following");
		req2.set_doN(GroupRequirement.ALL);
		req2.add_childRequirement(new SingleRequirement("2a","Name and point out the parts of an arrow."));
		req2.add_childRequirement(new SingleRequirement("2b","Describe three or more different types of arrows."));
		req2.add_childRequirement(new SingleRequirement("2c","Name the four principle materials for making arrow shafts."));
		GroupRequirement r2d = new GroupRequirement("2d","Do the following");
		r2d.set_doN(1);
		r2d.add_childRequirement(new SingleRequirement("2d1","Make a complete arrow from a bare shaft using appropriate equipment available to you."));
		r2d.add_childRequirement(new SingleRequirement("2d1","To demonstrate arrow repair, inspect the shafts and prepare and replace at least three vanes, one point, and one nock. You may use as many arrows as necessary to accomplish this. The repairs can be done on wood, fiberglass, or aluminum arrows."));
		req2.add_childRequirement(new SingleRequirement("2e","Explain how to properly care for and store arrows."));
		super._requirements.add(req2);
		
		GroupRequirement req3 = new GroupRequirement("3","Do the following");
		req3.set_doN(GroupRequirement.ALL);
		req3.add_childRequirement(new SingleRequirement("3a","Explain the proper use, care, and storage of, as well as the reasons for using tabs, arm guards, shooting gloves, and quivers."));
		req3.add_childRequirement(new SingleRequirement("3b","Explain the following terms: cast, draw weight, string height (fistmele), aiming, spine, mechanical release, freestyle, and barebow."));
		req3.add_childRequirement(new SingleRequirement("3c","Make a bowstring using appropriate materials."));
		super._requirements.add(req3);
	
		GroupRequirement req4 = new GroupRequirement("4","Explain the following");
		req3.set_doN(GroupRequirement.ALL);
		req3.add_childRequirement(new SingleRequirement("4a","The importance of obedience to a range officer or other person in charge of a range."));
		req3.add_childRequirement(new SingleRequirement("4b","The difference between an end and a round."));
		req3.add_childRequirement(new SingleRequirement("4c","The differences among field, target, and 3-D archery."));
		req3.add_childRequirement(new SingleRequirement("4d","How the five-color Federation Internationale de Tir a l'Arc (FITA) target is scored."));
		req3.add_childRequirement(new SingleRequirement("4e","How the National Field Archery Association (NFAA) black-and-white field targets and blue indoor targets are scored."));
		req3.add_childRequirement(new SingleRequirement("4f","The elimination system used in Olympic archery competition."));
		super._requirements.add(req4);

	
5. Do ONE of the following options:
	<b>Option A - Using a Recurve Bow or Longbow</b>
	a. Name and Point to the parts of the recurve bow or longbow you are shooting.
	b. Explain how to properly care for and store recurve bows and longbows.
	c. Show the ten steps of good shooting for the bow you are shooting.
	d. Demonstrate the proper way to string a recurve bow or longbow.
	e. Using a bow square, locate and mark with dental floss, crimp-on, or other method, the nocking point on the bowstring of the bow you are using.
	f. Do ONE of the following:
	1. Using a recurve bow or longbow and arrows with a finger release, shoot a single round of one of the following BSA, USA Archery, or NFAA rounds:
	a. An NFAA field round of 14 targets and make a score of 60 points.
	b. A BSA Scout field round of 14 targets and make a score of 80 points.
	c. A Junior 900 round and make a score of 180 points.
	d. An FITA/USA Archery indoor round I and make a score of 80 points.
	e. An NFAA indoor round and make a score of 50 points.
	(The indoor rounds may be shot outdoors if this is more convenient.)
	
	OR
	2. Shooting 30 arrows in five-arrow ends at an 80-centimeter (32-inch) five-color target at 10 yards and using the 10 scoring regions, make a score of 150.
	OR
	3. As a member of the NAA’s Junior Olympic Archery Development program (JOAD), achieve the level of green, purple, and gray stars as part of a JOAD Club indoor or outdoor season with your chosen style of archery equipment
	OR
	4. As a member of the NFAA's Junior Division, earn a Cub or Youth 100-score Progression patch.
	
	<b>Option B - Using a Compound Bow</b>
	a. Name and point to the parts of the compound bow you are shooting.
	b. Explain how to properly care for and store compound bows.
	c. Show the ten steps of good shooting for the compound bow you are shooting.
	d. Explain why it is necessary to have the string or cable on a compound bow replaced at an archery shop.
	e. Locate and mark with dental floss, crimp-on, or other method, the nocking point on the bowstring of the bow you are using.
	f. Do ONE of the following:
	1. Using a compound bow and arrows with a finger release, shoot a single round of ONE of the following BSA, NAA,or NFAA rounds:
	a. An NFAA field round of 4 targets and make a score of 70 points.
	b. A BSA Scout field round of 14 targets and make a score of 90 points.
	c. A Junior 900 round and make a score of 200 points.
	d. An FITA/USA Archery indoor round I and make a score of 90 points.
	e. An NFAA indoor round and make a score of 60 points.
	(The indoor rounds may be shot outdoors if this is more convenient.)
	
	OR
	2. Shooting at an 80-centimeter (32-inch) five-color target at 15 yards and using the 10 scoring regions, make a minimum score of 160. Accomplish this in the following manner:
	Shoot 15 arrows in five-arrow ends, at a distance of 10 yards
	AND
	Shoot 15 arrows in five-arrow ends, at a distance of 15 yards.
	
	OR
	3. As a member of the NAA’s Junior Olympic Archery Development program (JOAD), achieve the level of green, purple, and gray stars as part of a JOAD Club indoor or outdoor season with your chosen style of archery equipment
	OR
	4. As a member of the NFAA's Junior Division, earn a Cub or Youth 100-score Progression patch.
	}

	@Override
	public ArrayList<Requirement> get_requirements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void set_requirements(ArrayList<Requirement> _requirements) {
		// TODO Auto-generated method stub
		
	}	
}
