package com.frysning.springdnd.damage_type;

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
@RequestMapping("damagetypes")
public class DamageTypeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DamageTypeController.class);
	private final DamageTypeRepository repository;

	DamageTypeController(DamageTypeRepository repository) {
		this.repository = repository;
	}

	@GetMapping()
	List<EntityModel<DamageType>> all(@RequestParam(required = false, defaultValue = "false") Boolean showLinks) {
		LOGGER.info("Get all damage types");
		return repository.findAll().stream()
				.map(EntityModel::of)
				.collect(Collectors.toList());
	}
}