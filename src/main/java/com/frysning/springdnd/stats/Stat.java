package com.frysning.springdnd.stats;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stats")
public class Stat {

    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

    @Column(nullable = false)
    private int strength = 10;

    @Column(nullable = false)
    private int dexterity = 10;

    @Column(nullable = false)
    private int constitution = 10;

    @Column(nullable = false)
    private int intelligence = 10;

    @Column(nullable = false)
    private int wisdom = 10;

    @Column(nullable = false)
    private int charisma = 10;

    public Stat(int strength, int dexterity, int constitution, int intelligence, int wisdom,
        int charisma) {
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.charisma = charisma;
    }

    public Stat() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int str) {
        this.strength = str;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dex) {
        this.dexterity = dex;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int con) {
        this.constitution = con;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wis) {
        this.wisdom = wis;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int cha) {
        this.charisma = cha;
    }


}
