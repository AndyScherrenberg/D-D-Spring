package com.frysning.springdnd.enemy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.frysning.springdnd.action.CalculatedAction;
import com.frysning.springdnd.challenge_rating.ChallengeRating;
import com.frysning.springdnd.combiner.BaseObject;
import com.frysning.springdnd.modifier_type.ModifierType;
import com.frysning.springdnd.race_type.RaceType;
import com.frysning.springdnd.stats.ReadableStats;
import com.frysning.springdnd.stats.Stat;
import com.frysning.springdnd.trait.ReadableTrait;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "enemy")

public class Enemy extends BaseObject {

	@ManyToOne
	@JoinColumn(name = "race_type_id", referencedColumnName = "id", nullable = false)
	private RaceType raceType;
	private String ac;
	private String hitDice;
	private int defaultHitPoints;
	private int proficiencyBonus;
	@ManyToOne
	@JoinColumn(name = "challenge_rating_id", referencedColumnName = "id")
	private ChallengeRating challengeRating;

	private String alignment;
	@ElementCollection
	private List<Integer> savingThrows = new ArrayList<>();


	public Enemy(String name, Stat stat, RaceType raceType) {
		this.name = name;
		this.stat = stat;
		this.raceType = raceType;
	}

	public Enemy() {

	}

	public ChallengeRating getChallengeRating() {
		return challengeRating;
	}

	public void setChallengeRating(ChallengeRating challengeRating) {
		this.challengeRating = challengeRating;
	}

	public int getDefaultHitPoints() {
		return defaultHitPoints;
	}

	public void setDefaultHitPoints(int defaultHitPoints) {
		this.defaultHitPoints = defaultHitPoints;
	}

	public String getAc() {
		return ac;
	}

	public void setAc(String ac) {
		this.ac = ac;
	}

	public String getHitDice() {
		return hitDice;
	}

	public void setHitDice(String hitPoints) {
		this.hitDice = hitPoints;
	}

	public int getProficiencyBonus() {
		return proficiencyBonus;
	}

	public void setProficiencyBonus(int proficiencyBonus) {
		this.proficiencyBonus = proficiencyBonus;
	}

	public RaceType getRaceType() {
		return raceType;
	}

	public void setRaceType(RaceType raceType) {
		this.raceType = raceType;
	}

	public ReadableStats getBaseStats() {
		return new ReadableStats(stat);
	}

	public String getAlignment() {
		return alignment;
	}

	public void setAlignment(String alignment) {
		this.alignment = alignment;
	}

	public List<CalculatedSavingThrow> getCalculatedSavingThrows() {
		return savingThrows.stream().map(
				modifierId -> new CalculatedSavingThrow(ModifierType.getById(modifierId),
						getBaseStats(),
						proficiencyBonus)).collect(
				Collectors.toList());
	}


	@JsonIgnore
	public List<Integer> getSavingThrows() {
		return savingThrows;
	}

	public void setSavingThrows(
			List<Integer> modifierType) {
		this.savingThrows = modifierType;
	}

	public List<ReadableTrait> getReadableTraits() {
		return traits.stream()
				.map(trait -> new ReadableTrait(trait, getBaseStats(), getProficiencyBonus(),
						this.name))
				.collect(
						Collectors.toList());
	}

	public List<CalculatedAction> getCalculatedReactions() {
		return reactions.stream()
				.map(action -> new CalculatedAction(action, getBaseStats(), getProficiencyBonus(),
						this.name))
				.collect(
						Collectors.toList());
	}

	public List<CalculatedAction> getCalculatedActions() {
		return actions.stream()
				.map(action -> new CalculatedAction(action, getBaseStats(), getProficiencyBonus(),
						this.name))
				.collect(
						Collectors.toList());
	}
}
