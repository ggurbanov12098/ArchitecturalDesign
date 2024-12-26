package com.example.msbooking.client;

import com.example.msbooking.model.dto.PaymentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ms-payment", url = "http://localhost:8083/api")
public interface PaymentClient {
    @PostMapping("/payments/create")
    PaymentDTO createPayment(@RequestBody PaymentDTO paymentDTO);
}