package io.codelex.flightplanner.services;

import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.dto.AddFlightRequest;
import io.codelex.flightplanner.dto.PageResult;
import io.codelex.flightplanner.dto.SearchFlightsRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Service
public abstract class FlightPlannerService {

    protected final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    protected final Set<Airport> matchedAirportSet = new HashSet<>();

    public abstract Flight addFlight(AddFlightRequest flightRequest);

    public abstract Flight fetchFlight(int id);

    public abstract PageResult<Flight> searchFlights(SearchFlightsRequest request);

    public abstract Set<Airport> searchAirports(String search);

    public abstract void deleteFlight(int id);

    public abstract void clear();

    protected Flight getFlightCarrierAndTimesFromRequest(AddFlightRequest flightRequest) {
        String flightCarrier = flightRequest.getCarrier();
        LocalDateTime flightDepartureTime = LocalDateTime.parse(flightRequest.getDepartureTime(), formatter);
        LocalDateTime flightArrivalTime = LocalDateTime.parse(flightRequest.getArrivalTime(), formatter);
        if (flightDepartureTime.compareTo(flightArrivalTime) >= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Entered times not possible for a correct flight");
        }
        return new Flight(flightCarrier, flightDepartureTime, flightArrivalTime);
    }

    protected LocalDate getFormattedRequestDepartureDate(SearchFlightsRequest request) {
        return LocalDate.parse(request.getDepartureDate());
    }

    protected String getNormalizedSearch(String search) {
        return search.trim()
                .toLowerCase();
    }

    protected void setAirports(Flight flightToAdd, Airport from, Airport to) {
        flightToAdd.setFrom(from);
        flightToAdd.setTo(to);
    }
}