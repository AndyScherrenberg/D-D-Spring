package com.frysning.springdnd.magic_school;

import com.frysning.springdnd.modifier_type.ModifierType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("magicschools")
public class MagicSchoolController {

    private final static Logger LOGGER = LoggerFactory.getLogger(MagicSchoolController.class);

    private final MagicSchoolRepository repository;

    MagicSchoolController(MagicSchoolRepository repository){
        this.repository = repository;
    }

    @GetMapping()
    List<EntityModel<MagicSchool>> all() {
        LOGGER.info("GET all magic schools");
        return repository.findAll().stream().map(EntityModel::of)
            .collect(Collectors.toList());
    }
}
