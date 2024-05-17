package com.frysning.springdnd.spell;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.frysning.springdnd.components.Component;
import com.frysning.springdnd.conditions.Condition;
import com.frysning.springdnd.damage_type.DamageType;
import com.frysning.springdnd.effects.Effect;
import com.frysning.springdnd.magic_school.MagicSchool;
import com.frysning.springdnd.player_class.PlayerClass;
import com.frysning.springdnd.spell_level.SpellLevel;
import com.frysning.springdnd.spell_type.SpellType;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
public class Spell {

	@JsonProperty(index = 1)
	private @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	@JsonProperty(index = 2)
	private String name;

	@Column(length = 4000)
	private String description;

	@Column(length = 4000)
	private String higher_level_description;

	private String castingTime;

	private String duration;

	private String range;

	private int[] components;

	private String componentDescription;

	private String target;

	@ManyToOne
	@JoinColumn(name = "spell_type_id", referencedColumnName = "id", nullable = true)
	private SpellType spellType;

	@ManyToOne
	@JoinColumn(name = "spell_level_id", referencedColumnName = "id", nullable = false)
	private SpellLevel spellLevel;

	@ManyToOne
	@JoinColumn(name = "magic_school_id", referencedColumnName = "id", nullable = false)
	private MagicSchool magicSchool;


	@ManyToMany
	private List<PlayerClass> playerClasses;

	@ManyToMany
	private List<DamageType> damageTypes;

	@ManyToMany
	private List<Condition> conditions;

	@ManyToMany
	private List<Effect> effects;

	private String save;

	private String attack;

	private String area;
	//Area?

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SpellType getSpellType() {
		return spellType;
	}

	public void setSpellType(SpellType spellType) {
		this.spellType = spellType;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHigher_level_description() {
		return higher_level_description;
	}

	public void setHigher_level_description(String higher_level_description) {
		this.higher_level_description = higher_level_description;
	}

	public String getCastingTime() {
		return castingTime;
	}

	public void setCastingTime(String castingTime) {
		this.castingTime = castingTime;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	@JsonIgnore
	public int[] getInternalComponents() {
		return components;
	}

	public void setComponents(int[] components) {
		this.components = components;
	}

	@JsonIgnore
	public String getComponentDescription() {
		return componentDescription;
	}

	public void setComponentDescription(String componentDescription) {
		this.componentDescription = componentDescription;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public SpellLevel getSpellLevel() {
		return spellLevel;
	}

	public void setSpellLevel(SpellLevel spellLevel) {
		this.spellLevel = spellLevel;
	}

	public MagicSchool getMagicSchool() {
		return magicSchool;
	}

	public void setMagicSchool(MagicSchool magicSchool) {
		this.magicSchool = magicSchool;
	}

	public List<PlayerClass> getPlayerClasses() {
		return playerClasses;
	}

	public void setPlayerClasses(List<PlayerClass> playerClasses) {
		this.playerClasses = playerClasses;
	}

	public List<DamageType> getDamageTypes() {
		return damageTypes;
	}

	public void setDamageTypes(List<DamageType> damageTypes) {
		this.damageTypes = damageTypes;
	}

	public List<Condition> getConditions() {
		return conditions;
	}

	public void setConditions(List<Condition> conditions) {
		this.conditions = conditions;
	}

	public List<Effect> getEffects() {
		return effects;
	}

	public void setEffects(List<Effect> effects) {
		this.effects = effects;
	}

	public String getSave() {
		return save;
	}

	public void setSave(String save) {
		this.save = save;
	}

	public String getAttack() {
		return attack;
	}

	public void setAttack(String attack) {
		this.attack = attack;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getComponents() {
		var components = Arrays.stream(this.components).distinct().sorted().toArray();

		var componentString = new StringBuilder();
		for (int comp : components){
			componentString.append(Component.getById(comp).getDescription()).append(",");
		}
		if (!componentString.isEmpty()){
			componentString.deleteCharAt(componentString.length()-1);
		}

		if (componentDescription != null && !componentDescription.isEmpty()) {
			return String.format("%s (%s)", componentString, componentDescription).trim();
		}

		return componentString.toString();
	}
}
