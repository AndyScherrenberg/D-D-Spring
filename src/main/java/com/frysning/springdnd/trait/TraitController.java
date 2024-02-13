package com.frysning.springdnd.trait;

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
@RequestMapping("traits")
public class TraitController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TraitController.class);
    private final TraitRepository repository;
    private final TraitModelAssembler assembler;

    TraitController(
        TraitRepository repository, TraitModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping()
    List<EntityModel<Trait>> all() {
        LOGGER.info("GET all traits");
        return repository.findAll().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
    }

    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    ResponseEntity<?> newTrait(Trait newTrait) {
        LOGGER.info("POST new trait: {}", newTrait);
        EntityModel<Trait> entityModel = assembler.toModel(repository.save(newTrait));

        return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
    }

    @PutMapping(path = "/{id}", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    ResponseEntity<?> updateTrait(@PathVariable Long id, Trait newTrait) {
        LOGGER.info("PUT trait with id and value: {}, {}", id, newTrait);
        Trait updatedTrait = repository.findById(id).map(trait ->
            {
                trait.setName(newTrait.getName());
                trait.setDescription(newTrait.getDescription());
                return repository.save(newTrait);
            }
        ).orElseGet(() -> {
            newTrait.setId(id);
            return repository.save(newTrait);
        });

        EntityModel<Trait> entityModel = assembler.toModel(repository.save(updatedTrait));
        return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
            .body(entityModel);
    }

    @GetMapping("/{id}")
    public EntityModel<Trait> one(@PathVariable Long id) {
        LOGGER.info("GET trait by id: {}", id);
        Trait trait = repository.findById(id) //
            .orElseThrow(() -> new TraitNotFoundException(id));

        return assembler.toModel(trait);
    }

}
