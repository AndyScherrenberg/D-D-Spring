package com.frysning.springdnd.stats;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StatCustomRepository {

    Stat getStat(Stat stat);
}