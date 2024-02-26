package com.frysning.springdnd.stats;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StatRepository extends JpaRepository<Stat, Long>, StatCustomRepository {

    Stat getStat(Stat stat);

    @Query(value = "select * from stats where " +
            "strength= :#{#stat.strength} " +
            "and dexterity = :#{#stat.dexterity} " +
            "and constitution = :#{#stat.constitution} " +
            "and intelligence = :#{#stat.intelligence} " +
            "and wisdom = :#{#stat.wisdom} " +
            "and charisma = :#{#stat.charisma} "
          //  "LIMIT = 1",
            ,nativeQuery = true)
     Stat findEntity(@Param("stat") Stat stat);
}