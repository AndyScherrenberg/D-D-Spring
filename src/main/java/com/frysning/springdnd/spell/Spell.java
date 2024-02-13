package com.frysning.springdnd.spell;


import com.frysning.springdnd.spell_type.SpellType;

import javax.persistence.*;

@Entity
public class Spell  {

    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "spell_type_id", referencedColumnName = "id", nullable = false)
    private SpellType spellType;

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

    //MagicSchool

    //Level

    //Casting Time

    //Range

    //Target

    //Components

    //Duration

    //Classes

    //Description

    //HigherLevels

    //DamageType[]

    //Damage[]

    //Area?


}
