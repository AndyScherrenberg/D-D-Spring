package com.frysning.springdnd.traits;

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
@RequestMapping("traits")
public class TraitController {

    private final TraitRepository repository;
    private final TraitModelAssembler assembler;

    TraitController(
        TraitRepository repository, TraitModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping()
    List<EntityModel<Trait>> all() {
        return repository.findAll().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
    }

    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    ResponseEntity<?> newTrait(Trait newTrait) {
        EntityModel<Trait> entityModel = assembler.toModel(repository.save(newTrait));

        return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
    }

    @PutMapping(path = "/{id}", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    ResponseEntity<?> updateTrait(@PathVariable Long id, Trait newTrait) {
        Trait updatedTrait = repository.findById(id).map(trait ->
            {
                trait.setName(newTrait.getName());
                trait.setValue(newTrait.getValue());
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

        Trait trait = repository.findById(id) //
            .orElseThrow(() -> new TraitNotFoundException(id));

        return assembler.toModel(trait);
    }

}
