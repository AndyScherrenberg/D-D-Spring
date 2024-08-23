package com.frysning.springdnd.speed_type;


import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeedTypeRepository extends JpaRepository<SpeedType, Long> {

    SpeedType getSpeedTypeByName(String name);

}