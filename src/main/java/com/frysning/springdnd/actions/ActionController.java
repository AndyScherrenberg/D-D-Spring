package com.frysning.springdnd.actions;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("actions")
public class ActionController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ActionController.class);
    private final ActionRepository repository;
    private final ActionModelAssembler assembler;

    ActionController(ActionRepository repository, ActionModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping()
    List<EntityModel<Action>> all() {
        LOGGER.info("GET all actions");
        return repository.findAll().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
    }

    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    ResponseEntity<?> create(Action newAction) {
        EntityModel<Action> entityModel = assembler.toModel(repository.save(newAction));

        return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
    }


    @PutMapping(path = "/{id}", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    ResponseEntity<?> updateAttack(@PathVariable Long id, Action newAction) {
        Action updatedAction = repository.findById(id).map(action ->
            {
                action.setName(newAction.getName());
                action.setReach(newAction.getReach());
                action.setHitDice(newAction.getHitDice());
                action.setTarget(newAction.getTarget());
                action.setActionType(newAction.getActionType());
                action.setModifierType(newAction.getModifierType());
                action.setDamageType(newAction.getDamageType());
                action.setWeaponAttack(newAction.isWeaponAttack());
                return repository.save(action);
            }
        ).orElseGet(() -> {
            newAction.setId(id);
            return repository.save(newAction);
        });

        EntityModel<Action> entityModel = assembler.toModel(repository.save(updatedAction));
        return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
            .body(entityModel);
    }

    @GetMapping("/{id}")
    EntityModel<Action> one(@PathVariable Long id) {

        Action action = repository.findById(id) //
            .orElseThrow(() -> new ActionNotFoundException(id));

        return assembler.toModel(action);
    }

}
