package com.frysning.springdnd.spelltypes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("spelltypes")
public class SpellTypeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpellTypeController.class);
    private final SpellTypeRepository repository;
    private final SpellTypeModelAssembler assembler;

    SpellTypeController(
            SpellTypeRepository repository, SpellTypeModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping()
    List<EntityModel<SpellType>> all() {
        LOGGER.info("Get al spellTypes");
        return repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
    }

    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    ResponseEntity<?> newSpellType(SpellType newSpellType) {
        LOGGER.info("Post new spellType: {}", newSpellType.toString());
        EntityModel<SpellType> entityModel = assembler.toModel(repository.save(newSpellType));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping(path = "/{id}", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    ResponseEntity<?> updateSpellType(@PathVariable Long id, SpellType newSpellType) {
        LOGGER.info("Update spellType by id {} and value: {}", id, newSpellType.toString());
        SpellType updatedSpellType = repository.findById(id).map(spellType ->
                {
                    spellType.setName(newSpellType.getName());

                    return repository.save(spellType);
                }
        ).orElseGet(() -> {
            newSpellType.setId(id);
            return repository.save(newSpellType);
        });

        EntityModel<SpellType> entityModel = assembler.toModel(repository.save(updatedSpellType));
        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @GetMapping("/{id}")
    public EntityModel<SpellType> one(@PathVariable Long id) {
        LOGGER.info("Get spellType by id {}", id);
        SpellType spellType = repository.findById(id) //
                .orElseThrow(() -> new SpellTypeNotFoundException(id));

        return assembler.toModel(spellType);
    }

}
