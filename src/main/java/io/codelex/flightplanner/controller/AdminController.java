package io.codelex.flightplanner.controller;

import io.codelex.flightplanner.service.FlightPlannerService;
import io.codelex.flightplanner.dto.AddFlightRequest;
import io.codelex.flightplanner.domain.Flight;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/admin-api")
public class AdminController {

    private final FlightPlannerService flightPlannerService;

    @PutMapping("/flights")
    @ResponseStatus(HttpStatus.CREATED)
    public synchronized Flight addFlight(@Valid @RequestBody final AddFlightRequest flightRequest) {
        if (flightRequest.getTo().equals(flightRequest.getFrom())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return flightPlannerService.addFlight(flightRequest);
    }

    @GetMapping("/flights/{id}")
    public Flight fetchFlight(@PathVariable final int id) {
        return flightPlannerService.fetchFlight(id);
    }

    @DeleteMapping("/flights/{id}")
    public synchronized void deleteFlight(@PathVariable final int id) {
        flightPlannerService.deleteFlight(id);
    }
}
