package com.frysning.springdnd.enemy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.frysning.springdnd.action.Action;
import com.frysning.springdnd.action.CalculatedAction;
import com.frysning.springdnd.challenge_rating.ChallengeRating;
import com.frysning.springdnd.damage_type.DamageType;
import com.frysning.springdnd.language.Language;
import com.frysning.springdnd.modifier_type.ModifierType;
import com.frysning.springdnd.race_type.RaceType;
import com.frysning.springdnd.size.Size;
import com.frysning.springdnd.speed.Speed;
import com.frysning.springdnd.spell.Spell;
import com.frysning.springdnd.stats.ReadableStats;
import com.frysning.springdnd.stats.Stat;
import com.frysning.springdnd.trait.ReadableTrait;
import com.frysning.springdnd.trait.Trait;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "enemy")
public class Enemy {

    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @ManyToOne
    @JoinColumn(name = "stat_id", referencedColumnName = "id", nullable = false)
    private Stat stat;
    @ManyToOne
    @JoinColumn(name = "race_type_id", referencedColumnName = "id", nullable = false)
    private RaceType raceType;
    private String ac;
    private String hitDice;
    private int defaultHitPoints;
    @ManyToMany(targetEntity = Speed.class)
    private List<Speed> speed = new ArrayList<>();
    private int proficiencyBonus;
    @ManyToOne
    @JoinColumn(name = "challenge_rating_id", referencedColumnName = "id")
    private ChallengeRating challengeRating;

    @ManyToMany
    private List<DamageType> weakness;
    @ManyToMany
    private List<DamageType> immunities;
    @ManyToMany
    private List<DamageType> resistance;
    @ManyToMany
    private List<Action> actions = new ArrayList<>();
    @ManyToMany
    private List<Action> reactions = new ArrayList<>();

    @ManyToMany
    private List<Trait> traits = new ArrayList<>();
    @Column(nullable = true)
    private int size;
    private String alignment;
    @ManyToMany
    private List<Language> languages = new ArrayList<>();
    @ElementCollection
    private List<Integer> savingThrows = new ArrayList<>();

    @ManyToMany
    private List<Spell> spells = new ArrayList<>();

    public Enemy(String name, Stat stat, RaceType raceType) {
        this.name = name;
        this.stat = stat;
        this.raceType = raceType;
    }

    public Enemy() {

    }

    @JsonIgnore
    public List<Language> getValidLanguages() {
        return languages.stream().filter(language -> language.getId() != null)
            .collect(Collectors.toList());
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public List<Action> getReactions() {
        return reactions;
    }

    @JsonIgnore
    public void setReactions(List<Action> reactions) {
        this.reactions = reactions;
    }

    public List<CalculatedAction> getCalculatedReactions() {
        return reactions.stream()
            .map(action -> new CalculatedAction(action, getBaseStats(), getProficiencyBonus(),
                this.name))
            .collect(
                Collectors.toList());
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

    @JsonIgnore
    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public List<CalculatedAction> getCalculatedActions() {
        return actions.stream()
            .map(action -> new CalculatedAction(action, getBaseStats(), getProficiencyBonus(),
                this.name))
            .collect(
                Collectors.toList());
    }

    public ChallengeRating getChallengeRating() {
        return challengeRating;
    }

    public void setChallengeRating(ChallengeRating challengeRating) {
        this.challengeRating = challengeRating;
    }

    public List<Speed> getSpeed() {
        return speed;
    }

    public void setSpeed(List<Speed> speed) {
        this.speed = speed;
    }

    @JsonIgnore
    public List<Speed> getValidSpeed() {
        return speed.stream().filter(speed1 -> speed1.getId() != null).collect(Collectors.toList());
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

    @JsonIgnore
    public Stat getStat() {
        return stat;
    }

    public void setStat(Stat stat) {
        this.stat = stat;
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

    public Size getSize() {
        return Size.getById(size);
    }

    public void setSize(int size) {
        this.size = size;
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


    @JsonIgnore
    public List<Trait> getValidTraits() {
        return traits.stream().filter(trait -> trait.getId() != null)
            .collect(Collectors.toList());
    }

    @JsonIgnore
    public List<Trait> getTraits() {
        return traits;
    }

    public void setTraits(List<Trait> traits) {
        this.traits = traits;
    }

    public List<Spell> getSpells() { return spells;}

    public void setSpells(List<Spell> spells) { this.spells = spells;}

    public List<ReadableTrait> getReadableTraits() {
        return traits.stream()
            .map(trait -> new ReadableTrait(trait, getBaseStats(), getProficiencyBonus(),
                this.name))
            .collect(
                Collectors.toList());
    }


}
