package com.frysning.springdnd.trait;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Trait {

    @JsonProperty(index = 1)
    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

    @JsonProperty(index = 2)
    private String name;
    @Column(length = 4000)
    private String description;

    public Trait(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Trait() {

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String value) {
        this.description = value;
    }


}
