package com.frysning.springdnd.language;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("languages")
public class LanguageController {

    private final LanguageRepository repository;
    private final LanguageModelAssembler assembler;

    LanguageController(LanguageRepository repository, LanguageModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping()
    List<EntityModel<Language>> all() {
        return repository.findAll()
            .stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
    }

    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    ResponseEntity<?> create(Language newLanguage) {
        EntityModel<Language> entityModel = assembler.toModel(repository.save(newLanguage));

        return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
    }


    @PutMapping(path = "/{id}", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    ResponseEntity<?> updateAttack(@PathVariable Long id, Language newLanguage) {
        Language updatedAction = repository.findById(id).map(action ->
            {
                action.setName(newLanguage.getName());
                return repository.save(action);
            }
        ).orElseGet(() -> {
            newLanguage.setId(id);
            return repository.save(newLanguage);
        });

        EntityModel<Language> entityModel = assembler.toModel(repository.save(updatedAction));
        return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
            .body(entityModel);
    }

    @GetMapping("/{id}")
    EntityModel<Language> one(@PathVariable Long id) {

        Language language = repository.findById(id) //
            .orElseThrow(() -> new LanguageNotFoundException(id));

        return assembler.toModel(language);
    }

}
