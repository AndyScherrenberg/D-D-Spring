package com.frysning.springdnd.spell_level;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
class SpellTypeModelAssembler implements RepresentationModelAssembler<SpellLevel, EntityModel<SpellLevel>> {

    @Override
    public EntityModel<SpellLevel> toModel(SpellLevel spellLevel) {
        return EntityModel.of(spellLevel,
            linkTo(methodOn(SpellTypeController.class).one(spellLevel.getId())).withSelfRel(),
            linkTo(methodOn(SpellTypeController.class).all()).withRel("spelltypes")
        );
    }
}