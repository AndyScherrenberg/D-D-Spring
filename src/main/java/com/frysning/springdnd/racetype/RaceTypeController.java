package com.frysning.springdnd.racetype;

import java.util.List;
import java.util.stream.Collectors;
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

    private final RaceTypeRepository repository;
    private final RaceTypeModelAssembler assembler;

    RaceTypeController(RaceTypeRepository repository, RaceTypeModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping()
    List<EntityModel<RaceType>> all() {
        System.out.println("Something is trying to reach racetypes");
        return repository.findAll().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
    }

    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    ResponseEntity<?> newStat(RaceType newRaceType) {
        EntityModel<RaceType> entityModel = assembler.toModel(repository.save(newRaceType));

        return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
    }

    @PutMapping(path = "/{id}", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    ResponseEntity<?> updateAttack(@PathVariable Long id, RaceType newRaceType) {
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

        RaceType raceType = repository.findById(id) //
            .orElseThrow(() -> new RaceTypeNotFoundException(id));

        return assembler.toModel(raceType);
    }

}
