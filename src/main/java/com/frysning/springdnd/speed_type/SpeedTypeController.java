package com.frysning.springdnd.speed_type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("speedtype")
public class SpeedTypeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpeedTypeController.class);
    private final SpeedTypeRepository repository;
    private final SpeedTypeModelAssembler assembler;

    SpeedTypeController(SpeedTypeRepository repository, SpeedTypeModelAssembler assembler){
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping()
    List<EntityModel<SpeedType>> all() {
        LOGGER.info("Get al speedType");
        return repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EntityModel<SpeedType> one(@PathVariable Long id) {
        LOGGER.info("GET speedType by id: {}", id);

        SpeedType speedType = repository.findById(id) //
                .orElseThrow(() -> new SpeedTypeNotFoundException(id));

        return assembler.toModel(speedType);
    }

}
