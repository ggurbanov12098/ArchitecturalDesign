package com.example.msbooking.service.impl;

import com.example.msbooking.client.CustomerClient;
import com.example.msbooking.client.PaymentClient;
import com.example.msbooking.model.dto.BookingDTO;
import com.example.msbooking.model.dto.PaymentDTO;
import com.example.msbooking.model.entities.Flight;
import com.example.msbooking.model.request.BookingRequest;
import com.example.msbooking.model.entities.Booking;
import com.example.msbooking.repository.BookingRepository;
import com.example.msbooking.repository.FlightRepository;
import com.example.msbooking.service.BookingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;
    private final CustomerClient customerClient;
    private final PaymentClient paymentClient;

    BookingServiceImpl(BookingRepository bookingRepository, FlightRepository flightRepository,
                       CustomerClient customerClient, PaymentClient paymentClient) {
        this.bookingRepository = bookingRepository;
        this.flightRepository = flightRepository;
        this.customerClient = customerClient;
        this.paymentClient = paymentClient;
    }

    @Override
    public List<Booking> findAllByCustomerId(Long customerId) {
        return bookingRepository.getAllByCustomer(customerId);
    }

    @Override
    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    @Override
    public BookingDTO getBookingById(Long id) {
        var booking = bookingRepository.findById(id).get();
        return BookingDTO.builder()
                .id(booking.getId())
                .customerId(booking.getCustomer())
                .flightId(booking.getFlight().getId())
                .bookingStatus(booking.getBookingStatus())
                .build();
    }

    @Override
    public Booking save(BookingRequest bookingRequest) {
        var customer = customerClient.findCustomer(bookingRequest.getCustomerId());
        if (customer == null) {
            throw new IllegalStateException("Customer with ID " + bookingRequest.getCustomerId() + " not found.");
        }

        Optional<Flight> optionalFlight = flightRepository.findById(bookingRequest.getFlightId());
        Flight flight = optionalFlight.orElseThrow(() -> new IllegalStateException("Flight with ID " + bookingRequest.getFlightId() + " not found."));

        var booking = Booking.builder()
                .flight(flight)
                .customer(bookingRequest.getCustomerId())
                .bookingStatus(true)
                .build();
        booking = bookingRepository.save(booking);

        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setBookingId(booking.getId());

        paymentClient.createPayment(paymentDTO);

        return booking;
    }

    @Override
    public Booking cancelBooking(Long id) {
        Booking booking = bookingRepository.findById(id).get();
        booking.cancelReservation();
        bookingRepository.save(booking);
        return booking;
    }
}
