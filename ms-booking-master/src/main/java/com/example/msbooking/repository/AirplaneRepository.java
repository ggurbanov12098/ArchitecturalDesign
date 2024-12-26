package com.example.msbooking.repository;

import com.example.msbooking.model.entities.Airplane;
import com.example.msbooking.model.enums.EAirplane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirplaneRepository extends JpaRepository<Airplane, Long> {
    Airplane findByAirplane(EAirplane eAirplane);
}
