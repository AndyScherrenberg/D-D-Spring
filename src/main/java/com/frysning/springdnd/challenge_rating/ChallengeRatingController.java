package com.frysning.springdnd.challenge_rating;

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
@RequestMapping("challengeratings")
public class ChallengeRatingController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ChallengeRatingController.class);
	private final ChallengeRatingRepository repository;

	ChallengeRatingController(ChallengeRatingRepository repository) {
		this.repository = repository;
	}

	@GetMapping()
	List<EntityModel<ChallengeRating>> all(@RequestParam(required = false, defaultValue = "false") Boolean showLinks) {
		LOGGER.info("Get al challenge ratings");
		return repository.findAll().stream()
				.map(EntityModel::of)
				.collect(Collectors.toList());
	}
}