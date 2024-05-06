package com.frysning.springdnd.speed_type;


class SpeedTypeNotFoundException extends RuntimeException {

    SpeedTypeNotFoundException(Long id) {
        super("Could not find speedType " + id);
    }
}