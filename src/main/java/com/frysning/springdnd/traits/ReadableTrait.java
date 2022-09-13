package com.frysning.springdnd.traits;

import com.frysning.springdnd.modifiertype.ModifierType;
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
        this(trait.getId(), trait.getName(), trait.getValue());
        this.setValue(MessageFormat.format(this.getValue(), enemyName));
        this.updateValue(readableStats, proficiencyBonus);
    }

    public ReadableTrait(Long id, String name, String value) {
        super(id, name, value);
    }

    private void updateValue(ReadableStats stats, int proficiencyBonus) {

        List<String> strings = Arrays.stream(
                StringUtils.substringsBetween(this.getValue(), "[", "]"))
            .distinct().collect(Collectors.toList());

        for (String string : strings
        ) {
            ModifierType modifierType = ModifierType.valueOf(string);
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


    }

    private void replaceDCSaving(ModifierType modifierType, int dcValue) {
        this.setValue(this.getValue().replace("[" + modifierType.modifier + "]",
            MessageFormat.format("DC {0} {1}", dcValue + baseModifier, modifierType.modifier)));
    }

}
