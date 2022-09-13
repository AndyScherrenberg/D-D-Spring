package com.frysning.springdnd.actions;

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
@RequestMapping("reactions")
public class ReactionController extends BaseActionController {

    ReactionController(ActionRepository repository, ActionModelAssembler assembler) {
        super(repository, assembler);
    }

    @GetMapping()
    List<EntityModel<Action>> all() {
        return super.all(true);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    ResponseEntity<?> createAction(Action newAction) {
        newAction.setReaction(true);
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
