package io.codelex.flightplanner.controller;

import io.codelex.flightplanner.service.FlightPlannerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/testing-api")
public class TestController {

    private final FlightPlannerService flightPlannerService;

    @PostMapping("/clear")
    public void clear() {
        flightPlannerService.clear();
    }
}
