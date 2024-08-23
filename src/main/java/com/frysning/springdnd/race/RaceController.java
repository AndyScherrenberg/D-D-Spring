package com.frysning.springdnd.race;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.frysning.springdnd.speed.SpeedValue;
import com.frysning.springdnd.speed.SpeedValueRepositoryImpl;
import com.frysning.springdnd.stats.StatRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("races")
public class RaceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RaceController.class);
    private final RaceRepository repository;
    private final RaceModelAssembler assembler;

    private final StatRepositoryImpl statRepository;
    private final SpeedValueRepositoryImpl speedRepository;

    RaceController(
            RaceRepository repository, RaceModelAssembler assembler, StatRepositoryImpl statRepository,
            SpeedValueRepositoryImpl speedRepository) {
        this.repository = repository;
        this.assembler = assembler;
        this.statRepository = statRepository;
        this.speedRepository = speedRepository;
    }

    @GetMapping()
    List<EntityModel<Race>> all() {
        LOGGER.info("Get al races");
        return repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
    }

    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    ResponseEntity<?> postRace(Race newRace) {
        LOGGER.info("Post new race: {}", newRace.getName());
        /**
         * We got issues with saving certain values
         * Speed there needs to be a way that can post the value of what speed we want with the range included
         * We don't do this at the moment and when we want to make a frontend exp it will be a hashle to do.
         * We don't want to select a speed from a list and post it.
         * We want to post a speedType with a Range and if this combi is already existing then we use that value otherwise
         * we make it.
         * For Speeds we also need to sanitize it so that when 2 of the same types get posted we only use the first oen
         * This function also needs to work for the Enemy controller.
         */

        newRace.setStat(statRepository.getStat(newRace.getStat()));
        if (newRace.getSpeed() != null) {
            var sanitizeSpeedList = new ArrayList<SpeedValue>();
            for (SpeedValue speedValue : newRace.getSpeed()) {
                var existingSpeed = speedRepository.getSpeed(speedValue);
                if (existingSpeed != null) {
                    sanitizeSpeedList.add(existingSpeed);
                }
            }
            newRace.setSpeed(sanitizeSpeedList);
        }

        EntityModel<Race> entityModel = assembler.toModel(repository.save(newRace));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping(path = "/{id}", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    ResponseEntity<?> updateRace(@PathVariable Long id, Race newRace) {
        LOGGER.info("Update race by id {} and value: {}", id, newRace.toString());
        Race updatedRace = repository.findById(id).map(race ->
                {
                    if (!race.getName().isBlank()) {
                        race.setName(newRace.getName());
                    }
                    if (newRace.getStat() != null) {
                        race.setStat(statRepository.getStat(newRace.getStat()));
                    }

                    if (newRace.getSpeed() != null) {
                        var sanitizeSpeedList = new ArrayList<SpeedValue>();
                        for (SpeedValue speed : newRace.getSpeed()) {
                            var existingSpeed = speedRepository.getSpeed(speed);
                            if (existingSpeed != null) {
                                sanitizeSpeedList.add(existingSpeed);
                            }
                        }
                        race.setSpeed(sanitizeSpeedList);
                    }

                    if (!newRace.getValidLanguages().isEmpty()) {
                        race.setLanguages(newRace.getValidLanguages());
                    }

                    race.setSize(newRace.getSize().getId());

                    return repository.save(race);
                }
        ).orElseGet(() -> {
            newRace.setId(id);
            return repository.save(newRace);
        });

        EntityModel<Race> entityModel = assembler.toModel(repository.save(updatedRace));
        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @GetMapping("/{id}")
    public EntityModel<Race> one(@PathVariable Long id) {
        LOGGER.info("Get race by id {}", id);
        Race race = repository.findById(id) //
                .orElseThrow(() -> new RaceNotFoundException(id));

        return assembler.toModel(race);
    }

}
