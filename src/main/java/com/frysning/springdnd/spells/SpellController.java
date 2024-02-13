package com.frysning.springdnd.spells;

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
@RequestMapping("spells")
public class SpellController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpellController.class);
    private final SpellRepository repository;
    private final SpellModelAssembler assembler;

    SpellController(
            SpellRepository repository, SpellModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping()
    List<EntityModel<Spell>> all() {
        LOGGER.info("Get all spells");
        return repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
    }

    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    ResponseEntity<?> newSpell(Spell newSpell) {
        LOGGER.info("Post new spell: {}", newSpell);
        EntityModel<Spell> entityModel = assembler.toModel(repository.save(newSpell));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping(path = "/{id}", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    ResponseEntity<?> updateSpellType(@PathVariable Long id, Spell newSpell) {
        LOGGER.info("Update spellType by id {} and value: {}", id, newSpell.toString());
        Spell updatedSpell = repository.findById(id).map(spellType ->
                {
                    spellType.setName(newSpell.getName());

                    return repository.save(spellType);
                }
        ).orElseGet(() -> {
            newSpell.setId(id);
            return repository.save(newSpell);
        });

        EntityModel<Spell> entityModel = assembler.toModel(repository.save(updatedSpell));
        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @GetMapping("/{id}")
    public EntityModel<Spell> one(@PathVariable Long id) {
        LOGGER.info("Get spell by id {}", id);
        Spell spell = repository.findById(id) //
                .orElseThrow(() -> new SpellNotFoundException(id));

        return assembler.toModel(spell);
    }

}
