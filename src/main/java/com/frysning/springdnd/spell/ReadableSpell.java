package com.frysning.springdnd.spell;

import com.frysning.springdnd.modifier_type.ModifierType;
import com.frysning.springdnd.stats.ReadableStats;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.frysning.util.DataCollection.SPELL_TAG;

public class ReadableSpell extends Spell {
	private final int baseModifier = 8;
	private int spellSaveValue = 0;

	ReadableSpell(Spell spell, ReadableStats readableStats, int proficiencyBonus,
				  String entityName, ModifierType castingStat) {
		setSpellValues(spell);
		this.setDescription(MessageFormat.format(this.getDescription(), entityName));
		updateValue(readableStats, proficiencyBonus);
		setSpellSaveDC(castingStat, readableStats, proficiencyBonus);
		setSpellDamage(castingStat, readableStats);
	}

	private void setSpellValues(Spell spell) {
		this.setId(spell.getId());
		this.setAttack(spell.getAttack());
		this.setArea(spell.getArea());
		this.setCastingTime(spell.getCastingTime());
		this.setComponents(spell.getInternalComponents());
		this.setComponentDescription(spell.getComponentDescription());
		this.setConditions(spell.getConditions());
		this.setDamageTypes(spell.getDamageTypes());
		this.setDescription(spell.getDescription());
		this.setDuration(spell.getDuration());
		this.setEffects(spell.getEffects());
		this.setHigher_level_description(spell.getHigher_level_description());
		this.setMagicSchool(spell.getMagicSchool());
		this.setName(spell.getName());
		this.setPlayerClasses(spell.getPlayerClasses());
		this.setSpellRange(spell.getSpellRange());
		this.setSave(spell.getSave());
		this.setSpellLevel(spell.getSpellLevel());
		this.setSpellType(spell.getSpellType());
		this.setTarget(spell.getTarget());
	}

	private void updateValue(ReadableStats stats, int proficiencyBonus) {
		String[] traitsToUpdate = StringUtils.substringsBetween(this.getDescription(), "[", "]");

		if (traitsToUpdate != null) {
			List<String> savingThrows = Arrays.stream(traitsToUpdate)
					.distinct().collect(Collectors.toList());
			savingThrows.forEach(savingThrow ->
				determineWhichModifierIsNeeded(ModifierType.valueOrNotSupported(savingThrow), stats,
						proficiencyBonus)
			);
		}
	}

	private void determineWhichModifierIsNeeded(ModifierType modifierType, ReadableStats stats,
												int proficiencyBonus) {
		switch (modifierType) {
			case STR:
				readableDescription(modifierType,
						stats.getStrength().getModifier() + proficiencyBonus);
				break;
			case DEX:
				readableDescription(
						modifierType,
						stats.getDexterity().getModifier() + proficiencyBonus);
				break;
			case CON:
				readableDescription(
						modifierType,
						stats.getConstitution().getModifier() + proficiencyBonus);
				break;
			case INT:
				readableDescription(
						modifierType,
						stats.getIntelligence().getModifier() + proficiencyBonus);
				break;
			case WIS:
				readableDescription(
						modifierType,
						stats.getWisdom().getModifier() + proficiencyBonus);
				break;
			case CHA:
				readableDescription(
						modifierType,
						stats.getCharisma().getModifier() + proficiencyBonus);
				break;
			case NOT_SUPPORTED:
				break;
		}
	}

	private void readableDescription(ModifierType modifierType, int dcValue) {
		this.setDescription(this.getDescription().replace("[" + modifierType.modifier + "]",
				MessageFormat.format("DC {0} {1}", dcValue + baseModifier, modifierType.modifier)));
	}

	private void setSpellDamage(ModifierType modifierType,ReadableStats readableStats){
		var spellDamage = readableStats.getStatModifier(modifierType);
		var cDescription = this.getDescription().replace(SPELL_TAG, MessageFormat.format("{0}({1})", spellDamage, modifierType.modifier));
		this.setDescription(cDescription);
	}

	private void setSpellSaveDC(ModifierType modifierType, ReadableStats readableStats, int prof) {
		int modifier = readableStats.getStatModifier(modifierType);

		spellSaveValue = modifier + prof + baseModifier;
	}

	int getSpellSave() {
		return spellSaveValue;
	}

}
