package com.frysning.springdnd.action_type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("actiontypes")
public class ActionTypeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ActionTypeController.class);
	private final ActionTypeRepository repository;

	ActionTypeController(ActionTypeRepository repository) {
		this.repository = repository;
	}

	@GetMapping()
	List<EntityModel<ActionType>> all(@RequestParam(required = false, defaultValue = "false") Boolean showLinks) {
		LOGGER.info("Get al action types");
		return repository.findAll().stream()
				.map(EntityModel::of)
				.collect(Collectors.toList());
	}
}