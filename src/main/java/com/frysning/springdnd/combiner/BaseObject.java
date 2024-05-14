package com.frysning.springdnd.combiner;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.frysning.springdnd.action.Action;
import com.frysning.springdnd.conditions.Condition;
import com.frysning.springdnd.damage_type.DamageType;
import com.frysning.springdnd.language.Language;
import com.frysning.springdnd.size.Size;
import com.frysning.springdnd.speed.Speed;
import com.frysning.springdnd.spell.Spell;
import com.frysning.springdnd.stats.Stat;
import com.frysning.springdnd.trait.Trait;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@MappedSuperclass
public abstract class BaseObject implements Serializable {

	@JsonProperty(index=1)
	protected @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

	@Column(nullable = false, unique = true)
	@JsonProperty(index = 2)
	protected String name;

	@ManyToOne
	@JoinColumn(name = "stat_id", referencedColumnName = "id", nullable = false)
	protected Stat stat;
	private int size;

	@ManyToMany
	private List<Language> languages = new ArrayList<>();

	@ManyToMany
	protected List<Trait> traits = new ArrayList<>();

	@ManyToMany
	private List<Speed> speed = new ArrayList<>();

	@ManyToMany
	private List<Spell> spells = new ArrayList<>();

	@ManyToMany
	private List<DamageType> damageWeakness = new ArrayList<>();
	@ManyToMany
	private List<DamageType> damageImmunity = new ArrayList<>();
	@ManyToMany
	private List<DamageType> damageResistance = new ArrayList<>();

	@ManyToMany
	private List<Condition> conditionWeakness = new ArrayList<>();
	@ManyToMany
	private List<Condition> conditionImmunity = new ArrayList<>();
	@ManyToMany
	private List<Condition> conditionResistance = new ArrayList<>();

	@ManyToMany
	protected List<Action> actions = new ArrayList<>();
	@ManyToMany
	protected List<Action> reactions = new ArrayList<>();

	/**
	 GETTER SETTERS
	 **/

	public Size getSize() {
		return Size.getById(size);
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Stat getStat() {
		return stat;
	}

	public void setStat(Stat baseStat) {
		this.stat = baseStat;
	}

	public List<Speed> getSpeed() {
		return speed;
	}

	public void setSpeed(List<Speed> baseSpeed) {
		this.speed = baseSpeed;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Language> getLanguages() {
		return languages;
	}

	public void setLanguages(List<Language> languages) {
		this.languages = languages;
	}

	public List<Trait> getTraits() { return traits;}

	public void setTraits(List<Trait> traits) { this.traits = traits;}

	public List<Spell> getSpells() { return spells;}

	public void setSpells(List<Spell> spells) { this.spells = spells;}

	@JsonIgnore
	public List<DamageType> getDamageWeakness() {
		return damageWeakness;
	}

	public void setDamageWeakness(List<DamageType> damageWeakness) {
		this.damageWeakness = damageWeakness;
	}

	@JsonIgnore
	public List<DamageType> getDamageImmunity() {
		return damageImmunity;
	}

	public void setDamageImmunity(List<DamageType> damageImmunity) {
		this.damageImmunity = damageImmunity;
	}

	@JsonIgnore
	public List<DamageType> getDamageResistance() {
		return damageResistance;
	}

	public void setDamageResistance(List<DamageType> damageResistance) {
		this.damageResistance = damageResistance;
	}

	@JsonIgnore
	public List<Condition> getConditionWeakness() {
		return conditionWeakness;
	}

	public void setConditionWeakness(List<Condition> conditionWeakness) {
		this.conditionWeakness = conditionWeakness;
	}

	@JsonIgnore
	public List<Condition> getConditionImmunity() {
		return conditionImmunity;
	}

	public void setConditionImmunity(List<Condition> conditionImmunity) {
		this.conditionImmunity = conditionImmunity;
	}

	@JsonIgnore
	public List<Condition> getConditionResistance() {
		return conditionResistance;
	}

	public void setConditionResistance(List<Condition> conditionResistance) {
		this.conditionResistance = conditionResistance;
	}

	public List<Action> getReactions() {
		return reactions;
	}

	@JsonIgnore
	public void setReactions(List<Action> reactions) {
		this.reactions = reactions;
	}

	@JsonIgnore
	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}

	/**
	 * Cleaned Data
	 */

	@JsonIgnore
	public List<Language> getValidLanguages() {
		return languages.stream().filter(language -> language.getId() != null)
				.collect(Collectors.toList());
	}

	@JsonIgnore
	public List<Trait> getValidTraits() {
		return traits.stream().filter(trait -> trait.getId() != null)
				.collect(Collectors.toList());
	}

	@JsonIgnore
	public List<Speed> getValidSpeed() {
		return speed.stream().filter(speed1 -> speed1.getId() != null).collect(Collectors.toList());
	}

	@JsonIgnore
	public List<Action> getValidActions() {
		return actions.stream().filter(action -> action.getId() != null)
				.collect(Collectors.toList());
	}

	@JsonIgnore
	public List<Action> getValidReactions() {
		return reactions.stream().filter(reaction -> reaction.getId() != null)
				.collect(Collectors.toList());
	}
}
