package com.frysning.springdnd.speed;

import com.frysning.springdnd.speed_type.SpeedTypeController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
class SpeedValueModelAssembler implements RepresentationModelAssembler<SpeedValue, EntityModel<SpeedValue>> {

	public EntityModel<SpeedValue> toModel(SpeedValue speedValue, boolean withLinks) {
		if (withLinks) {
			return toModel(speedValue);
		} else {
			return EntityModel.of(speedValue, Links.NONE);
		}
	}

	@Override
	public EntityModel<SpeedValue> toModel(SpeedValue speedValue) {
		return EntityModel.of(speedValue,
				linkTo(methodOn(SpeedValueController.class).all(true)).withRel("speed"),
				linkTo(methodOn(SpeedValueController.class).one(speedValue.getId())).withSelfRel(),
				linkTo(methodOn(SpeedTypeController.class).one(speedValue.getSpeedTypeId())).withRel("speedtype")
		);
	}
}