package io.codelex.flightplanner.admin;

import io.codelex.flightplanner.config.AbstractService;
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

    private final AbstractService abstractService;

    @PutMapping("/flights")
    @ResponseStatus(HttpStatus.CREATED)
    public synchronized Flight addFlight(@Valid @RequestBody AddFlightRequest flightRequest) {
        if (flightRequest.getTo().equals(flightRequest.getFrom())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return abstractService.addFlight(flightRequest);
    }

    @GetMapping("/flights/{id}")
    public Flight fetchFlight(@PathVariable int id) {
        return abstractService.fetchFlight(id);
    }

    @DeleteMapping("/flights/{id}")
    public synchronized void deleteFlight(@PathVariable int id) {
        abstractService.deleteFlight(id);
    }
}