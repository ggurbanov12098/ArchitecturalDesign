package com.example.msbooking.repository;

import com.example.msbooking.model.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByOriginAndDestinationAndDepartureTime(String origin, String destination, LocalDate departureTime);
    List<Flight> findByOrigin(@Param("origin") String origin);
    List<Flight> findByDestination(@Param("destination") String destination);
    List<Flight> findByDepartureTime(@Param("departureTime") LocalDate departureTime);
    List<Flight> findByOriginAndDestination(@Param("origin") String origin, @Param("destination") String destination);
}