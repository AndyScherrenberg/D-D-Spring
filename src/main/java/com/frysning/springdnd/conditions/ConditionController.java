package com.frysning.springdnd.conditions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("conditions")
public class ConditionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConditionController.class);
	private final ConditionRepository repository;

	ConditionController(ConditionRepository repository) {
		this.repository = repository;
	}

	@GetMapping()
	List<EntityModel<Condition>> all(@RequestParam(required = false, defaultValue = "false") Boolean showLinks) {
		LOGGER.info("Get al conditions");
		return repository.findAll().stream()
				.map(EntityModel::of)
				.collect(Collectors.toList());
	}
}