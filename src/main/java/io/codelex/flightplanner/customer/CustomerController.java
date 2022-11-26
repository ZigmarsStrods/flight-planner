package io.codelex.flightplanner.customer;

import io.codelex.flightplanner.common.CommonService;
import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.dto.PageResult;
import io.codelex.flightplanner.dto.SearchFlightsRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class CustomerController {

    private final CommonService commonService;

    public CustomerController(CommonService commonService) {
        this.commonService = commonService;
    }

    @PostMapping("/flights/search")
    public PageResult<Flight> searchFlights(@Valid @RequestBody SearchFlightsRequest request) {
        if (request.getTo().equals(request.getFrom())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return commonService.searchFlights(request);
    }

    @GetMapping("/flights/{id}")
    public Flight findFlightById(@PathVariable int id) {
        return commonService.fetchFlight(id);
    }

    @GetMapping("/airports")
    public Set<Airport> searchAirports(String search) {
        return commonService.searchAirports(search);
    }
}
