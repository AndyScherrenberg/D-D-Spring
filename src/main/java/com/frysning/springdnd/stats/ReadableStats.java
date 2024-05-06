package com.frysning.springdnd.stats;public class ReadableStats {    private final Stat stat;    public ReadableStats(Stat stat) {        this.stat = stat;    }    public ReadableStats(Stat stat, Stat racialStat) {        this.stat = new Stat();        this.stat.setStrength(stat.getStrength() + racialStat.getStrength());        this.stat.setDexterity(stat.getDexterity() + racialStat.getDexterity());        this.stat.setConstitution(stat.getConstitution() + racialStat.getConstitution());        this.stat.setIntelligence(stat.getIntelligence() + racialStat.getIntelligence());        this.stat.setWisdom(stat.getWisdom() + racialStat.getWisdom());        this.stat.setCharisma(stat.getCharisma() + racialStat.getCharisma());    }    public CalculatedStat getStrength() {        return new CalculatedStat(stat.getStrength());    }    public CalculatedStat getDexterity() {        return new CalculatedStat(stat.getDexterity());    }    public CalculatedStat getConstitution() {        return new CalculatedStat(stat.getConstitution());    }    public CalculatedStat getIntelligence() {        return new CalculatedStat(stat.getIntelligence());    }    public CalculatedStat getWisdom() {        return new CalculatedStat(stat.getWisdom());    }    public CalculatedStat getCharisma() {        return new CalculatedStat(stat.getCharisma());    }}