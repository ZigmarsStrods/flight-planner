package io.codelex.flightplanner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchFlightsRequest {

    @NotBlank
    private String from;
    @NotBlank
    private String to;
    @NotBlank
    private String departureDate;
}