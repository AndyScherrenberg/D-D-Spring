package com.frysning.springdnd.actions;import com.frysning.springdnd.modifiertype.ModifierType;import com.frysning.springdnd.stats.ReadableStats;import java.text.MessageFormat;public class CalculatedAction extends Action {    private int toHit = 0;    private int hitBonus = 0;    private String enemyName;    public CalculatedAction(Action action, ReadableStats readableStats, int proficiencyBonus,        String enemyName) {        this.setActionType(action.getActionType());        this.setDamageType(action.getDamageType());        this.setModifierType(action.getModifierType());        this.setName(action.getName());        this.setHitDice(action.getHitDice());        this.setReach(action.getReach());        this.setTarget(action.getTarget());        this.setSpecialInformation(action.getSpecialInformation());        this.setWeaponAttack(action.isWeaponAttack());        this.enemyName = enemyName;        update(readableStats, proficiencyBonus);    }    void update(ReadableStats readableStats, int proficiencyBonus) {        toHit += proficiencyBonus;        int strStat = 0;        int dexStat = 0;        for (ModifierType modifierType : getModifiers()) {            switch (modifierType) {                case STR:                    strStat = readableStats.getStrength().getModifier();                    break;                case DEX:                    dexStat = readableStats.getDexterity().getModifier();                    break;                case CON:                case INT:                case WIS:                case CHA:                case NOT_SUPPORTED:                    break;            }        }        if (dexStat > strStat) {            toHit += dexStat;            hitBonus = dexStat;        } else {            toHit += strStat;            hitBonus = strStat;        }    }    public int getToHit() {        return toHit;    }    public void setToHit(int toHit) {        this.toHit = toHit;    }    public int getHitBonus() {        return hitBonus;    }    public void setHitBonus(int hitBonus) {        this.hitBonus = hitBonus;    }    public String getSpecialInformation() {        String information = super.getSpecialInformation();        if (information != null && information.length() > 0) {            return MessageFormat.format(information, enemyName);        }        return null;    }}