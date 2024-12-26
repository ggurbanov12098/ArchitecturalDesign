package com.example.msbooking.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightSearch {
    private String origin;
    private String destination;
    private LocalDate departureTime;
    private LocalDate returnTime;
}
