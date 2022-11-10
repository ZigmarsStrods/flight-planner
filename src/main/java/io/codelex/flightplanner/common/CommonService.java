package io.codelex.flightplanner.common;

import io.codelex.flightplanner.domain.*;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CommonService {

    private final CommonRepository commonRepo;

    public CommonService(CommonRepository commonRepo) {
        this.commonRepo = commonRepo;
    }

    public Flight addFlight(AddFlightRequest flightRequest) {
        return commonRepo.addFlight(flightRequest);
    }

    public Flight fetchFlight(int id) {
        return commonRepo.fetchFlight(id);
    }

    public PageResult<Flight> searchFlights(SearchFlightsRequest request) {
        return commonRepo.searchFlights(request);
    }

    public Set<Airport> searchAirports(String search) {
        return commonRepo.searchAirports(search);
    }

    public void deleteFlight(int id) {
        commonRepo.deleteFlight(id);
    }

    public void clear() {
        commonRepo.clear();
    }
}
