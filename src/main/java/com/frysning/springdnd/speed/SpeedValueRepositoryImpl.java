package com.frysning.springdnd.speed;

import com.frysning.springdnd.speed_type.SpeedType;
import com.frysning.springdnd.speed_type.SpeedTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.List;
import java.util.Objects;

public class SpeedValueRepositoryImpl implements SpeedValueCustomRepository {

    @Autowired
    @Lazy
    SpeedValueRepository speedValueRepository;

    @Autowired
    @Lazy
    SpeedTypeRepository speedTypeRepository;

    @Override
    public SpeedValue getSpeed(SpeedValue speedValue) {
        SpeedValue newSpeedValue = null;

        if (speedValue.getId() != null && speedValue.getId() != 0) {
            newSpeedValue = speedValueRepository.getReferenceById(speedValue.getId());
        }

        SpeedType speedType = speedTypeRepository.getSpeedTypeByName(speedValue.getSpeedName());

        if (newSpeedValue == null) {
            var speeds = findEntity(speedValue);
            newSpeedValue = speeds.stream().filter(s -> Objects.equals(s.getSpeedName(), speedValue.getSpeedName())).findFirst().orElse(null);
        }

        if (newSpeedValue == null && speedType != null) {
            speedValue.setSpeedType(speedType);
            newSpeedValue = speedValueRepository.save(speedValue);
        }

        return newSpeedValue;
    }

    public List<SpeedValue> findEntity(SpeedValue speedValue) {
        return speedValueRepository.findEntity(speedValue);
    }
}
