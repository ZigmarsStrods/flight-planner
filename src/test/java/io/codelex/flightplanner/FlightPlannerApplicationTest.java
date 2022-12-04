package io.codelex.flightplanner;

import io.codelex.flightplanner.controllers.AdminController;
import io.codelex.flightplanner.domain.Airport;
import io.codelex.flightplanner.domain.Flight;
import io.codelex.flightplanner.dto.AddFlightRequest;
import io.codelex.flightplanner.controllers.TestController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
public class FlightPlannerApplicationTest {

    @Autowired
    AdminController adminController;

    @Autowired
    TestController testController;

    private final Airport from = new Airport("Latvia", "Riga", "RIX");
    private final Airport to = new Airport("Denmark", "Copenhagen", "CPH");
    private final String carrier = "AirBaltic";
    private final String departureTime = "2022-12-01 12:12";
    private final String arrivalTime = "2022-12-04 12:12";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final LocalDateTime formattedDepartureTime = LocalDateTime.parse(departureTime, formatter);
    private final LocalDateTime formattedArrivalTime = LocalDateTime.parse(arrivalTime, formatter);
    private final AddFlightRequest testFlightRequest = new AddFlightRequest(from, to, carrier, departureTime, arrivalTime);

    @BeforeEach
    void clearFlights() {
        testController.clear();
    }

    @Test
    void canAddFlight() {

        Flight savedFlight = adminController.addFlight(testFlightRequest);

        Assertions.assertNotEquals(0, savedFlight.getId());
        Assertions.assertEquals(from, savedFlight.getFrom());
        Assertions.assertEquals(to, savedFlight.getTo());
        Assertions.assertEquals(carrier, savedFlight.getCarrier());
        Assertions.assertEquals(formattedDepartureTime, savedFlight.getDepartureTime());
        Assertions.assertEquals(formattedArrivalTime, savedFlight.getArrivalTime());
    }
}