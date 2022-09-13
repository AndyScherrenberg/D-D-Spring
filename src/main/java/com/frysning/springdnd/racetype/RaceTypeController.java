package com.frysning.springdnd.racetype;

import com.frysning.springdnd.size.SizeController;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("racetypes")
public class RaceTypeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SizeController.class);
    private final RaceTypeRepository repository;
    private final RaceTypeModelAssembler assembler;

    RaceTypeController(RaceTypeRepository repository, RaceTypeModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping()
    List<EntityModel<RaceType>> all() {
        LOGGER.info("GET all racetypes");
        return repository.findAll().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
    }

    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    ResponseEntity<?> newRaceType(RaceType newRaceType) {
        LOGGER.info("POST new RaceType with value: {}", newRaceType.toString());
        EntityModel<RaceType> entityModel = assembler.toModel(repository.save(newRaceType));

        return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
    }

    @PutMapping(path = "/{id}", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    ResponseEntity<?> updateRaceType(@PathVariable Long id, RaceType newRaceType) {
        LOGGER.info("PUT raceType with id and value: {}, {}", id, newRaceType.toString());
        RaceType updatedStats = repository.findById(id).map(stats ->
            {
                stats.setName(newRaceType.getName());
                return repository.save(stats);
            }
        ).orElseGet(() -> {
            newRaceType.setId(id);
            return repository.save(newRaceType);
        });

        EntityModel<RaceType> entityModel = assembler.toModel(repository.save(updatedStats));
        return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
            .body(entityModel);
    }

    @GetMapping("/{id}")
    public EntityModel<RaceType> one(@PathVariable Long id) {
        LOGGER.info("GET RaceType by id: {}", id);
        RaceType raceType = repository.findById(id) //
            .orElseThrow(() -> new RaceTypeNotFoundException(id));

        return assembler.toModel(raceType);
    }

}
