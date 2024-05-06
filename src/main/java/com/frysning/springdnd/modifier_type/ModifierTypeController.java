package com.frysning.springdnd.modifier_type;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("modifiertypes")
public class ModifierTypeController {

	private final static Logger LOGGER = LoggerFactory.getLogger(ModifierTypeController.class);

	@GetMapping()
	List<ModifierType> all() {
		LOGGER.info("GET all modifierTypes");
		return Stream.of(ModifierType.values())
				.collect(Collectors.toList());
	}
}
