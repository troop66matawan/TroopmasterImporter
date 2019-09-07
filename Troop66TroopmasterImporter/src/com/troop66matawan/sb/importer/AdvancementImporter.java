package com.troop66matawan.sb.importer;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.troop66matawan.tm.importer.HeaderFormatException;
import com.troop66matawan.tm.model.MeritBadge;
import com.troop66matawan.tm.model.RankAdvancement;
import com.troop66matawan.tm.model.Scout;
import com.troop66matawan.tm.model.ScoutFactory;

public class AdvancementImporter extends ScoutbookImporter {
	private static final String ADVANCEMENT_TYPE_HDR = "Advancement Type";
	protected Integer advancedmentTypeIdx;
	private static final String ADVANCEMENT_HDR = "Advancement";
	protected Integer advancementIdx;
	private static final String DATE_COMPLETE_HDR = "Date Completed";
	protected Integer dateIdx;

	private static final String AD_TYPE_RANK = "Rank";
	private static final String AD_TYPE_MERIT_BADGE = "Merit Badge";
	
	private static final String AD_RANK_SCOUT = "Scout";
	private static final String AD_RANK_TENDERFOOT = "Tenderfoot";
	private static final String AD_RANK_2NDCLASS = "Second Class";
	private static final String AD_RANK_1STCLASS = "First Class";
	private static final String AD_RANK_STAR = "Star Scout";
	private static final String AD_RANK_LIFE = "Life Scout";
	private static final String AD_RANK_EAGLE = "Eagle Scout";
	private static ScoutFactory f = ScoutFactory.getInstance();

	public AdvancementImporter(String filename) throws IOException, HeaderFormatException {
		super(filename);
		
		this.advancedmentTypeIdx = this._headerTokens.indexOf(AdvancementImporter.ADVANCEMENT_TYPE_HDR);
		this.advancementIdx = this._headerTokens.indexOf(AdvancementImporter.ADVANCEMENT_HDR);
		this.dateIdx = this._headerTokens.indexOf(AdvancementImporter.DATE_COMPLETE_HDR);
		
		if (advancedmentTypeIdx == -1 || advancementIdx == -1 || dateIdx == -1) {
			throw new HeaderFormatException();
		}
	}
	protected Scout importRank(Scout scout, String advancement, Date date ) {
		RankAdvancement rank = scout.get_rankAdvancement();
		
		if (rank == null) {
			rank = new RankAdvancement();
		}
		switch (advancement) {
			case AD_RANK_SCOUT:
				rank.set_scout(date);
				break;
			case AD_RANK_TENDERFOOT:
				rank.set_tenderfoot(date);
				break;
			case AD_RANK_2NDCLASS:
				rank.set_2ndClass(date);
				break;
			case AD_RANK_1STCLASS:
				rank.set_1stClass(date);
				break;
			case AD_RANK_STAR:
				rank.set_star(date);
				break;
			case AD_RANK_LIFE:
				rank.set_life(date);
				break;
			case AD_RANK_EAGLE:
				rank.set_eagle(date);
				break;
		}
		scout.set_rankAdvancement(rank);
		return scout;
	}
	public void doImport() throws IOException {
		List<String> tokens = super.readTokenizedLine(); 
		while (tokens != null) {
			Scout s = getScout(f, tokens);
			String advancementType = tokens.get(this.advancedmentTypeIdx);
			String advancement = tokens.get(this.advancementIdx);
			Date dateComplete = stringToDate(tokens.get(this.dateIdx));
			
			switch (advancementType) {
				case AD_TYPE_RANK:
					s = importRank(s, advancement, dateComplete);
					f.updateScout(s);
					break;
				case AD_TYPE_MERIT_BADGE:
					MeritBadge mb = new MeritBadge();
					mb.set_name(advancement);
					mb.set_earned(dateComplete);
					s.addMeritBadge(mb);
					f.updateScout(s);
					break;
			}
		}
	}
}
