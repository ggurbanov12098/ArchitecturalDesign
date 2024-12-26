package com.example.msbooking.service;

import com.example.msbooking.model.dto.BookingDTO;
import com.example.msbooking.model.entities.Booking;
import com.example.msbooking.model.request.BookingRequest;

import java.util.List;

public interface BookingService {
    List<Booking> findAllByCustomerId(Long customerId);

    List<Booking> findAll();

    BookingDTO getBookingById(Long id);

    Booking save(BookingRequest bookingRequest);
    Booking cancelBooking(Long id);
}
