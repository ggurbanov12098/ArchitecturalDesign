package com.example.msbooking.client;

import com.example.msbooking.model.dto.RegisteredCustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-customer", url = "http://localhost:8081/api")
public interface CustomerClient {

    @GetMapping("/customers/{id}")
    RegisteredCustomerDTO findCustomer(@PathVariable Long id);
}