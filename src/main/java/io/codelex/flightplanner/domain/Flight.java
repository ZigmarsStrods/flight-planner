package io.codelex.flightplanner.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

    @Id
    @GeneratedValue
    @NotNull
    private int id;
    @ManyToOne
    @NotNull
    private Airport from;
    @ManyToOne
    @NotNull
    private Airport to;
    @NotNull
    private String carrier;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotNull
    private LocalDateTime departureTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotNull
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
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Flight flight = (Flight) o;
        return false;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}