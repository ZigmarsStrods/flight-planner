package io.codelex.flightplanner.common;

import io.codelex.flightplanner.domain.*;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class CommonInMemoryRepository implements CommonRepository {

    private final MemoryDB memoryDB;

    public CommonInMemoryRepository(MemoryDB memoryDB) {
        this.memoryDB = memoryDB;
    }

    public Flight addFlight(AddFlightRequest flightRequest) {
        return memoryDB.addFlight(flightRequest);
    }

    @Override
    public Flight fetchFlight(int id) {
        return memoryDB.fetchFlight(id);
    }

    public PageResult<Flight> searchFlights(SearchFlightsRequest request) {
        return memoryDB.searchFlights(request);
    }

    public Set<Airport> searchAirports(String search) {
        return memoryDB.searchAirports(search);
    }

    @Override
    public void deleteFlight(int id) {
        memoryDB.deleteFlight(id);
    }

    public void clear() {
        memoryDB.clear();
    }

}