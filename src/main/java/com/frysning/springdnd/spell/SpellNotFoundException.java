package com.frysning.springdnd.spell;


public class SpellNotFoundException extends RuntimeException {

    public SpellNotFoundException(Long id) {
        super("Could not find spell " + id);
    }
}