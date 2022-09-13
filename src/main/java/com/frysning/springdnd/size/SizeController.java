package com.frysning.springdnd.size;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sizes")
public class SizeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SizeController.class);

    @GetMapping()
    List<Size> all() {
        LOGGER.info("GET all sizes");
        return Stream.of(Size.values())
            .collect(Collectors.toList());
    }


}
