package com.frysning.springdnd.combiner;import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;import com.frysning.springdnd.enemy.EnemyController;import com.frysning.springdnd.race.RaceController;import org.springframework.hateoas.EntityModel;import org.springframework.hateoas.server.RepresentationModelAssembler;import org.springframework.stereotype.Component;@Componentclass CombinedModelAssembler implements    RepresentationModelAssembler<Combined, EntityModel<Combined>> {    @Override    public EntityModel<Combined> toModel(Combined combined) {        return EntityModel.of(combined,            linkTo(methodOn(EnemyController.class).one(combined.enemy().getId())).withSelfRel(),            linkTo(methodOn(RaceController.class).one(combined.race().getId())).withSelfRel()        );    }}