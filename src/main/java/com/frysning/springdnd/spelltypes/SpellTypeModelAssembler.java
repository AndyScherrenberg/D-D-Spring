package com.frysning.springdnd.spelltypes;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
class SpellTypeModelAssembler implements RepresentationModelAssembler<SpellType, EntityModel<SpellType>> {

    @Override
    public EntityModel<SpellType> toModel(SpellType spellType) {
        return EntityModel.of(spellType,
            linkTo(methodOn(SpellTypeController.class).one(spellType.getId())).withSelfRel(),
            linkTo(methodOn(SpellTypeController.class).all()).withRel("spelltypes")
        );
    }
}