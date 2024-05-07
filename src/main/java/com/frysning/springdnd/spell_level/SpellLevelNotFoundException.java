package com.frysning.springdnd.spell_level;


public class SpellTypeNotFoundException extends RuntimeException {

    public SpellTypeNotFoundException(Long id) {
        super("Could not find spellType " + id);
    }
}