package io.codelex.flightplanner.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.text.WordUtils;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Airport {
    @NotBlank
    @NotNull
    private String country;
    @NotBlank
    @NotNull
    private String city;
    @Id
    @NotBlank
    @NotNull
    private String airport;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = WordUtils.capitalizeFully(country);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = WordUtils.capitalizeFully(city);
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport.trim().toUpperCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport1 = (Airport) o;
        return country.equals(airport1.country) && city.equals(airport1.city) && airport.equals(airport1.airport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, city, airport);
    }
}
