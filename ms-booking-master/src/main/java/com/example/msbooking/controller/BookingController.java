package com.example.msbooking.controller;

import com.example.msbooking.model.dto.BookingDTO;
import com.example.msbooking.model.request.BookingRequest;
import com.example.msbooking.model.entities.Booking;
import com.example.msbooking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*", maxAge = 3600)
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<Booking>> getAllBookingsByCustomerId(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.findAllByCustomerId(id));
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.findAll();
    }

    @PostMapping
    public Booking createBooking(@RequestBody BookingRequest bookingRequest) {
        return bookingService.save(bookingRequest);
    }

    @GetMapping("{id}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long id) {
        var booking = bookingService.getBookingById(id);
        return ResponseEntity.ok(booking);
    }

    @PutMapping("/{id}/cancel")
    public Booking cancelBooking(@PathVariable Long id) {
        return bookingService.cancelBooking(id);
    }
}
