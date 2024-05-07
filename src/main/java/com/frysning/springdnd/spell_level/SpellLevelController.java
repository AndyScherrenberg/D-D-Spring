package com.frysning.springdnd.spell_level;

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
    List<EntityModel<SpellLevel>> all() {
        LOGGER.info("Get al spellTypes");
        return repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
    }

    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    ResponseEntity<?> newSpellType(SpellLevel newSpellLevel) {
        LOGGER.info("Post new spellType: {}", newSpellLevel.toString());
        EntityModel<SpellLevel> entityModel = assembler.toModel(repository.save(newSpellLevel));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping(path = "/{id}", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    ResponseEntity<?> updateSpellType(@PathVariable Long id, SpellLevel newSpellLevel) {
        LOGGER.info("Update spellType by id {} and value: {}", id, newSpellLevel.toString());
        SpellLevel updatedSpellLevel = repository.findById(id).map(spellLevel ->
                {
                    spellLevel.setName(newSpellLevel.getName());

                    return repository.save(spellLevel);
                }
        ).orElseGet(() -> {
            newSpellLevel.setId(id);
            return repository.save(newSpellLevel);
        });

        EntityModel<SpellLevel> entityModel = assembler.toModel(repository.save(updatedSpellLevel));
        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @GetMapping("/{id}")
    public EntityModel<SpellLevel> one(@PathVariable Long id) {
        LOGGER.info("Get spellType by id {}", id);
        SpellLevel spellLevel = repository.findById(id) //
                .orElseThrow(() -> new SpellTypeNotFoundException(id));

        return assembler.toModel(spellLevel);
    }

}
