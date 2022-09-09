package com.frysning.springdnd.size;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sizes")
public class SizeController {


    @GetMapping()
    List<Size> all() {
        return Stream.of(Size.values())
            .collect(Collectors.toList());
    }


}
