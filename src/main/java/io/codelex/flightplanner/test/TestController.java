package io.codelex.flightplanner.test;

import io.codelex.flightplanner.config.AbstractService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/testing-api")
public class TestController {

    private final AbstractService abstractService;

    @PostMapping("/clear")
    void clear() {
        abstractService.clear();
    }
}