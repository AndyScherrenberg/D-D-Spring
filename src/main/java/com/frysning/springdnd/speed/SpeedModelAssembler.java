package com.frysning.springdnd.speed;

import com.frysning.springdnd.speed_type.SpeedTypeController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
class SpeedModelAssembler implements RepresentationModelAssembler<Speed, EntityModel<Speed>> {

	public EntityModel<Speed> toModel(Speed speed, boolean withLinks) {
		if (withLinks) {
			return toModel(speed);
		} else {
			return EntityModel.of(speed, Links.NONE);
		}
	}

	@Override
	public EntityModel<Speed> toModel(Speed speed) {
		return EntityModel.of(speed,
				linkTo(methodOn(SpeedController.class).all(true)).withRel("speed"),
				linkTo(methodOn(SpeedController.class).one(speed.getId())).withSelfRel(),
				linkTo(methodOn(SpeedTypeController.class).one(speed.getSpeedTypeId())).withRel("speedtype")
		);
	}
}