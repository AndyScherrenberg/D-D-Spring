package com.frysning.springdnd.enemies;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.frysning.springdnd.racetype.RaceTypeController;
import com.frysning.springdnd.stats.StatController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;


@Component
class EnemyModelAssembler implements RepresentationModelAssembler<Enemy, EntityModel<Enemy>> {

    @Override
    public EntityModel<Enemy> toModel(Enemy enemy) {
        return EntityModel.of(enemy,
            linkTo(methodOn(EnemyController.class).one(enemy.getId())).withSelfRel(),
            linkTo(
                methodOn(RaceTypeController.class).one(enemy.getRaceType().getId())).withRel(
                "racetypes"),
            linkTo(methodOn(StatController.class).one(enemy.getStat().getId())).withRel("stats"),
            linkTo(methodOn(EnemyController.class).all()).withRel("enemies")
        );
    }
}