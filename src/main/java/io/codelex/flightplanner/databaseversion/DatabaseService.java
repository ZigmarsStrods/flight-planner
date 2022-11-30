package io.codelex.flightplanner.databaseversion;

import io.codelex.flightplanner.config.AbstractService;
import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.dto.AddFlightRequest;
import io.codelex.flightplanner.dto.PageResult;
import io.codelex.flightplanner.dto.SearchFlightsRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class DatabaseService extends AbstractService {

    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;

    @Override
    public Flight addFlight(AddFlightRequest flightRequest) {
        Flight flightToAdd = getFlightCarrierAndTimesFromRequest(flightRequest);
        Airport fromAirport = findOrSaveAirport(flightRequest.getFrom());
        flightToAdd.setFrom(fromAirport);
        Airport toAirport = findOrSaveAirport(flightRequest.getTo());
        flightToAdd.setTo(toAirport);
        if (flightRepository.existsByFromAndToAndCarrierAndDepartureTimeAndArrivalTime(flightToAdd.getFrom(),
                flightToAdd.getTo(), flightToAdd.getCarrier(), flightToAdd.getDepartureTime(),
                flightToAdd.getArrivalTime())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Can not add 2 identical flights!");
        }
        return flightRepository.save(flightToAdd);
    }

    @Override
    public Flight fetchFlight(int id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Requested flight not found in the DB!!!"));
    }

    @Override
    public PageResult<Flight> searchFlights(SearchFlightsRequest request) {
        LocalDate formattedRequestDepartureDate = getFormattedRequestDepartureDate(request);
        List<Flight> flightsFound = flightRepository.searchFlights(request.getFrom(), request.getTo());
        return new PageResult<>(0, flightsFound.size(), flightsFound);
    }

    @Override
    public Set<Airport> searchAirports(String search) {
        String normalizedSearch = getNormalizedSearch(search);
        return airportRepository.searchAirport(normalizedSearch);
    }

    @Override
    public void deleteFlight(int id) {
        if (flightRepository.existsById(id)) {
            flightRepository.deleteById(id);
        }
    }

    @Override
    public void clear() {
        flightRepository.deleteAll();
    }

    private Airport findOrSaveAirport(Airport airportSearch) {
        return airportRepository.findById(airportSearch.getAirport())
                .orElse(airportRepository.save(airportSearch));
    }
}