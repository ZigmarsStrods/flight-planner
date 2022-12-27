package io.codelex.flightplanner.jparepository;

import io.codelex.flightplanner.domain.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AirportRepository extends JpaRepository<Airport, String> {

    @Query("SELECT a FROM Airport a WHERE lower(a.airport) LIKE ?1%"
            + "OR lower(a.city) LIKE ?1% OR lower(a.country) LIKE ?1%")
    Set<Airport> searchAirport(String searchString);
}
