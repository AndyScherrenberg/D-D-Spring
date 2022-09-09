package com.frysning.springdnd.racetype;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class RaceTypeNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(RaceTypeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String attackNotFoundHandler(RaceTypeNotFoundException ex) {
        return ex.getMessage();
    }
}