package io.codelex.flightplanner.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Objects;

public class Flight {

    private int id;

    private Airport from;

    private Airport to;

    private String carrier;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime departureTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime arrivalTime;

    public Flight() {
    }

    public Flight(int id, Airport from, Airport to, String carrier, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        this.id = id;
        this.from = from;
        this.to = to;
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
    public int hashCode() {
        return Objects.hash(from, to, carrier, departureTime, arrivalTime);
    }
}
