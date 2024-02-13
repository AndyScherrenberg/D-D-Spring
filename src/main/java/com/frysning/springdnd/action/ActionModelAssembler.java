package com.frysning.springdnd.action;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;


@Component
class ActionModelAssembler implements RepresentationModelAssembler<Action, EntityModel<Action>> {

    @Override
    public EntityModel<Action> toModel(Action action) {
        String rel = "actions";
        if (action.isReaction()) {
            rel = "reactions";
        }

        return EntityModel.of(action,
            linkTo(methodOn(BaseActionController.class).getAction(action.getId())).withSelfRel(),
            linkTo(methodOn(BaseActionController.class).all(action.isReaction())).withRel(rel)
        );
    }
}