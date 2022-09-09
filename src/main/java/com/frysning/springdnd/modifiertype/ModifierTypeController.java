package com.frysning.springdnd.modifiertype;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("modifiertypes")
public class ModifierTypeController {


    @GetMapping()
    List<ModifierType> all() {
        return Stream.of(ModifierType.values())
            .collect(Collectors.toList());
    }


}
