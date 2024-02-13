package com.frysning.springdnd.racetype;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;


@Component
class RaceTypeModelAssembler implements
    RepresentationModelAssembler<RaceType, EntityModel<RaceType>> {

    @Override
    public EntityModel<RaceType> toModel(RaceType raceType) {
        return EntityModel.of(raceType,
            linkTo(methodOn(RaceTypeController.class).one(raceType.getId())).withSelfRel(),
            linkTo(methodOn(RaceTypeController.class).all()).withRel("racetypes")
        );
    }
}