package com.frysning.springdnd.combiner;import com.fasterxml.jackson.annotation.JsonIgnore;import com.frysning.springdnd.actions.CalculatedAction;import com.frysning.springdnd.enemies.CalculatedSavingThrow;import com.frysning.springdnd.enemies.Enemy;import com.frysning.springdnd.language.Language;import com.frysning.springdnd.modifiertype.ModifierType;import com.frysning.springdnd.race.Race;import com.frysning.springdnd.size.Size;import com.frysning.springdnd.stats.ReadableStats;import com.frysning.springdnd.stats.Stat;import com.frysning.springdnd.traits.ReadableTrait;import com.frysning.springdnd.traits.Trait;import java.util.Collections;import java.util.List;import java.util.stream.Collectors;import org.springframework.util.StringUtils;public class Combined {    Enemy enemy;    Race race;    public Combined(Enemy enemy, Race race) {        this.enemy = enemy;        this.race = race;    }    @JsonIgnore    public Enemy enemy() {        return enemy;    }    @JsonIgnore    public Race race() {        return race;    }    public String getName() {        return enemy.getName();    }    public String getRaceName() {        return race.getName();    }    public String getRaceType() {        return enemy.getRaceType().getName();    }    public ReadableStats getBaseStats() {        return enemy.getBaseStats();    }    public Stat getRacialStats() {        return race.getStat();    }    public ReadableStats getCombinedStats() {        return new ReadableStats(enemy.getStat(), race.getStat());    }    public List<CalculatedAction> getCalculatedActions() {        return enemy.getActions().stream()            .map(                action -> new CalculatedAction(action, getCombinedStats(),                    enemy.getProficiencyBonus(), this.getName()))            .collect(                Collectors.toList());    }    public List<CalculatedAction> getCalculatedReactions() {        return enemy.getReactions().stream()            .map(                action -> new CalculatedAction(action, getCombinedStats(),                    enemy.getProficiencyBonus(), this.getName()))            .collect(                Collectors.toList());    }    public String getArmorClass() {        return enemy.getAc();    }    public String getSpeed() {        List<String> enemySpeed = enemy.getSpeed().stream()            .map(speed -> StringUtils.capitalize(speed.getSpeedType().toLowerCase()) + " "                + speed.getRange()                + "ft.").collect(                Collectors.toList());        return String.join(",", enemySpeed);    }    public int getProficiencyBonus() {        return enemy.getProficiencyBonus();    }    public Size getSize() {        if (enemy.getSize().id != race.getSize().getId()) {            return race.getSize();        } else {            return enemy.getSize();        }    }    public String getHitDice() {        return enemy.getHitDice();    }    public String getAlignment() {        return enemy.getAlignment();    }    public int getDefaultHitPoints() {        return enemy.getDefaultHitPoints();    }    public List<CalculatedSavingThrow> getCalculatedSavingThrows() {        return enemy.getSavingThrows().stream().map(            modifierId -> new CalculatedSavingThrow(ModifierType.getById(modifierId),                getCombinedStats(),                enemy().getProficiencyBonus())).collect(            Collectors.toList());    }    public List<Language> getLanguages() {        List<Language> enemyLanguages = enemy.getLanguages();        List<Language> raceLanguages = race.getLanguages();        enemyLanguages.removeAll(raceLanguages);        enemyLanguages.addAll(raceLanguages);        return enemyLanguages;    }    public List<ReadableTrait> getReadableTraits() {        List<Trait> enemyTraits = enemy.getTraits();        List<Trait> raceTraits = Collections.emptyList();        enemyTraits.removeAll(raceTraits);        enemyTraits.addAll(raceTraits);        return enemyTraits.stream()            .map(trait -> new ReadableTrait(trait, getBaseStats(), getProficiencyBonus(),                this.getName()))            .collect(                Collectors.toList());    }}