package io.codelex.flightplanner.databaseversion;

import io.codelex.flightplanner.domain.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface AirportRepository extends JpaRepository<Airport, String> {

    @Query("SELECT a FROM Airport a WHERE lower(a.airport) LIKE ?1%" +
            "OR lower(a.city) LIKE ?1% OR lower(a.country) LIKE ?1%")
    Set<Airport> searchAirport(String searchString);
}