package com.frysning.springdnd.race_type;


class RaceTypeNotFoundException extends RuntimeException {

    RaceTypeNotFoundException(Long id) {
        super("Could not find racetype " + id);
    }
}