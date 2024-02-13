package com.frysning.springdnd.enemy;


public class EnemyNotFoundException extends RuntimeException {

    public EnemyNotFoundException(Long id) {
        super("Could not find enemy " + id);
    }
}