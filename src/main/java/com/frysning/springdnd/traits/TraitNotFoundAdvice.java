package com.frysning.springdnd.traits;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class TraitNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(TraitNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String attackNotFoundHandler(TraitNotFoundException ex) {
        return ex.getMessage();
    }
}