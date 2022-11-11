package io.codelex.flightplanner.common;

import io.codelex.flightplanner.domain.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Repository
@ConditionalOnProperty(prefix = "flight-planner", name = "store-type", havingValue = "in-memory")
public class CommonInMemoryRepository implements CommonRepository {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final List<Flight> flightList = new ArrayList<>();

    public CommonInMemoryRepository() {
    }

    public Flight addFlight(AddFlightRequest flightRequest) {
        int flightId = flightList.size();
        Airport flightFrom = flightRequest.getFrom();
        Airport flightTo = flightRequest.getTo();
        String flightCarrier = flightRequest.getCarrier();
        LocalDateTime flightDepartureTime = LocalDateTime.parse(flightRequest.getDepartureTime(), formatter);
        LocalDateTime flightArrivalTime = LocalDateTime.parse(flightRequest.getArrivalTime(), formatter);
        if (flightDepartureTime.compareTo(flightArrivalTime) >= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Entered times not possible for a correct flight");
        }
        Flight flightToAdd = new Flight(flightId, flightFrom, flightTo, flightCarrier, flightDepartureTime, flightArrivalTime);
        if (flightList.stream().anyMatch(flight -> flight.equals(flightToAdd))) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Can not add 2 identical flights!");
        }
        flightList.add(flightToAdd);
        return flightToAdd;
    }

    public Flight fetchFlight(int id) {
        return flightList.stream().
                filter(flight -> flight.getId() == id)
                .findAny()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Requested flight not found in the DB!!!"));
    }

    public PageResult<Flight> searchFlights(SearchFlightsRequest request) {
        List<Flight> flightsFound = flightList.stream()
                .filter(flight -> flight.getFrom().getAirport().equals(request.getFrom()) &&
                        flight.getTo().getAirport().equals(request.getTo()) &&
                        flight.getDepartureTime().toLocalDate().toString().equals(request.getDepartureDate())).toList();
        return new PageResult<>(0, flightsFound.size(), flightsFound);
    }

    public Set<Airport> searchAirports(String search) {
        Set<Airport> matchedAirport = new HashSet<>();
        String normalizedSearch = search.trim()
                .toLowerCase();
        Optional<Flight> matchedFromAirport = flightList.stream()
                .filter(flight -> flight.getFrom().getCountry().toLowerCase().startsWith(normalizedSearch) ||
                        flight.getFrom().getCity().toLowerCase().startsWith(normalizedSearch) ||
                        flight.getFrom().getAirport().toLowerCase().startsWith(normalizedSearch))
                .findAny();
        Optional<Flight> matchedToAirport = flightList.stream()
                .filter(flight -> flight.getTo().getCountry().toLowerCase().startsWith(normalizedSearch) ||
                        flight.getTo().getCity().toLowerCase().startsWith(normalizedSearch) ||
                        flight.getTo().getAirport().toLowerCase().startsWith(normalizedSearch))
                .findAny();
        matchedFromAirport.ifPresent(flight -> matchedAirport.add(flight.getFrom()));
        matchedToAirport.ifPresent(flight -> matchedAirport.add(flight.getTo()));
        return matchedAirport;
    }

    public void deleteFlight(int id) {
        flightList.removeIf(flight -> flight.getId() == id);
    }

    public void clear() {
        flightList.clear();
    }

}