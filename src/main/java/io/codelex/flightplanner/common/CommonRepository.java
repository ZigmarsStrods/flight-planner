package io.codelex.flightplanner.common;

import io.codelex.flightplanner.domain.*;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CommonRepository {

    Flight addFlight(AddFlightRequest flightRequest);

    Flight fetchFlight(int id);

    PageResult<Flight> searchFlights(SearchFlightsRequest request);

    Set<Airport> searchAirports(String search);

    void deleteFlight(int id);

    void clear();
}
