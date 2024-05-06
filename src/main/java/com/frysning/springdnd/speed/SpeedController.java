package com.frysning.springdnd.speed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("speed")
public class SpeedController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpeedController.class);
	private final SpeedRepository repository;
	private final SpeedModelAssembler assembler;

	SpeedController(SpeedRepository repository, SpeedModelAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
	}

	@GetMapping()
	List<EntityModel<Speed>> all(@RequestParam(required = false, defaultValue = "false") Boolean showLinks) {
		LOGGER.info("Get al speed");
		return repository.findAll().stream()
				.map(model -> assembler.toModel(model, showLinks))
				.collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public EntityModel<Speed> one(@PathVariable Long id) {
		LOGGER.info("Get speed by id {}", id);
		Speed speed = repository.findById(id) //
				.orElseThrow(() -> new SpeedNotFoundException(id));

		return assembler.toModel(speed);
	}
}