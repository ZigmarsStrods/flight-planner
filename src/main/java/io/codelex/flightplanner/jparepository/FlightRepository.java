package io.codelex.flightplanner.jparepository;

import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    boolean existsByFromAndToAndCarrierAndDepartureTimeAndArrivalTime(Airport from, Airport to, String carrier, LocalDateTime departureTime, LocalDateTime arrivalTime);

    @Query("Select f from Flight f where f.from.airport = ?1 "
            + "AND f.to.airport = ?2 "
            + "AND f.departureTime between cast(?3 as timestamp) and cast(?4 as timestamp)")
    List<Flight> searchFlights(String from, String to, LocalDate departureDate, LocalDate departureDatePlusOneDay);
}
