package com.frysning.springdnd.effects;

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
@RequestMapping("effects")
public class EffectController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EffectController.class);
	private final EffectRepository repository;

	EffectController(EffectRepository repository) {
		this.repository = repository;
	}

	@GetMapping()
	List<EntityModel<Effect>> all(@RequestParam(required = false, defaultValue = "false") Boolean showLinks) {
		LOGGER.info("Get al effects");
		return repository.findAll().stream()
				.map(EntityModel::of)
				.collect(Collectors.toList());
	}
}