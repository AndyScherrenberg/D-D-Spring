package com.frysning.springdnd.race;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.frysning.springdnd.conditions.Condition;
import com.frysning.springdnd.damage_type.DamageType;
import com.frysning.springdnd.language.Language;
import com.frysning.springdnd.size.Size;
import com.frysning.springdnd.speed.Speed;
import com.frysning.springdnd.spell.Spell;
import com.frysning.springdnd.stats.Stat;
import com.frysning.springdnd.trait.Trait;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.*;

@Entity
@Table(name = "race")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class Race {

    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

    @Column(nullable = false, unique = true)
    private String name;
    @ManyToOne
    @JoinColumn(name = "stat_id", referencedColumnName = "id", nullable = false)
    private Stat stat;
    private int size;

    @ManyToMany
    private List<Language> languages = new ArrayList<>();

    @ManyToMany
    private List<Trait> traits = new ArrayList<>();
    public Race() {
    }

    @ManyToMany
    private List<Speed> speed = new ArrayList<>();

    @ManyToMany
    private List<Spell> spells = new ArrayList<>();


    @ManyToMany
    private List<DamageType> damageWeakness;
    @ManyToMany
    private List<DamageType> damageImmunity;
    @ManyToMany
    private List<DamageType> damageResistance;

    @ManyToMany
    private List<Condition> conditionWeakness;
    @ManyToMany
    private List<Condition> conditionImmunity;
    @ManyToMany
    private List<Condition> conditionResistance;

    public Race(String name, Stat stat) {
        this.name = name;
        this.stat = stat;
    }

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

    public List<Trait> getTraits() { return traits;}

    public void setTraits(List<Trait> traits) { this.traits = traits;}

    public List<Spell> getSpells() { return spells;}

    public void setSpells(List<Spell> spells) { this.spells = spells;}

    @JsonIgnore
    public List<Speed> getValidSpeed() {
        return speed.stream().filter(spd -> spd.getId() != null).collect(Collectors.toList());
    }


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
}
