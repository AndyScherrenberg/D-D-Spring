package com.frysning.springdnd.spell_level;


public class SpellLevelNotFoundException extends RuntimeException {

    public SpellLevelNotFoundException(Long id) {
        super("Could not find spellLevel " + id);
    }
}