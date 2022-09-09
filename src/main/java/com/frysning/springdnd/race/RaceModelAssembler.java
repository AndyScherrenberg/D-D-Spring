package com.frysning.springdnd.race;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.frysning.springdnd.stats.StatController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;


@Component
class RaceModelAssembler implements RepresentationModelAssembler<Race, EntityModel<Race>> {

    @Override
    public EntityModel<Race> toModel(Race race) {
        return EntityModel.of(race,
            linkTo(methodOn(RaceController.class).one(race.getId())).withSelfRel(),
            linkTo(methodOn(StatController.class).one(race.getStat().getId())).withSelfRel(),
            linkTo(methodOn(RaceController.class).all()).withRel("races")
        );
    }
}