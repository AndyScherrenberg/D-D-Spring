package com.frysning.springdnd.speed;


public class SpeedValueNotFoundException extends RuntimeException {

    public SpeedValueNotFoundException(Long id) {
        super("Could not find speed " + id);
    }
}