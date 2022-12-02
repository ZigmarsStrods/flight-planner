package io.codelex.flightplanner.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "from_airport")
    private Airport from;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "to_airport")
    private Airport to;
    private String carrier;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column
    private LocalDateTime departureTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime arrivalTime;

    public Flight(String carrier, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        this.carrier = carrier;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Airport getFrom() {
        return from;
    }

    public void setFrom(Airport from) {
        this.from = from;
    }

    public Airport getTo() {
        return to;
    }

    public void setTo(Airport to) {
        this.to = to;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
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
