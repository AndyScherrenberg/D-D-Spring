package com.frysning.springdnd.combiner;import com.frysning.springdnd.actions.Action;import com.frysning.springdnd.actions.ActionRepository;import com.frysning.springdnd.enemies.Enemy;import com.frysning.springdnd.enemies.EnemyNotFoundException;import com.frysning.springdnd.enemies.EnemyRepository;import com.frysning.springdnd.race.Race;import com.frysning.springdnd.race.RaceNotFoundException;import com.frysning.springdnd.race.RaceRepository;import com.frysning.springdnd.traits.Trait;import com.frysning.springdnd.traits.TraitRepository;import java.util.List;import java.util.Optional;import org.slf4j.Logger;import org.slf4j.LoggerFactory;import org.springframework.hateoas.EntityModel;import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.PathVariable;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RequestParam;import org.springframework.web.bind.annotation.RestController;@RestController@RequestMapping("combine")public class CombinerController {    private static final Logger LOGGER = LoggerFactory.getLogger(CombinerController.class);    private final EnemyRepository enemyRepository;    private final RaceRepository raceRepository;    private final TraitRepository traitRepository;    private final ActionRepository actionRepository;    private final CombinedModelAssembler combinedModelAssembler;    CombinerController(EnemyRepository enemyRepository, RaceRepository raceRepository,        CombinedModelAssembler combinedModelAssembler, TraitRepository traitRepository,        ActionRepository actionRepository) {        this.enemyRepository = enemyRepository;        this.raceRepository = raceRepository;        this.combinedModelAssembler = combinedModelAssembler;        this.traitRepository = traitRepository;        this.actionRepository = actionRepository;    }    @RequestMapping("/{enemyId}/{raceId}")    EntityModel<Combined> one(@PathVariable Long enemyId, @PathVariable Long raceId) {        LOGGER.info("Get Combined entity of {} and {}", enemyId, raceId);        Enemy enemy = enemyRepository.findById(enemyId) //            .orElseThrow(() -> new EnemyNotFoundException(enemyId));        Race race = raceRepository.findById(raceId) //            .orElseThrow(() -> new RaceNotFoundException(raceId));        Combined combined = new Combined(enemy, race);        return combinedModelAssembler.toModel(combined);    }    @GetMapping("/{enemyId}/{raceId}/extra")    public EntityModel<Combined> oneWithTraits(@PathVariable Long enemyId,        @PathVariable Long raceId,        @RequestParam(required = false) Long[] traits,        @RequestParam(required = false) Long[] actions,        @RequestParam(required = false) Long[] reactions) {        LOGGER.info(            "Get combinded of EnemyId {} and RaceId {}, with traits {}, actions {}, reactions {}",            enemyId, raceId,            traits,            actions, reactions);        Enemy enemy = enemyRepository.findById(enemyId) //            .orElseThrow(() -> new EnemyNotFoundException(enemyId));        Race race = raceRepository.findById(raceId) //            .orElseThrow(() -> new RaceNotFoundException(raceId));        List<Trait> enemyTraits = enemy.getTraits();        if (traits != null) {            for (Long trait : traits) {                Optional<Trait> optionalTrait = traitRepository.findById(trait);                if (optionalTrait.isPresent()) {                    if (!enemyTraits.contains(optionalTrait.get())) {                        enemyTraits.add(optionalTrait.get());                    }                }            }        }        enemy.setTraits(enemyTraits);        enemy.setActions(createExtraActions(actions, enemy.getActions()));        enemy.setReactions(createExtraActions(reactions, enemy.getReactions()));        Combined combined = new Combined(enemy, race);        return combinedModelAssembler.toModel(combined);    }    private List<Action> createExtraActions(        @RequestParam(required = false) Long[] actions,        List<Action> actionList) {        if (actions != null) {            for (Long reaction : actions) {                Optional<Action> optionalReaction = actionRepository.findById(reaction);                if (optionalReaction.isPresent()) {                    if (!actionList.contains(optionalReaction.get())) {                        actionList.add(optionalReaction.get());                    }                }            }        }        return actionList;    }}