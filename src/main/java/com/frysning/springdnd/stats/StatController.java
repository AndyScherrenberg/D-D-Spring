package com.frysning.springdnd.stats;

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
@RequestMapping("stats")
public class StatController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatController.class);
    private final StatRepository repository;
    private final StatModelAssembler assembler;

    StatController(StatRepository repository, StatModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping()
    List<EntityModel<Stat>> all() {
        LOGGER.info("GET all stats");

        return repository.findAll().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
    }

    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    ResponseEntity<?> newStat(Stat newStat) {
        LOGGER.info("POST new stat with value: {}", newStat.toString());

        EntityModel<Stat> entityModel = assembler.toModel(repository.save(newStat));

        return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
    }

    @PutMapping(path = "/{id}", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    ResponseEntity<?> updateAttack(@PathVariable Long id, Stat newStat) {
        LOGGER.info("PUT stat with id and value: {}, {}", id, newStat.toString());
        Stat updatedStats = repository.findById(id).map(stats ->
            {
                stats.setCharisma(newStat.getCharisma());
                stats.setConstitution(newStat.getConstitution());
                stats.setWisdom(newStat.getWisdom());
                stats.setDexterity(newStat.getDexterity());
                stats.setIntelligence(newStat.getIntelligence());
                stats.setStrength(newStat.getStrength());
                return repository.save(stats);
            }
        ).orElseGet(() -> {
            newStat.setId(id);
            return repository.save(newStat);
        });

        EntityModel<Stat> entityModel = assembler.toModel(repository.save(updatedStats));
        return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
            .body(entityModel);
    }

    @GetMapping("/{id}")
    public EntityModel<Stat> one(@PathVariable Long id) {
        LOGGER.info("GET stat by id: {}", id);

        Stat stat = repository.findById(id) //
            .orElseThrow(() -> new StatNotFoundException(id));

        return assembler.toModel(stat);
    }

}
