package com.frysning.springdnd.trait;

import com.frysning.springdnd.modifier_type.ModifierType;
import com.frysning.springdnd.stats.ReadableStats;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

public class ReadableTrait extends Trait {

    private int baseModifier = 8;

    public ReadableTrait(Trait trait, ReadableStats readableStats, int proficiencyBonus,
        String enemyName) {
        this(trait.getId(), trait.getName(), trait.getDescription());
        this.setDescription(MessageFormat.format(this.getDescription(), enemyName));
        this.updateValue(readableStats, proficiencyBonus);
    }

    public ReadableTrait(Long id, String name, String value) {
        super(id, name, value);
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
                replaceDCSaving(modifierType,
                    stats.getStrength().getModifier() + proficiencyBonus);
                break;
            case DEX:
                replaceDCSaving(
                    modifierType,
                    stats.getDexterity().getModifier() + proficiencyBonus);
                break;
            case CON:
                replaceDCSaving(
                    modifierType,
                    stats.getConstitution().getModifier() + proficiencyBonus);
                break;
            case INT:
                replaceDCSaving(
                    modifierType,
                    stats.getIntelligence().getModifier() + proficiencyBonus);
                break;
            case WIS:
                replaceDCSaving(
                    modifierType,
                    stats.getWisdom().getModifier() + proficiencyBonus);
                break;
            case CHA:
                replaceDCSaving(
                    modifierType,
                    stats.getCharisma().getModifier() + proficiencyBonus);
                break;
            case NOT_SUPPORTED:
                break;
        }
    }

    private void replaceDCSaving(ModifierType modifierType, int dcValue) {
        this.setDescription(this.getDescription().replace("[" + modifierType.modifier + "]",
            MessageFormat.format("DC {0} {1}", dcValue + baseModifier, modifierType.modifier)));
    }

}
