package com.example.msbooking.service.impl;

import com.example.msbooking.model.dto.FlightDTO;
import com.example.msbooking.model.entities.Flight;
import com.example.msbooking.model.entities.FlightSearch;
import com.example.msbooking.repository.FlightRepository;
import com.example.msbooking.service.FlightService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public FlightDTO getFlightById(Long id) {
        var flight = flightRepository.findById(id).get();

        return FlightDTO.builder()
                .id(flight.getId())
                .amount(flight.getAmount())
                .build();
    }

    @Override
    public List<Flight> searchFlights(FlightSearch searchCriteria) {
        if (searchCriteria.getReturnTime() == null) {
            return flightRepository.findByOriginAndDestinationAndDepartureTime(
                    searchCriteria.getOrigin(), searchCriteria.getDestination(), searchCriteria.getDepartureTime());
        } else if (searchCriteria.getDestination() == null && searchCriteria.getDepartureTime() == null && searchCriteria.getReturnTime() == null) {
            return flightRepository.findByOrigin(searchCriteria.getOrigin());
        } else if (searchCriteria.getOrigin() == null && searchCriteria.getDepartureTime() == null && searchCriteria.getReturnTime() == null) {
            return flightRepository.findByDestination(searchCriteria.getDestination());
        } else {
            return flightRepository.findByOriginAndDestination(searchCriteria.getOrigin(), searchCriteria.getDestination());
        }
    }
}