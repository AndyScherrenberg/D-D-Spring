package com.frysning.springdnd.race;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.frysning.springdnd.language.Language;
import com.frysning.springdnd.size.Size;
import com.frysning.springdnd.speed.Speed;
import com.frysning.springdnd.speed_type.SpeedType;
import com.frysning.springdnd.spell.Spell;
import com.frysning.springdnd.stats.Stat;
import com.frysning.springdnd.trait.Trait;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private List<Spell> spells = new ArrayList<>();

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
}
