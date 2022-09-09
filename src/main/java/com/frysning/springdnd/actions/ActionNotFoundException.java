package com.frysning.springdnd.actions;


class ActionNotFoundException extends RuntimeException {

    ActionNotFoundException(Long id) {
        super("Could not find attack " + id);
    }
}