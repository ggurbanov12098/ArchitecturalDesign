package com.example.msbooking.controller;

import com.example.msbooking.model.dto.FlightDTO;
import com.example.msbooking.model.entities.Flight;
import com.example.msbooking.model.entities.FlightSearch;
import com.example.msbooking.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
@CrossOrigin(origins = "*", maxAge = 3600)
public class FlightController {

    @Autowired
    private FlightService flightService;

    @PostMapping("/search")
    public ResponseEntity<List<Flight>> searchFlights(@RequestBody FlightSearch searchCriteria) {
        List<Flight> flights = flightService.searchFlights(searchCriteria);
        return ResponseEntity.ok(flights);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightDTO> getFlightById(@PathVariable Long id) {
        var flight = flightService.getFlightById(id);
        return ResponseEntity.ok(flight);
    }
}