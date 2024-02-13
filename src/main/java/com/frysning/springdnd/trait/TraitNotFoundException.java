package com.frysning.springdnd.trait;


public class TraitNotFoundException extends RuntimeException {

    public TraitNotFoundException(Long id) {
        super("Could not find trait " + id);
    }
}