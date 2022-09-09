package com.frysning.springdnd.stats;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;


@Component
class StatModelAssembler implements RepresentationModelAssembler<Stat, EntityModel<Stat>> {

    @Override
    public EntityModel<Stat> toModel(Stat stat) {
        return EntityModel.of(stat,
            linkTo(methodOn(StatController.class).one(stat.getId())).withSelfRel(),
            linkTo(methodOn(StatController.class).all()).withRel("stats")
        );
    }
}