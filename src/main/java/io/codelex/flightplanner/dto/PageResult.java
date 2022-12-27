package io.codelex.flightplanner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {
    private int page;
    private int totalItems;
    private List<T> items;
}
