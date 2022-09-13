package com.frysning.springdnd.language;


class LanguageNotFoundException extends RuntimeException {

    LanguageNotFoundException(Long id) {
        super("Could not find language " + id);
    }
}