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
@RequestMapping("spelllevel")
public class SpellLevelController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpellLevelController.class);
    private final SpellLevelRepository repository;
    private final SpellLevelModelAssembler assembler;

    SpellLevelController(
            SpellLevelRepository repository, SpellLevelModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping()
    List<EntityModel<SpellLevel>> all() {
        LOGGER.info("Get al spell levels");
        return repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EntityModel<SpellLevel> one(@PathVariable Long id) {
        LOGGER.info("Get spell level by id {}", id);
        SpellLevel spellLevel = repository.findById(id) //
                .orElseThrow(() -> new SpellLevelNotFoundException(id));

        return assembler.toModel(spellLevel);
    }

}
