package com.frysning.springdnd.race;


public class RaceNotFoundException extends RuntimeException {

    public RaceNotFoundException(Long id) {
        super("Could not find race " + id);
    }
}