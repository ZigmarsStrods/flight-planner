package io.codelex.flightplanner.controllers;

import io.codelex.flightplanner.services.AbstractService;
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
    public void clear() {
        abstractService.clear();
    }
}