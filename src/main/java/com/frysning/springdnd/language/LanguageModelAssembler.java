package com.frysning.springdnd.language;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;


@Component
class LanguageModelAssembler implements
    RepresentationModelAssembler<Language, EntityModel<Language>> {

    @Override
    public EntityModel<Language> toModel(Language language) {
        return EntityModel.of(language,
            linkTo(methodOn(LanguageController.class).one(language.getId())).withSelfRel(),
            linkTo(methodOn(LanguageController.class).all()).withRel("languages")
        );
    }
}