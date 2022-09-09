package com.frysning.springdnd.race;

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
@RequestMapping("races")
public class RaceController {

    private final RaceRepository repository;
    private final RaceModelAssembler assembler;

    RaceController(
        RaceRepository repository, RaceModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping()
    List<EntityModel<Race>> all() {
        return repository.findAll().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
    }

    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    ResponseEntity<?> newRace(Race newRace) {
        EntityModel<Race> entityModel = assembler.toModel(repository.save(newRace));

        return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
    }

    @PutMapping(path = "/{id}", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    ResponseEntity<?> updateRace(@PathVariable Long id, Race newRace) {
        Race updatedRace = repository.findById(id).map(race ->
            {
                race.setName(newRace.getName());
                if (newRace.getStat() != null) {
                    race.setStat(newRace.getStat());
                }

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

        Race race = repository.findById(id) //
            .orElseThrow(() -> new RaceNotFoundException(id));

        return assembler.toModel(race);
    }

}
