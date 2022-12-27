package io.codelex.flightplanner.service;

import io.codelex.flightplanner.jparepository.AirportRepository;
import io.codelex.flightplanner.jparepository.FlightRepository;
import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.dto.AddFlightRequest;
import io.codelex.flightplanner.dto.PageResult;
import io.codelex.flightplanner.dto.SearchFlightsRequest;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@ConditionalOnProperty(prefix = "flight-planner", name = "store-type", havingValue = "database")
@AllArgsConstructor
public class DatabaseService extends FlightPlannerService {

    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;

    @Override
    public Flight addFlight(final AddFlightRequest flightRequest) {
        Flight flightToAdd = getFlightCarrierAndTimesFromRequest(flightRequest);
        Airport fromAirport = findOrSaveAirport(flightRequest.getFrom());
        Airport toAirport = findOrSaveAirport(flightRequest.getTo());
        setAirports(flightToAdd, fromAirport, toAirport);
        if (flightRepository.existsByFromAndToAndCarrierAndDepartureTimeAndArrivalTime(flightToAdd.getFrom(),
                flightToAdd.getTo(), flightToAdd.getCarrier(),
                flightToAdd.getDepartureTime(), flightToAdd.getArrivalTime())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Can not add 2 identical flights!");
        }
        return flightRepository.save(flightToAdd);
    }

    @Override
    public Flight fetchFlight(final int id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Requested flight not found in the DB!!!"));
    }

    @Override
    public PageResult<Flight> searchFlights(final SearchFlightsRequest request) {
        LocalDate formattedRequestDepartureDate = getFormattedRequestDepartureDate(request);
        LocalDate departureDatePlusOneDay = formattedRequestDepartureDate.plusDays(1);
        List<Flight> flightsFound = flightRepository.searchFlights(request.getFrom(), request.getTo(), formattedRequestDepartureDate, departureDatePlusOneDay);
        return new PageResult<>(0, flightsFound.size(), flightsFound);
    }

    @Override
    public Set<Airport> searchAirports(final String search) {
        String normalizedSearch = getNormalizedSearch(search);
        return airportRepository.searchAirport(normalizedSearch);
    }

    @Override
    public void deleteFlight(final int id) {
        if (flightRepository.existsById(id)) {
            flightRepository.deleteById(id);
        }
    }

    @Override
    public void clear() {
        flightRepository.deleteAllInBatch();
        airportRepository.deleteAllInBatch();
    }

    private Airport findOrSaveAirport(final Airport airportSearch) {
        return airportRepository.findById(airportSearch.getAirport())
                .orElse(airportRepository.save(airportSearch));
    }
}
