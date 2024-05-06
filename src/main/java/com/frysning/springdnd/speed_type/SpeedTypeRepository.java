package com.frysning.springdnd.speed_type;


import com.frysning.springdnd.speed.Speed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeedTypeRepository extends JpaRepository<SpeedType, Long> {

    SpeedType getSpeedTypeByName(String name);

}