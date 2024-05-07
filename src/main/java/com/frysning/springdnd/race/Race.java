package com.frysning.springdnd.race;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.frysning.springdnd.combiner.BaseObject;
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
public class Race extends BaseObject {

    public Race() {
    }

    public Race(String name, Stat stat) {
        this.name = name;
        this.stat = stat;
    }

}
