package io.codelex.flightplanner.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    private Airport from;
    @ManyToOne
    private Airport to;
    private String carrier;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime departureTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime arrivalTime;

    public Flight(String carrier, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        this.carrier = carrier;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public boolean areFlightsEqual(Flight flightToCompare) {
        if (this == flightToCompare) return true;
        return from.equals(flightToCompare.from) &&
                to.equals(flightToCompare.to) &&
                carrier.equals(flightToCompare.carrier) &&
                departureTime.equals(flightToCompare.departureTime) &&
                arrivalTime.equals(flightToCompare.arrivalTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return id == flight.id && from.equals(flight.from) && to.equals(flight.to) && carrier.equals(flight.carrier) && departureTime.equals(flight.departureTime) && arrivalTime.equals(flight.arrivalTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, from, to, carrier, departureTime, arrivalTime);
    }
}