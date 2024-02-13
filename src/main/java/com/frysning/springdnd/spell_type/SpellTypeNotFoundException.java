package com.frysning.springdnd.spell_type;


public class SpellTypeNotFoundException extends RuntimeException {

    public SpellTypeNotFoundException(Long id) {
        super("Could not find spellType " + id);
    }
}