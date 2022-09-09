package com.frysning.springdnd.race;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class RaceNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(RaceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String attackNotFoundHandler(RaceNotFoundException ex) {
        return ex.getMessage();
    }
}