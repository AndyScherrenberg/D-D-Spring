package com.frysning.springdnd.speed;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpeedValueRepository extends JpaRepository<SpeedValue, Long> {

    SpeedValue getSpeed(SpeedValue speedValue);

    @Query(value = "select * from speed where " +
            "range = :#{#speed.range} "
            ,nativeQuery = true)
    List<SpeedValue> findEntity(@Param("speed") SpeedValue speedValue);

}