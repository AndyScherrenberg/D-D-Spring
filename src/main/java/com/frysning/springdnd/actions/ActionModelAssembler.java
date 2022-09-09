package com.frysning.springdnd.actions;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;


@Component
class ActionModelAssembler implements RepresentationModelAssembler<Action, EntityModel<Action>> {

    @Override
    public EntityModel<Action> toModel(Action action) {
        return EntityModel.of(action,
            linkTo(methodOn(ActionController.class).one(action.getId())).withSelfRel(),
            linkTo(methodOn(ActionController.class).all()).withRel("attacks")
        );
    }
}