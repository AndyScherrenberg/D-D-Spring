package com.frysning.springdnd.traits;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Trait {

    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

    private String name;
    @Column(length = 2000)
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
