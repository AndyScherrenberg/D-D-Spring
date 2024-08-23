package com.frysning.springdnd.action;

import java.util.List;
import org.springframework.hateoas.EntityModel;
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
public class ActionController extends BaseActionController {


    ActionController(ActionRepository repository, ActionModelAssembler assembler) {
        super(repository, assembler);
    }

    private static String className() {
        return ActionController.class.getSimpleName();
    }

    @GetMapping()
    List<EntityModel<Action>> all() {
        return super.all(false);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    ResponseEntity<?> createAction(Action newAction) {
        newAction.setReaction(false);
        return super.createAction(newAction);
    }

    @PutMapping(path = "/{id}", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    ResponseEntity<?> updateAction(@PathVariable Long id, Action newAction) {
        return super.updateAction(id, newAction);
    }

    @GetMapping("/{id}")
    EntityModel<Action> getAction(@PathVariable Long id) {
        return super.getAction(id);
    }

}
