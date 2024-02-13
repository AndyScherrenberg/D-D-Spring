package com.frysning.springdnd.spells;


public class SpellNotFoundException extends RuntimeException {

    public SpellNotFoundException(Long id) {
        super("Could not find spell " + id);
    }
}