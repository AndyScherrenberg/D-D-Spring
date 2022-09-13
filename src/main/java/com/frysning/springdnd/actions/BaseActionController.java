package com.frysning.springdnd.actions;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;

public class BaseActionController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass().getName());
    private final ActionRepository repository;
    private final ActionModelAssembler assembler;

    BaseActionController(ActionRepository repository, ActionModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }


    List<EntityModel<Action>> all(Boolean showReactions) {
        LOGGER.info("GET all actions");
        return repository.findAll().stream()
            .filter(action -> action.isReaction() == showReactions)
            .map(assembler::toModel)
            .collect(Collectors.toList());
    }

    ResponseEntity<?> createAction(Action newAction) {
        LOGGER.info("POST new action with value: {}", newAction.toString());
        EntityModel<Action> entityModel = assembler.toModel(repository.save(newAction));

        return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
    }


    ResponseEntity<?> updateAction(Long id, Action newAction) {
        LOGGER.info("PUT action with id and value: {}, {}", id, newAction.toString());
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
                action.setReaction(newAction.isReaction());
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

    EntityModel<Action> getAction(Long id) {
        LOGGER.info("GET action with id: {}", id);
        Action action = repository.findById(id) //
            .orElseThrow(() -> new ActionNotFoundException(id));

        return assembler.toModel(action);
    }

}
