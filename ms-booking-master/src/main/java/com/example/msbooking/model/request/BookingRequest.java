package com.example.msbooking.model.request;

import lombok.Data;

@Data
public class BookingRequest {
    private Long flightId;
    private Long customerId;
}
