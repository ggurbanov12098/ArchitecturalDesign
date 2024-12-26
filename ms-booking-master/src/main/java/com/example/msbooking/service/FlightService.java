package com.example.msbooking.service;

import com.example.msbooking.model.dto.FlightDTO;
import com.example.msbooking.model.entities.Flight;
import com.example.msbooking.model.entities.FlightSearch;

import java.util.List;

public interface FlightService {
    FlightDTO getFlightById(Long id);

    List<Flight> searchFlights(FlightSearch searchCriteria);
}
