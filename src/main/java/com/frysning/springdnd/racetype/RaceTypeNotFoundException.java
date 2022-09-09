package com.frysning.springdnd.racetype;


class RaceTypeNotFoundException extends RuntimeException {

    RaceTypeNotFoundException(Long id) {
        super("Could not find racetype " + id);
    }
}