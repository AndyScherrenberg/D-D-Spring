package com.frysning.springdnd.speed;

import com.frysning.springdnd.speed_type.SpeedType;
import com.frysning.springdnd.speed_type.SpeedTypeRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class SpeedRepositoryImpl implements SpeedCustomRepository{

    @Autowired
    @Lazy
    SpeedRepository speedRepository;

    @Autowired
    @Lazy
    SpeedTypeRepository speedTypeRepository;

    @Override
    public Speed getSpeed(Speed speed) {
        Speed newSpeed = null;

        if (speed.getId() != null && speed.getId() != 0) {
            newSpeed = speedRepository.getReferenceById(speed.getId());
        }

        SpeedType speedType = speedTypeRepository.getSpeedTypeByName(speed.getSpeedName());

        if (newSpeed == null) {
            var speeds = findEntity(speed);
            newSpeed = speeds.stream().filter(s -> Objects.equals(s.getSpeedName(), speed.getSpeedName())).findFirst().orElse(null);
        }

        if (newSpeed == null && speedType != null) {
            speed.setSpeedType(speedType);
            newSpeed = speedRepository.save(speed);
        }

        return newSpeed;
    }

    public List<Speed> findEntity(Speed speed) {
        return speedRepository.findEntity(speed);
    }
}
