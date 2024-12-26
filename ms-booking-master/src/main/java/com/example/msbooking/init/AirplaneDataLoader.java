package com.example.msbooking.init;

import com.example.msbooking.model.entities.Airplane;
import com.example.msbooking.model.enums.EAirplane;
import com.example.msbooking.repository.AirplaneRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class AirplaneDataLoader implements CommandLineRunner {
    private final AirplaneRepository airplaneRepository;

    public AirplaneDataLoader(AirplaneRepository airplaneRepository) {
        this.airplaneRepository = airplaneRepository;
    }

    @Override
    public void run(String... args) {
        for (EAirplane eAirplane : EAirplane.values()) {
            Airplane airplane = airplaneRepository.findByAirplane(eAirplane);
            if (airplane == null) {
                airplane = Airplane.builder()
                        .airplane(eAirplane)
                        .build();
                airplaneRepository.save(airplane);
            }
        }
    }
}
