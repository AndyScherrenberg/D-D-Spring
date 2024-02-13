package com.frysning.springdnd.spells;

import com.frysning.springdnd.spelltypes.SpellType;
import com.frysning.springdnd.spelltypes.SpellTypeController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
class SpellModelAssembler implements RepresentationModelAssembler<Spell, EntityModel<Spell>> {

    @Override
    public EntityModel<Spell> toModel(Spell spell) {
        return EntityModel.of(spell,
            linkTo(methodOn(SpellController.class).one(spell.getId())).withSelfRel(),
            linkTo(methodOn(SpellController.class).all()).withRel("spells")
        );
    }
}