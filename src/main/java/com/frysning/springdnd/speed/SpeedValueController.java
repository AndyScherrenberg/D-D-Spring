package com.frysning.springdnd.speed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("speed")
public class SpeedValueController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpeedValueController.class);
	private final SpeedValueRepository repository;
	private final SpeedValueModelAssembler assembler;

	SpeedValueController(SpeedValueRepository repository, SpeedValueModelAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
	}

	@GetMapping()
	List<EntityModel<SpeedValue>> all(@RequestParam(required = false, defaultValue = "false") Boolean showLinks) {
		LOGGER.info("Get al speed");
		return repository.findAll().stream()
				.map(model -> assembler.toModel(model, showLinks))
				.collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public EntityModel<SpeedValue> one(@PathVariable Long id) {
		LOGGER.info("Get speed by id {}", id);
		SpeedValue speedValue = repository.findById(id) //
				.orElseThrow(() -> new SpeedValueNotFoundException(id));

		return assembler.toModel(speedValue);
	}
}