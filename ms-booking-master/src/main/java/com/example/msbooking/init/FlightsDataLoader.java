package com.example.msbooking.init;

import com.example.msbooking.model.entities.Airplane;
import com.example.msbooking.model.entities.Flight;
import com.example.msbooking.model.enums.EAirplane;
import com.example.msbooking.repository.AirplaneRepository;
import com.example.msbooking.repository.FlightRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
@Order(2)
public class FlightsDataLoader implements CommandLineRunner {
    private static final int NUMBER_OF_FLIGHTS_TO_CREATE = 50;
    private final AirplaneRepository airplaneRepository;
    private final FlightRepository flightRepository;
    public FlightsDataLoader(AirplaneRepository airplaneRepository, FlightRepository flightRepository) {
        this.airplaneRepository = airplaneRepository;
        this.flightRepository = flightRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadFlightDataIfNeeded();
    }

    private void loadFlightDataIfNeeded() {
        if (flightRepository.count() < NUMBER_OF_FLIGHTS_TO_CREATE) { // Simple check to prevent re-insertion
            createFlights();
        }
    }

    public void createFlights() {
        long existingFlightsCount = flightRepository.count();
        if (existingFlightsCount >= NUMBER_OF_FLIGHTS_TO_CREATE) {
            return; // If there are already 50 or more flights, do nothing
        }

        Random random = new Random();
        List<String> origins = Arrays.asList("New York JFK", "London Heathrow", "Dubai International", "Tokyo Haneda", "Los Angeles LAX");
        List<String> destinations = Arrays.asList("Paris CDG", "Shanghai Pudong", "Singapore Changi", "Hong Kong International", "Sydney Kingsford");

        Set<Long> usedFlightNumbers = new HashSet<>();
        while (usedFlightNumbers.size() < NUMBER_OF_FLIGHTS_TO_CREATE) {
            long flightNumber = 1000L + random.nextInt(50); // Ensure unique flight numbers up to 50 different ones
            if (!usedFlightNumbers.contains(flightNumber)) {
                LocalDate departureDate = LocalDate.now().plusDays(random.nextInt(30));
                LocalDate returnDate = LocalDate.now().plusDays(random.nextInt(30));
                String origin = origins.get(random.nextInt(origins.size()));
                String destination = destinations.get(random.nextInt(destinations.size()));
                List<Airplane> listOfAirplanes = new ArrayList<>();

                for (EAirplane eAirplane : EAirplane.values()) {
                    Airplane airplane = airplaneRepository.findByAirplane(eAirplane);
                    listOfAirplanes.add(airplane);
                }

                var airplane = listOfAirplanes.get(random.nextInt(listOfAirplanes.size()));

                Flight flight = Flight.builder()
                        .flightNumber(flightNumber)
                        .departureTime(departureDate)
                        .returnTime(returnDate)
                        .origin(origin)
                        .destination(destination)
                        .amount(100.0f + random.nextFloat() * 500) // Pricing between $100 and $600
                        .airplane(airplane)
                        .build();

                flightRepository.save(flight);
                usedFlightNumbers.add(flightNumber); // Track used flight numbers to avoid duplicates
            }
        }
    }
}