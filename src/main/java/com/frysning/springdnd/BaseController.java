package com.frysning.springdnd;

import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface BaseController {
	@GetMapping()
	<T> List<EntityModel<T>> all(@RequestParam(required = false, defaultValue = "false") Boolean showLinks);

	@GetMapping()
	<T> EntityModel<T> one(@PathVariable Long id, @RequestParam(required = false, defaultValue = "false") Boolean showLinks);
}
