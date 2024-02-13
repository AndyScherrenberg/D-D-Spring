package com.frysning.springdnd.trait;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;


@Component
class TraitModelAssembler implements RepresentationModelAssembler<Trait, EntityModel<Trait>> {

    @Override
    public EntityModel<Trait> toModel(Trait trait) {
        return EntityModel.of(trait,
            linkTo(methodOn(TraitController.class).one(trait.getId())).withSelfRel(),
            linkTo(methodOn(TraitController.class).all()).withRel("traits")
        );
    }
}