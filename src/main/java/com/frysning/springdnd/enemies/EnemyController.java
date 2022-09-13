package com.frysning.springdnd.enemies;

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
@RequestMapping("enemies")
public class EnemyController {

    private final EnemyRepository repository;
    private final EnemyModelAssembler assembler;

    EnemyController(EnemyRepository repository, EnemyModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping()
    List<EntityModel<Enemy>> all() {
        return repository.findAll().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
    }

    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    ResponseEntity<?> newEnemy(Enemy newEnemy) {
        System.out.println("Enemy " + newEnemy.toString());
        EntityModel<Enemy> entityModel = assembler.toModel(repository.save(newEnemy));

        return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
    }

    @PutMapping(path = "/{id}", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    ResponseEntity<?> updateEnemy(@PathVariable Long id, Enemy newEnemy) {
        Enemy updatedEnemy = repository.findById(id).map(enemy ->
            {
                enemy.setName(newEnemy.getName());
                if (newEnemy.getStat() != null) {
                    enemy.setStat(newEnemy.getStat());
                }
                if (newEnemy.getRaceType() != null) {
                    enemy.setRaceType(newEnemy.getRaceType());
                }

                if (!newEnemy.getValidSpeed().isEmpty()) {
                    enemy.setSpeed(newEnemy.getValidSpeed());
                }

                if (newEnemy.getChallengeRating() != null) {
                    enemy.setChallengeRating(newEnemy.getChallengeRating());
                }

                if (!newEnemy.getValidSpeed().isEmpty()) {
                    enemy.setSpeed(newEnemy.getValidSpeed());
                }

                if (!newEnemy.getValidActions().isEmpty()) {
                    enemy.setActions(newEnemy.getValidActions());
                }

                if (!newEnemy.getValidReactions().isEmpty()) {
                    enemy.setReactions(newEnemy.getValidReactions());
                }

                enemy.setSize(newEnemy.getSize().getId());
                enemy.setSavingThrows(newEnemy.getSavingThrows());

                return repository.save(enemy);
            }
        ).orElseGet(() -> {
            newEnemy.setId(id);
            return repository.save(newEnemy);
        });

        EntityModel<Enemy> entityModel = assembler.toModel(repository.save(updatedEnemy));
        return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
            .body(entityModel);
    }

    @GetMapping("/{id}")
    public EntityModel<Enemy> one(@PathVariable Long id) {

        Enemy enemy = repository.findById(id) //
            .orElseThrow(() -> new EnemyNotFoundException(id));

        return assembler.toModel(enemy);
    }

}
