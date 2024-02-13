package com.frysning.springdnd.actions;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.frysning.springdnd.actiontype.ActionType;
import com.frysning.springdnd.damegetype.DamageType;
import com.frysning.springdnd.modifiertype.ModifierType;
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
import javax.persistence.ManyToOne;

@Entity
public class Action {

    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "action_type_id", referencedColumnName = "id")
    private ActionType actionType;

    private String reach;

    private String target;
    @ManyToOne
    @JoinColumn(name = "damage_type_id", referencedColumnName = "id")
    private DamageType damageType;

    private String hitDice;

    private String specialInformation;
    @ElementCollection
    private List<Integer> modifierType = new ArrayList<>();

    private boolean isWeaponAttack;
    @Column(columnDefinition = "boolean default false")
    private boolean isReaction;

    public Action() {
    }

    public Action(String name) {
        this.name = name;
    }

    public boolean isReaction() {
        return isReaction;
    }

    public void setReaction(boolean reaction) {
        isReaction = reaction;
    }

    public String getSpecialInformation() {
        return specialInformation;
    }

    public void setSpecialInformation(String specialInformation) {
        this.specialInformation = specialInformation;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public void setDamageType(DamageType damageType) {
        this.damageType = damageType;
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

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public String getReach() {
        return reach;
    }

    public void setReach(String reach) {
        this.reach = reach;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getHitDice() {
        return hitDice;
    }

    public void setHitDice(String hitDice) {
        this.hitDice = hitDice;
    }

    public List<ModifierType> getModifiers() {
        return modifierType.stream().map(ModifierType::getById).collect(
            Collectors.toList());
    }

    @JsonIgnore
    public List<Integer> getModifierType() {
        return modifierType;
    }

    public void setModifierType(
        List<Integer> modifierType) {
        this.modifierType = modifierType;
    }

    public boolean isWeaponAttack() {
        return isWeaponAttack;
    }

    public void setWeaponAttack(boolean weaponAttack) {
        isWeaponAttack = weaponAttack;
    }
}
