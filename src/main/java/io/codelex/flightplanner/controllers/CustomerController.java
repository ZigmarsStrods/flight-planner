package io.codelex.flightplanner.controllers;

import io.codelex.flightplanner.services.FlightPlannerService;
import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.dto.PageResult;
import io.codelex.flightplanner.dto.SearchFlightsRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class CustomerController {

    private final FlightPlannerService flightPlannerService;

    @PostMapping("/flights/search")
    public PageResult<Flight> searchFlights(@Valid @RequestBody SearchFlightsRequest request) {
        if (request.getTo().equals(request.getFrom())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return flightPlannerService.searchFlights(request);
    }

    @GetMapping("/flights/{id}")
    public Flight findFlightById(@PathVariable int id) {
        return flightPlannerService.fetchFlight(id);
    }

    @GetMapping("/airports")
    public Set<Airport> searchAirports(String search) {
        return flightPlannerService.searchAirports(search);
    }
}