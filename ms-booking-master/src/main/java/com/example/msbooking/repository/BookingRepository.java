package com.example.msbooking.repository;

import com.example.msbooking.model.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> getAllByCustomer(Long customerId);
}
