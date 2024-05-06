package com.frysning.springdnd.speed_type;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SpeedTypeModelAssembler implements RepresentationModelAssembler<SpeedType, EntityModel<SpeedType>> {

    @Override
    public EntityModel<SpeedType> toModel(SpeedType speedType) {
        return EntityModel.of(speedType,
                linkTo(methodOn(SpeedTypeController.class).all()).withRel("speedtype"),
                linkTo(methodOn(SpeedTypeController.class).one(speedType.getId())).withSelfRel()
        );
    }
}
