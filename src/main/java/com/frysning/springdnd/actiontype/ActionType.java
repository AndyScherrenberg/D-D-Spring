package com.frysning.springdnd.actiontype;import javax.persistence.Entity;import javax.persistence.GeneratedValue;import javax.persistence.GenerationType;import javax.persistence.Id;@Entitypublic class ActionType {    private @Id    @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;    private String name;    public String getName() {        return name;    }    public void setName(String name) {        this.name = name;    }}