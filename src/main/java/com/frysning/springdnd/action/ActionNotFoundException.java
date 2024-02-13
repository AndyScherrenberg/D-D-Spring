package com.frysning.springdnd.action;


class ActionNotFoundException extends RuntimeException {

    ActionNotFoundException(Long id) {
        super("Could not find attack " + id);
    }
}