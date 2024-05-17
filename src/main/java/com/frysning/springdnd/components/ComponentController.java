package com.frysning.springdnd.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("components")
public class ComponentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComponentController.class);

    @GetMapping()
    List<Component> all() {
        LOGGER.info("GET all components");
        return Stream.of(Component.values())
            .collect(Collectors.toList());
    }


}
