package com.frysning.springdnd.stats;


class StatNotFoundException extends RuntimeException {

    StatNotFoundException(Long id) {
        super("Could not find stat " + id);
    }
}