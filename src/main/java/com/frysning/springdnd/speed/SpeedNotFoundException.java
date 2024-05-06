package com.frysning.springdnd.speed;


public class SpeedNotFoundException extends RuntimeException {

    public SpeedNotFoundException(Long id) {
        super("Could not find speed " + id);
    }
}