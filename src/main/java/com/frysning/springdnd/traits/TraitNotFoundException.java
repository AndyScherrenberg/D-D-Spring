package com.frysning.springdnd.traits;


public class TraitNotFoundException extends RuntimeException {

    public TraitNotFoundException(Long id) {
        super("Could not find trait " + id);
    }
}