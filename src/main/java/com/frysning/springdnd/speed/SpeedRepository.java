package com.frysning.springdnd.speed;


import com.frysning.springdnd.stats.Stat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpeedRepository extends JpaRepository<Speed, Long> {

    Speed getSpeed(Speed speed);

    @Query(value = "select * from speed where " +
            "range = :#{#speed.range} "
            ,nativeQuery = true)
    List<Speed> findEntity(@Param("speed") Speed speed);

}