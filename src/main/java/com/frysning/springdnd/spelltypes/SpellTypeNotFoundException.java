package com.frysning.springdnd.spelltypes;


public class SpellTypeNotFoundException extends RuntimeException {

    public SpellTypeNotFoundException(Long id) {
        super("Could not find spellType " + id);
    }
}