package com.troop66matawan.tm.model;

import java.util.List;

public class EagleRequiredMeritBadges {
	public static final MeritBadge CAMPING_MB = new MeritBadge("Camping");
	public static final MeritBadge CITINCOMMUNITY = new MeritBadge("Cit In Community");
	public static final MeritBadge NATION_MB = new MeritBadge("Cit In Nation");
	public static final MeritBadge WORLD_MB = new MeritBadge("Cit In World");
	public static final MeritBadge COMMUNCICATION_MB = new MeritBadge("Communication");
	public static final MeritBadge COOKING_MB = new MeritBadge("Cooking"); 
	public static final MeritBadge FAMILY_MB = new MeritBadge("Family Life");
	public static final MeritBadge FIRSTAID_MB = new MeritBadge("First Aid");
	public static final MeritBadge FITNESS_MB = new MeritBadge("Personal Fitness");
	public static final MeritBadge PMGMT_MB = new MeritBadge("Personal Management");
	public static final MeritBadge SWIM_MB = new MeritBadge("Swimming");
	public static final MeritBadge CYCLING_MB = new MeritBadge("Cycling");
	public static final MeritBadge HIKING_MB = new MeritBadge("Hiking");
	public static final MeritBadge LIFESAVING_MB = new MeritBadge("Lifesaving");
	public static final MeritBadge EPREP_MB = new MeritBadge("Emergency Prep");
	public static final MeritBadge ESCI_MB = new MeritBadge("Environmental Sci");
	public static final MeritBadge Sustainability_MB = new MeritBadge("Sustainability");

	boolean hasCamping(List<MeritBadge> mbs){return mbs.contains(CAMPING_MB);}
	boolean hasCommunity(List<MeritBadge> mbs){return mbs.contains(CITINCOMMUNITY);}
	boolean hasNation(List<MeritBadge> mbs){return mbs.contains(NATION_MB);}
	boolean hasWorld(List<MeritBadge> mbs){return mbs.contains(WORLD_MB);}
	boolean hasCommunication(List<MeritBadge> mbs){return mbs.contains(COMMUNCICATION_MB);}
	boolean hasFamilyLife(List<MeritBadge> mbs){return mbs.contains(FAMILY_MB);}
	boolean hasFirstAid(List<MeritBadge> mbs){return mbs.contains(FIRSTAID_MB);}
	boolean hasCooking(List<MeritBadge> mbs){return mbs.contains(COOKING_MB);}
	boolean hasFitness(List<MeritBadge> mbs){return mbs.contains(FITNESS_MB);}
	boolean hasMgmt(List<MeritBadge> mbs){return mbs.contains(PMGMT_MB);}
	boolean hasOneOfLifesavingOrEPrep(List<MeritBadge> mbs) {return (mbs.contains(LIFESAVING_MB) || mbs.contains(EPREP_MB)) ;}
	boolean hasOneOfHikingCyclingSwimming(List<MeritBadge> mbs) {return (mbs.contains(SWIM_MB) || mbs.contains(CYCLING_MB)|| mbs.contains(HIKING_MB)) ;}
	boolean hasOneofEsciOrSustain(List<MeritBadge> mbs) {return (mbs.contains(ESCI_MB) || mbs.contains(Sustainability_MB)) ;}

	public String listRemaining(List<MeritBadge> mbs)
	{
		String list="";
		if (!hasCamping(mbs)){
			list +=CAMPING_MB.get_name();
			list += "; ";
		} 
		if (!hasCommunity(mbs)){
			list +=CITINCOMMUNITY.get_name();
			list += "; ";
		}
		if (! hasNation(mbs) ) {
			list +=NATION_MB.get_name();
			list += "; ";
		} 
		if (! hasWorld(mbs) ) { 
			list +=WORLD_MB.get_name();
			list += "; ";
		} 
		if (! hasCommunication(mbs) ){
			list +=COMMUNCICATION_MB.get_name();
			list += "; ";
		} 
		if (! hasFamilyLife(mbs) ){
			list +=FAMILY_MB.get_name();
			list += "; ";
		}
		if (! hasFirstAid(mbs) ){
			list +=FIRSTAID_MB.get_name();
			list += "; ";
		} 
		if (! hasCooking(mbs) ){
			list +=COOKING_MB.get_name();
			list += "; ";
		} 
		if (! hasFitness(mbs) ){
			list +=FITNESS_MB.get_name();
			list += "; ";
		} 
		if (! hasMgmt(mbs) ){
			list +=PMGMT_MB.get_name();
			list += "; ";
		} 
		if (! hasOneOfLifesavingOrEPrep(mbs) ){
			list +=LIFESAVING_MB.get_name() + " OR "  + EPREP_MB.get_name();
			list += "; ";
		} 
		if (! hasOneOfHikingCyclingSwimming(mbs)){ 
			list +=SWIM_MB.get_name() +" OR " + CYCLING_MB.get_name() + " OR " + HIKING_MB.get_name();
			list += "; ";
		}
		if (! hasOneofEsciOrSustain(mbs)) { 
			list +=ESCI_MB.get_name() + " OR " + Sustainability_MB.get_name();
			list += "; ";
		}
		return list;
	}
	

}
