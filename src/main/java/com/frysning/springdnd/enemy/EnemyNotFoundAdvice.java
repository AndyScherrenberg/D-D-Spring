package com.frysning.springdnd.enemy;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class EnemyNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(EnemyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String attackNotFoundHandler(EnemyNotFoundException ex) {
        return ex.getMessage();
    }
}